package validation;

import models.Reservation;

import java.time.LocalDateTime;

public class ReservationValidator {

    public static boolean validateReservationParameters(Reservation reservation) {

        if (reservation == null) return false;

        return validateServiceCategory(reservation)
                && validateEmployee(reservation)
                && validateClient(reservation)
                && validateReservationTime(reservation)
                && validateReservationTimeIsValid(reservation);
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

    private static boolean validateReservationTimeIsValid(Reservation reservation) {
        return !reservation.getReservationTime().isBefore(LocalDateTime.now());
    }
}