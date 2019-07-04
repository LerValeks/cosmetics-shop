package service.validation;

import models.EmploymentStatus;
import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;

import java.util.stream.Collectors;

public class ClientValidator_DAN {

    private static ClientDAO clientDAO;
    private static EmployeeDAO employeeDAO;

    public static boolean validateClientParameters(Reservation reservation) {

        return validateClientName(reservation)
                && validateClientSurname(reservation)
                && validateClientPhone(reservation);
    }

    public static boolean validateIfCurrentClient(Reservation reservation) {

        return clientDAO.getAllItems().contains(reservation.getClient());
    }

    //TODO: Ammend lambda
    public static boolean validateClientHasReservationAtTheSameTime(Reservation reservation) {

        employeeDAO.getAllItems().stream()
                .filter(employee -> employee.getEmploymentStatus() == EmploymentStatus.EMPLOYED)
                .map(employee -> employee.getReservations())
         //       .flatMap(reservations -> reservation.getReservationTime())
                .collect(Collectors.toList());

        return false;
    }

    private static boolean validateClientName(Reservation reservation) {

        return reservation.getClient().getName() != null;
    }

    private static boolean validateClientSurname(Reservation reservation) {

        return reservation.getClient().getSurname() != null;
    }

    private static boolean validateClientPhone(Reservation reservation) {

        return reservation.getClient().getPhoneNumber() != null;
    }
}