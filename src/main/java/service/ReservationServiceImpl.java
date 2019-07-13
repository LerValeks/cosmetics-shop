package service;

import models.Client;
import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;
import service.validation.ClientValidator;
import service.validation.EmployeeValidator;
import service.validation.ReservationValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl {

    //TODO: Dan/Robert to discuss if shall be final or static and how static/final may affect?
    private EmployeeDAO employeeDAO;
    private ClientDAO clientDAO;

    public ReservationServiceImpl(EmployeeDAO employeeDAO, ClientDAO clientDAO) {
        this.employeeDAO = employeeDAO;
        this.clientDAO = clientDAO;
    }

    public Reservation makeReservation(Reservation reservation) throws ClientException, EmployeeException, ReservationException {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
            throw new ReservationException("Reservation parameters have been incorrectly initialized!");
        }

        if (!EmployeeValidator.validateIfCurrentEmployeeEmployed(reservation.getEmployee())) {
            throw new EmployeeException("Employee not found!");
        }

        if (ReservationValidator.validateReservationTimeIsAvailable(reservation)) {
            throw new ReservationException("This employee has reservation at proposed time. Please choose another time!");
        }

        if (!ClientValidator.validateClientParameters(reservation.getClient())) {
            throw new ClientException("Client parameters have been incorrectly initialized!");
        }

        if (!ClientValidator.validateIfCurrentClient(reservation.getClient())) {
            clientDAO.add(reservation.getClient());
        } else if (ClientValidator.validateClientHasReservationAtTheSameTime(reservation)) {
            throw new ClientException("You have another reservation at the same time. Please choose another time");
        }
        reservation.getEmployee().getReservations().add(reservation);
        return reservation;
    }

    //TODO: Robert to advise why Set cannot be used in flatMap?
    public List<Reservation> displayReservation(String phoneNumber) {

        return employeeDAO.getAllItems().stream()
                .map(employee -> employee.getReservations())
                .flatMap(List::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }
    public List<Reservation> reservationByClientInSpecificPeriod(String phoneNumber, LocalDate startDate, LocalDate endDate) throws ClientException {
        return employeeDAO.getAllItems().stream()
                .map(employee -> employee.getReservations())
                .flatMap(List::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .filter(reservation -> reservation.getReservationTime().compareTo(startDate)==0 || reservation.getReservationTime().compareTo(startDate)>0)
                .filter(reservation -> reservation.getReservationTime().compareTo(endDate)==0 || reservation.getReservationTime().compareTo(endDate)<0)
                    .collect(Collectors.toList());
    }
    public boolean cancelReservation(Reservation reservation) throws ReservationException {

        if (ReservationValidator.validateReservationIsTimeNotInPast(reservation)) {
            throw new ReservationException("Past due reservation cannot be cancelled!");
        }
        return employeeDAO.getItem(reservation.getEmployee().getId()).getReservations().remove(reservation);
    }
}