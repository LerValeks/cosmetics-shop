package service.validation;

import models.Reservation;
import repository.EmployeeDAO;
import service.exceptions.ReservationException;

import java.time.LocalDate;

public class ReservationValidator {

    private static EmployeeDAO employeeDAO;

    public static boolean validateReservationParameters(Reservation reservation) throws ReservationException {

        if (reservation == null) return false;

        return validateServiceCategoryIsNotNull(reservation)
                && validateEmployeeIsNotNull(reservation)
                && validateClientIsNotNull(reservation)
                && validateReservationTimeIsNotNull(reservation)
                && validateReservationIsTimeNotInPast(reservation)
                && validateReservationServiceMatchesEmployeeSpecialization(reservation);
    }

    public static boolean validateReservationTimeIsAvailable(Reservation reservation) throws ReservationException {

        return employeeDAO.getItem(reservation.getEmployee().getId())
                .getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime == reservation.getReservationTime());
    }

    //TODO: Check where used
    public static boolean validateReservationIsAvailable(Reservation reservation) throws ReservationException {

        return employeeDAO.getItem(reservation.getEmployee().getId())
                .getReservations().contains(reservation);
    }

    public static boolean validateReservationIsTimeNotInPast(Reservation reservation) {
        return !reservation.getReservationTime().isBefore(LocalDate.now());
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

    private static boolean validateReservationServiceMatchesEmployeeSpecialization(Reservation reservation) throws ReservationException {

        return reservation.getEmployee().getServiceCategory().equals(reservation.getServiceCategory());
    }
}