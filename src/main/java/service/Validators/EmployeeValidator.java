package service.Validators;

import models.Employee;
import service.Exceptions.EmployeeException;

public class EmployeeValidator {

    public static boolean IsEmployeeValid(Employee employee) throws EmployeeException {
        if (employee == null) {
            throw new EmployeeException ("Employee is null");
        } else {
            return (IsEmployeeServiceCategoryValid(employee)&IsEmployeePhoneNumberValid(employee)&
                    IsEmployeeNameValid(employee)&IsEmployeeSurnameValid(employee));
        }
    }

    public static boolean IsEmployeeNameValid(Employee employee) throws EmployeeException {
        if (employee.getName() == null) {
            throw new EmployeeException ("Attribute of Employee is null");
        }
        return true;
    }

    public static boolean IsEmployeeSurnameValid(Employee employee) throws EmployeeException {
        if (employee.getSurname() == null) {
            throw new EmployeeException ("Attribute of Employee is null");
        }
        return true;
    }

    public static boolean IsEmployeePhoneNumberValid(Employee employee) throws EmployeeException {
        if (employee.getPhoneNumber() == null) {
            throw new EmployeeException ("Attribute of Employee is null");
        }
        return true;
    }

    public static boolean IsEmployeeServiceCategoryValid(Employee employee)throws EmployeeException {
        if (employee.getServiceCategory() == null) {
            throw new EmployeeException ("Attribute of Employee is null");
        }
        return true;
    }
}