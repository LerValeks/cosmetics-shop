package service.validation;

import models.Employee;
import models.Reservation;
import models.ReservationStatus;
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

    //TODO: Robert to advise this approach and impact on reservation cancellation
    public static boolean validateReservationIsTimeNotInPast(Reservation reservation) {

        LocalDateTime reservationTime = reservation.getReservationTime();
        LocalDateTime today = LocalDateTime.now();

        return !reservationTime.isBefore(today.minusMinutes(5));
    }

    private static boolean validateReservationServiceMatchesEmployeeSpecialization(Reservation reservation) throws ReservationException {

        return reservation.getEmployee().getServiceCategory().equals(reservation.getServiceCategory());
    }

    public static boolean validateReservationStatus(Reservation reservation) throws ReservationException {

        return reservation.getReservationStatus().equals(ReservationStatus.ACTIVE);
    }

    public boolean validateIfReservationTimeIsFree(Reservation reservation) throws ReservationException {

        Employee employee = employeeDAO.getItem(reservation.getEmployee().getPhoneNumber());
        LocalDateTime reservationTime = reservation.getReservationTime();

        return employee.getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime.equals(reservationTime));
    }
}