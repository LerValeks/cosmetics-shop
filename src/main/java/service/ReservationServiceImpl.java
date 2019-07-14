package service;

import models.Employee;
import models.Reservation;
import models.ReservationStatus;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;
import service.validation.ClientValidator;
import service.validation.EmployeeValidator;
import service.validation.ReservationValidator;

import java.time.LocalDate;
import java.util.Set;
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

        validateReservationParameters(reservation);
        validateReservationStatus(reservation);
        validateIfEmployeeIsEmployed(reservation);
        validateIfReservationTimeIsAvailable(reservation);
        validateClientParameters(reservation);
        validateIfClientHasReservation(reservation);

        addReservationToEmployeeBook(reservation);

        return reservation;
    }

    public Set<Reservation> showReservationsForSpecicTimePeriod(String phoneNumber, LocalDate startDate, LocalDate endDate) throws ClientException {

        return employeeDAO.getAllItems().stream()
                .map(Employee::getReservations)
                .flatMap(Set::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .filter(reservation -> reservation.getReservationTime().toLocalDate().compareTo(startDate) == 0 || reservation.getReservationTime().toLocalDate().compareTo(startDate) > 0)
                .filter(reservation -> reservation.getReservationTime().toLocalDate().compareTo(endDate) == 0 || reservation.getReservationTime().toLocalDate().compareTo(endDate) < 0)
                .collect(Collectors.toSet());
    }

    public boolean cancelClientReservation(Reservation reservation) throws ReservationException {

        validateReservationParameters(reservation);
        validateReservationIsTimeNotInPast(reservation);

        String phoneNumber = reservation.getEmployee().getPhoneNumber();
        employeeDAO.getItem(phoneNumber).getReservations().remove(reservation);
        reservation.setReservationStatus(ReservationStatus.CANCELLED);

        return employeeDAO.getItem(phoneNumber).getReservations().add(reservation);
    }

    private void validateReservationParameters(Reservation reservation) throws ReservationException {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
            throw new ReservationException("Reservation object is null or reservation parameters are incorrectly initialized");
        }
    }

    private void validateClientParameters(Reservation reservation) throws ClientException {

        if (!ClientValidator.validateClientParameters(reservation.getClient())) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
    }

    private void validateIfEmployeeIsEmployed(Reservation reservation) throws EmployeeException {

        if (!employeeValidator.validateIfCurrentEmployeeIsEmployed(reservation.getEmployee())) {
            throw new EmployeeException("Employee not found!");
        }
    }

    private void validateIfReservationTimeIsAvailable(Reservation reservation) throws ReservationException {

        if (reservationValidator.validateIfReservationTimeIsFree(reservation)) {
            throw new ReservationException("This time is already booked. Please try another time");
        }
    }

    private void validateIfClientHasReservation(Reservation reservation) throws ClientException {

        if (!clientValidator.validateIfExistingClient(reservation.getClient())) {
            clientDAO.add(reservation.getClient());
        } else if (clientValidator.validateIfExistingClientHasReservationAtTheSameTime(reservation)) {
            throw new ClientException("Client has reservation at requested time, please choose another reservation time");
        }
    }

    private void addReservationToEmployeeBook(Reservation reservation) {

        reservation.getEmployee().getReservations().add(reservation);
    }

    private void validateReservationIsTimeNotInPast(Reservation reservation) throws ReservationException {

        if (ReservationValidator.validateReservationIsTimeNotInPast(reservation)) {
            throw new ReservationException("Past due reservation cannot be cancelled!");
        }
    }

    private void validateReservationStatus(Reservation reservation) throws ReservationException {

        if (ReservationValidator.validateReservationStatus(reservation)) {
            throw new ReservationException("Cannot reserve non-pending reservation status!");
        }
    }
}