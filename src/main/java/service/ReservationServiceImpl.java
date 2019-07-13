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

        if (!EmployeeValidator.validateIfCurrentEmployee(reservation)) {
            throw new EmployeeException("TBC");
        }

        if (ReservationValidator.validateReservationTimeAvailable(reservation)) {
            throw new ReservationException("TBC");
        }

        if (!ClientValidator.validateClientParameters(reservation.getClient())) {
            throw new ClientException("Client parameters have been incorrectly initialized!");
        }


        return reservation;
    }

    public List<Reservation> displayReservation(String phoneNumber) {
        return null;
    }

    public Long cancelReservation(Reservation reservation) {
        return null;
    }
}