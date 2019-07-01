package service.Validators;

import models.Client;

public class ClientValidator {
    public static boolean IsEmployeeValid(Client client) {
        if (client == null) {
            return false;
        } else {
            return (IsClientPhoneNumberValid(client)&
                    IsClientNameValid(client)&IsClientSurnameValid(client));
        }
    }

    public static boolean IsClientNameValid(Client client) {
        if (client.getName() == null) {

        }
        return true;
    }

    public static boolean IsClientSurnameValid(Client employee) {
        if (employee.getSurname() == null) {

        }
        return true;
    }

    public static boolean IsClientPhoneNumberValid(Client client) {
        if (client.getPhoneNumber() == null) {

        }
        return true;
    }


}
