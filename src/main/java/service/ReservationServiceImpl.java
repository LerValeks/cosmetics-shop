package service;

import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;
import service.validation.ClientValidator;
import service.validation.EmployeeValidator;
import service.validation.ReservationValidator;

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

    public boolean makeReservation(Reservation reservation) throws ClientException, EmployeeException, ReservationException {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
            throw new ReservationException("Reservation parameters have been incorrectly initialized!");
        }

        if (!EmployeeValidator.validateIfCurrentEmployee(reservation.getEmployee())) {
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
            throw new ClientException("You have reservation at the requested time");
        }
        return reservation.getEmployee().getReservations().add(reservation);
    }

    //TODO: Robert to advsie why Set cannot be used in flatMap?
    public List<Reservation> displayReservation(String phoneNumber) {

        return employeeDAO.getAllItems().stream()
                .map(employee -> employee.getReservations())
                .flatMap(List::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    public boolean cancelReservation(Reservation reservation) throws ReservationException {

        if (ReservationValidator.validateReservationIsTimeNotInPast(reservation)) {
            throw new ReservationException("Past due reservation cannot be cancelled!");
        }
        return employeeDAO.getItem(reservation.getEmployee().getId()).getReservations().remove(reservation);
    }
}