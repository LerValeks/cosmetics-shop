package service;

import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.validation.ClientValidator_DAN;
import service.validation.EmployeeValidator_DAN;
import service.validation.ReservationValidator;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private EmployeeDAO employeeDAO;
    private ClientDAO clientDAO;

    public ReservationServiceImpl(EmployeeDAO employeeDAO, ClientDAO clientDAO) {
        this.employeeDAO = employeeDAO;
        this.clientDAO = clientDAO;
    }

    @Override
    public Reservation makeReservation(Reservation reservation) {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
//throw exception - reservation fields are incorrect
        }

        if (!EmployeeValidator_DAN.validateIfCurrentEmployee(reservation)) {
//throw exception - employee is not working anymore
        }

        if (ReservationValidator.validateReservationTimeAvailable(reservation)) {
//throw exception - this time is booked already
        }

        if (!ClientValidator_DAN.validateClientParameters(reservation)) {
//throw exception - client fields are incorrect
        }


        if (!ClientValidator_DAN.validateIfCurrentClient(reservation)) {

            clientDAO.add(reservation.getClient());

        } else {

            if (ClientValidator_DAN.validateClientHasReservationAtTheSameTime(reservation)) {
                //throw exception - client has a reservation at the suggested time
            }
        }

        return reservation;
    }

    @Override
    public List<Reservation> displayReservation(String phoneNumber) {
        return null;
    }

    @Override
    public Long cancelReservation(Reservation reservation) {
        return null;
    }
}