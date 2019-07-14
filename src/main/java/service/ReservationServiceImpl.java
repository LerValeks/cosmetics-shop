package service;

import models.Employee;
import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;
import service.validation.ClientValidator;
import service.validation.EmployeeValidator;
import service.validation.ReservationValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl {

    private final EmployeeDAO employeeDAO;
    private final ClientDAO clientDAO;
    private EmployeeValidator employeeValidator;
    private ClientValidator clientValidator;
    private ReservationValidator reservationValidator;

    public ReservationServiceImpl(EmployeeDAO employeeDAO, ClientDAO clientDAO, EmployeeValidator employeeValidator, ClientValidator clientValidator, ReservationValidator reservationValidator) {
        this.employeeDAO = employeeDAO;
        this.clientDAO = clientDAO;
        this.employeeValidator = employeeValidator;
        this.clientValidator = clientValidator;
        this.reservationValidator = reservationValidator;
    }

    public Reservation makeReservation(Reservation reservation) throws ClientException, EmployeeException, ReservationException {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
            throw new ReservationException("Reservation object is null or reservation parameters are incorrectly initialized");
        }

        if (!employeeValidator.validateIfCurrentEmployeeIsEmployed(reservation.getEmployee())) {
            throw new EmployeeException("Employee not found!");
        }

        if (reservationValidator.validateIfReservationTimeIsFree(reservation)) {
            throw new ReservationException("This time is already booked. Please try another time");
        }

        if (!ClientValidator.validateClientParameters(reservation.getClient())) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }

        if (!clientValidator.validateIfExistingClient(reservation.getClient())) {
            clientDAO.add(reservation.getClient());
        } else if (clientValidator.validateIfExistingClientHasReservationAtTheSameTime(reservation)) {
            throw new ClientException("Client has reservation at requested time, please choose another reservation time");
        }
        reservation.getEmployee().getReservations().add(reservation);
        return reservation;
    }

    //TODO: Robert to advise why Set cannot be used in flatMap?
    public List<Reservation> displayReservation(String phoneNumber) {

        return employeeDAO.getAllItems().stream()
                .map(Employee::getReservations)
                .flatMap(List::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    public boolean cancelReservation(Reservation reservation) throws ReservationException {

        if (ReservationValidator.validateReservationIsTimeNotInPast(reservation)) {
            throw new ReservationException("Past due reservation cannot be cancelled!");
        }
        return employeeDAO.getItem(reservation.getEmployee().getPhoneNumber()).getReservations().remove(reservation);
    }

    public List<Reservation> reservationByClientInSpecificPeriod(String phoneNumber, LocalDateTime startDate, LocalDateTime endDate) throws ClientException {

        return employeeDAO.getAllItems().stream()
                .map(Employee::getReservations)
                .flatMap(List::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .filter(reservation -> reservation.getReservationTime().compareTo(startDate) == 0 || reservation.getReservationTime().compareTo(startDate) > 0)
                .filter(reservation -> reservation.getReservationTime().compareTo(endDate) == 0 || reservation.getReservationTime().compareTo(endDate) < 0)
                .collect(Collectors.toList());
    }
}