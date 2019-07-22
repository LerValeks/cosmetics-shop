package service.validation;

import models.Client;
import service.exceptions.ClientException;

public class ClientValidator {

    public static boolean validateClientParameters(Client client) throws ClientException {

        return validateClientIsNotNull(client)
                && validateClientNameIsNotNull(client)
                && validateClientSurnameIsNotNull(client)
                && validateClientPhoneIsNotNull(client);
    }

    private static boolean validateClientIsNotNull(Client client) {

        return client != null;
    }

    private static boolean validateClientNameIsNotNull(Client client) {

        return client.getName() != null;
    }

    private static boolean validateClientSurnameIsNotNull(Client client) {

        return client.getSurname() != null;
    }

    private static boolean validateClientPhoneIsNotNull(Client client) {

        return client.getPhoneNumber() != null;
    }
}