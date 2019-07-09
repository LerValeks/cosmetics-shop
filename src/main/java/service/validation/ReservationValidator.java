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

        return validateServiceCategoryIsNotNull(reservation)
                && validateEmployeeIsNotNull(reservation)
                && validateClientIsNotNull(reservation)
                && validateReservationTimeIsNotNull(reservation)
                && validateReservationIsTimeNotInPast(reservation);
    }

    public static boolean validateReservationTimeIsAvailable(Reservation reservation) throws ReservationException {

        return employeeDAO.getItem(reservation.getEmployee().getId())
                .getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime == reservation.getReservationTime());
    }

    public static boolean validateReservationIsAvailable(Reservation reservation) throws ReservationException {

        return employeeDAO.getItem(reservation.getEmployee().getId())
                .getReservations().contains(reservation);
    }

    public static boolean validateReservationIsTimeNotInPast(Reservation reservation) {
        return !reservation.getReservationTime().isBefore(LocalDateTime.now());
    }

    private static boolean validateServiceCategoryIsNotNull(Reservation reservation) {

        return reservation.getServiceCategory() != null;
    }

    private static boolean validateEmployeeIsNotNull(Reservation reservation) {

        return reservation.getEmployee() != null;
    }

    private static boolean validateClientIsNotNull(Reservation reservation) {

        return reservation.getClient() != null;
    }

    private static boolean validateReservationTimeIsNotNull(Reservation reservation) {

        return reservation.getReservationTime() != null;
    }
}