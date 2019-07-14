package service.validation;

import models.Reservation;
import repository.EmployeeDAO;
import service.exceptions.ReservationException;

import java.time.LocalDateTime;

public class ReservationValidator {

    private final EmployeeDAO employeeDAO;

    public ReservationValidator(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public static boolean validateReservationParameters(Reservation reservation) throws ReservationException {

        return validateReservationIsNotNull(reservation)
                && validateServiceCategoryIsNotNull(reservation)
                && validateEmployeeIsNotNull(reservation)
                && validateClientIsNotNull(reservation)
                && validateReservationTimeIsNotNull(reservation)
                && validateReservationIsTimeNotInPast(reservation)
                && validateReservationServiceMatchesEmployeeSpecialization(reservation);
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

    public static boolean validateReservationIsTimeNotInPast(Reservation reservation) {

        return !reservation.getReservationTime().isBefore(LocalDateTime.now());
    }

    private static boolean validateReservationServiceMatchesEmployeeSpecialization(Reservation reservation) throws ReservationException {

        return reservation.getEmployee().getServiceCategory().equals(reservation.getServiceCategory());
    }

    public boolean validateIfReservationTimeIsFree(Reservation reservation) throws ReservationException {

        return employeeDAO.getItem(reservation.getEmployee().getPhoneNumber())
                .getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime == reservation.getReservationTime());
    }
}