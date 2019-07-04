package validation;

import models.Employee;
import models.Reservation;
import repository.EmployeeDatabase;

import java.time.LocalDateTime;

public class ReservationValidator {

    private static EmployeeDatabase employeeDatabase;

    public static boolean validateReservationParameters(Reservation reservation) {

        if (reservation == null) return false;

        return validateServiceCategory(reservation)
                && validateEmployee(reservation)
                && validateClient(reservation)
                && validateReservationTime(reservation)
                && validateReservationTimeNotInPast(reservation);
    }

    //TODO: Dan to investigate how 1 particular employee to be retrieved from DB
    //TODO: Dan to add null exception handler if no employee retrieved from DB

    public static boolean validateReservationTimeAvailable(Reservation reservation) {

        Employee employee = employeeDatabase.getItemFromDatabase(reservation.getEmployee().getId()); //Write this method more exactly

        return employee.getReservations().contains(reservation.getReservationTime());
    }

    private static boolean validateServiceCategory(Reservation reservation) {

        return reservation.getServiceCategory() != null;
    }

    private static boolean validateEmployee(Reservation reservation) {

        return reservation.getEmployee() != null;
    }

    private static boolean validateClient(Reservation reservation) {

        return reservation.getClient() != null;
    }

    private static boolean validateReservationTime(Reservation reservation) {

        return reservation.getReservationTime() != null;
    }

    private static boolean validateReservationTimeNotInPast(Reservation reservation) {
        return !reservation.getReservationTime().isBefore(LocalDateTime.now());
    }
}