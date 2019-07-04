package validation;

import models.EmploymentStatus;
import models.Reservation;
import repository.ClientDatabase;
import repository.EmployeeDatabase;

import java.util.stream.Collectors;

public class ClientValidator {

    private static ClientDatabase clientDatabase;
    private static EmployeeDatabase employeeDatabase;

    public static boolean validateClientParameters(Reservation reservation) {

        return validateClientName(reservation)
                && validateClientSurname(reservation)
                && validateClientPhone(reservation);
    }

    public static boolean validateIfCurrentClient(Reservation reservation) {

        return clientDatabase.getAllItemsFromDatabase().contains(reservation.getClient());
    }

    public static boolean validateClientHasReservationAtTheSameTime(Reservation reservation) {
//TODO: Ammend lambda
        employeeDatabase.getAllItemsFromDatabase().stream()
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