package service;

import models.Reservation;
import repository.ClientDatabase;
import repository.EmployeeDatabase;
import validation.ClientValidator;
import validation.EmployeeValidator;
import validation.ReservationValidator;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private EmployeeDatabase employeeDatabase;
    private ClientDatabase clientDatabase;

    //TODO: Robert to advise if 2 constructors are needed? Or one with 2 database parameters? How this may affect?

    public ReservationServiceImpl(EmployeeDatabase employeeDatabase, ClientDatabase clientDatabase) {
        this.employeeDatabase = employeeDatabase;
        this.clientDatabase = clientDatabase;
    }

    @Override
    public Reservation bookVisit(Reservation reservation) {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
//throw exception - reservation fields are incorrect
        }

        if (!EmployeeValidator.validateIfCurrentEmployee(reservation)) {
//throw exception - employee is not working anymore
        }

        if (ReservationValidator.validateReservationTimeAvailable(reservation)) {
//throw exception - this time is booked already
        }

        if (!ClientValidator.validateClientParameters(reservation)) {
//throw exception - client fields are incorrect
        }


        if (!ClientValidator.validateIfCurrentClient(reservation)) {

            clientDatabase.addToDatabase(reservation.getClient());

        } else {

            if (ClientValidator.validateClientHasReservationAtTheSameTime(reservation)) {
                //throw exception - client has a reservation at the suggested time
            }
        }

        return reservation;
    }

    @Override
    public List<Reservation> displayVisit(String phoneNumber) {
        return null;
    }

    @Override
    public Long cancelVisit(Reservation reservation) {
        return null;
    }
}