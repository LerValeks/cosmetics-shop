package validation;

import models.Reservation;

public class ReservationValidator {

    public static boolean validateReservationParameters(Reservation reservation) {

        if (reservation == null) return false;

        return validateServiceCategory(reservation)
                && validateEmployee(reservation)
                && validateClient(reservation)
                && validateReservationTime(reservation);
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
}