package service.validation;

import models.Reservation;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;

import java.time.LocalDateTime;

public class ReservationValidator {

    private static EmployeeDAO employeeDAO;

    public static boolean validateReservationParameters(Reservation reservation) throws EmployeeException {

        if (reservation == null) return false;

        return validateServiceCategory(reservation)
                && validateEmployee(reservation)
                && validateClient(reservation)
                && validateReservationTime(reservation)
                && validateReservationTimeNotInPast(reservation);
    }

    public static boolean validateReservationTimeAvailable(Reservation reservation) throws ReservationException {

        return employeeDAO.getItem(reservation.getEmployee().getId())
                .getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime == reservation.getReservationTime());
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