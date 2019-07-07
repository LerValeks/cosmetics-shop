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
         //TODO ValidateDuplicates
       /* if (validateIfCurrentClient(client)) {
            throw new ClientException("Such client already exist in Database");
        } */
        return true;
    }

    public static boolean validateClientIsNull(Client client) throws ClientException  {
        if (client==null) {
            throw new ClientException("Client is null");
        }
        return true;
    }

    //TODO change name to validateIfCurrentClientIsDuplicate
    public static boolean validateIfCurrentClient(Client client)  {

        return clientDAO.getAllItems().contains(client);
    }

    //TODO: Correct lambda
    //TODO: Aleks suggest to transfer to separate class
    public static boolean validateClientHasReservationAtTheSameTime(Reservation reservation) {

        employeeDAO.getAllItems().stream()
                .filter(employee -> employee.getEmploymentStatus() == EmploymentStatus.EMPLOYED)
                .map(employee -> employee.getReservations())
                //       .flatMap(reservations -> reservation.getReservationTime())
                .collect(Collectors.toList());
        return false;
    }

    //TODO change to ValidateClientNameIsNull
    private static boolean validateClientName(Client client) {

        return client.getName() == null;
    }

    //TODO change to ValidateClientSurnameIsNull
    private static boolean validateClientSurname(Client client) {

        return client.getSurname() == null;
    }
    //TODO change to ValidateClienPhoneIsNull
    private static boolean validateClientPhone(Client client) {

        return client.getPhoneNumber() == null;
    }
}