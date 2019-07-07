package service.validation;

import models.Client;
import models.EmploymentStatus;
import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;

import java.util.stream.Collectors;

public class ClientValidator {

    private static ClientDAO clientDAO;
    private static EmployeeDAO employeeDAO;

    public static boolean validateClientParameters(Client client) throws ClientException {
        validateClientIsNull(client);
         if (validateClientName(client)
                || validateClientSurname(client)
                || validateClientPhone(client)) {
            throw new ClientException("Client attributes are null");
        }
        return true;
    }

    public static boolean validateClientIsNull(Client client) throws ClientException  {
        if (client==null) {
            throw new ClientException("Client is null");
        }
        return true;
    }

    public static boolean validateIfCurrentClient(Client client) {

        return clientDAO.getAllItems().contains(client);
    }

    //TODO: Correct lambda
    public static boolean validateClientHasReservationAtTheSameTime(Reservation reservation) {

        employeeDAO.getAllItems().stream()
                .filter(employee -> employee.getEmploymentStatus() == EmploymentStatus.EMPLOYED)
                .map(employee -> employee.getReservations())
                //       .flatMap(reservations -> reservation.getReservationTime())
                .collect(Collectors.toList());
        return false;
    }

    private static boolean validateClientName(Client client) {

        return client.getName() == null;
    }

    private static boolean validateClientSurname(Client client) {

        return client.getSurname() == null;
    }

    private static boolean validateClientPhone(Client client) {

        return client.getPhoneNumber() == null;
    }
}