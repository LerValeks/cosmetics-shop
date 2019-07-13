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

        validateClientIsNotNull(client);
        validateClientNameIsNotNull(client);
        validateClientSurnameIsNotNull(client);
        validateClientPhoneIsNotNull(client);
        return true;
    }

    public static boolean validateClientIsNotNull(Client client) throws ClientException {
        if (client == null) {
            throw new ClientException("Client is null");
        }
        return true;
    }

    private static boolean validateClientNameIsNotNull(Client client) throws ClientException {
        if (client.getName() == null) {
            throw new ClientException("Client attribute is null");
        }
        return true;
    }

    private static boolean validateClientSurnameIsNotNull(Client client) throws ClientException {
        if (client.getSurname() == null) {
            throw new ClientException("Client attribute is null");
        }
        return true;
    }


    private static boolean validateClientPhoneIsNotNull(Client client) throws ClientException {
        if (client.getPhoneNumber() == null) {
            throw new ClientException("Client attribute is null");
        }
        return true;
    }
}