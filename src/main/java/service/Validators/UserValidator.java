package service.Validators;

import models.Client;
import models.Employee;
import models.User;

public class UserValidator {
    public static boolean IsEmployeeValid(User user) {
        if (user == null) {
            return false;
        } else {
            return (IsUserServiceCategoryValid(user) & IsUserPhoneNumberValid(user) &
                    IsUserNameValid(user) & IsUserSurnameValid(user));
        }
    }

    public static boolean IsUserNameValid(User user) {
        if (user.getName() == null) {

        }
        return true;
    }

    public static boolean IsUserSurnameValid(User user) {
        if (user.getSurname() == null) {

        }
        return true;
    }

    public static boolean IsUserPhoneNumberValid(User user) {
        if (user.getPhoneNumber() == null) {

        }
        return true;
    }

    public static boolean IsUserServiceCategoryValid(User user) {
        if (user instanceof Employee) {
            if (((Employee) user).getServiceCategory() == null) {

            }

        }
        return true;
    }
}
