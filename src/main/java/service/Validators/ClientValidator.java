package service.Validators;

import models.Client;
import service.Exceptions.ClientException;

public class ClientValidator {
    public static boolean IsClientValid(Client client) throws ClientException {
        if (client == null) {
            throw new ClientException("Client is null");
        } else {
            return (IsClientPhoneNumberValid(client)&
                    IsClientNameValid(client)&IsClientSurnameValid(client));
        }
    }

    public static boolean IsClientNameValid(Client client) throws ClientException  {
        if (client.getName() == null) {
            throw new ClientException("Attribute of Client is null");
        }
        return true;
    }

    public static boolean IsClientSurnameValid(Client employee) throws ClientException{
        if (employee.getSurname() == null) {
            throw new ClientException("Attribute of Client is null");
        }
        return true;
    }

    public static boolean IsClientPhoneNumberValid(Client client) throws ClientException{
        if (client.getPhoneNumber() == null) {
            throw new ClientException("Attribute of Client is null");
        }
        return true;
    }


}
