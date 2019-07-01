package service.Validators;

import models.Client;
import models.Employee;
import models.User;
import service.Exceptions.UserException;

public class UserValidator {

    public static boolean IsUserValid(User user) throws UserException {
        if (user == null) {
            throw new UserException("User is null");
        } else {
            return (IsUserServiceCategoryValid(user) & IsUserPhoneNumberValid(user) &
                    IsUserNameValid(user) & IsUserSurnameValid(user));
        }
    }

    public static boolean IsUserNameValid(User user) throws UserException {
        if (user.getName() == null) {
            throw new UserException("Attribute of User is null");
        }
        return true;
    }

    public static boolean IsUserSurnameValid(User user) throws UserException {
        if (user.getSurname() == null) {
            throw new UserException("Attribute of User is null");
        }
        return true;
    }

    public static boolean IsUserPhoneNumberValid(User user) throws UserException {
        if (user.getPhoneNumber() == null) {
            throw new UserException("Attribute of User is null");
        }
        return true;
    }

    public static boolean IsUserServiceCategoryValid(User user) throws UserException{
        if (user instanceof Employee) {
            if (((Employee) user).getServiceCategory() == null) {
                throw new UserException("Attribute of User is null");
            }
        }
        return true;
    }
}
