package service.validation;

import models.Reservation;
import models.ReservationStatus;
import models.ServiceCategory;
import service.exceptions.ReservationException;

import java.time.LocalDateTime;

public class ReservationValidator {

    public static boolean validateReservationParameters(Reservation reservation) throws ReservationException {

        return validateReservationIsNotNull(reservation)
                && validateServiceCategoryIsNotNull(reservation)
                && validateEmployeeIsNotNull(reservation)
                && validateClientIsNotNull(reservation)
                && validateReservationTimeIsNotNull(reservation)
                && validateReservationTimeIsNotInPast(reservation)
                && validateReservationServiceMatchesEmployeeSpecialization(reservation)
                && validateReservationStatusIsActive(reservation);
    }

    private static boolean validateReservationIsNotNull(Reservation reservation) {

        return reservation != null;
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

    //TODO: Robert to advise if this approach is correct from business logic perspective
    private static boolean validateReservationTimeIsNotInPast(Reservation reservation) {

        LocalDateTime reservationTime = reservation.getReservationTime();
        LocalDateTime now = LocalDateTime.now();

        return !reservationTime.isBefore(now.minusMinutes(5));
    }

    private static boolean validateReservationServiceMatchesEmployeeSpecialization(Reservation reservation) {

        ServiceCategory employeeServiceCategory = reservation.getEmployee().getServiceCategory();
        ServiceCategory reservationServiceCategory = reservation.getServiceCategory();

        return employeeServiceCategory.equals(reservationServiceCategory);
    }

    private static boolean validateReservationStatusIsActive(Reservation reservation) {

        return reservation.getReservationStatus().equals(ReservationStatus.ACTIVE);
    }
}