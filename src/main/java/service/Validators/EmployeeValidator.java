package service.Validators;

import models.Employee;

public class EmployeeValidator {

    public static boolean IsEmployeeValid(Employee employee) {
        if (employee == null) {
            return false;
        } else {
            return (IsEmployeeServiceCategoryValid(employee)&IsEmployeePhoneNumberValid(employee)&
                    IsEmployeeNameValid(employee)&IsEmployeeSurnameValid(employee));
        }
    }

    public static boolean IsEmployeeNameValid(Employee employee) {
        if (employee.getName() == null) {

        }
        return true;
    }

    public static boolean IsEmployeeSurnameValid(Employee employee) {
        if (employee.getSurname() == null) {

        }
        return true;
    }

    public static boolean IsEmployeePhoneNumberValid(Employee employee) {
        if (employee.getPhoneNumber() == null) {

        }
        return true;
    }

    public static boolean IsEmployeeServiceCategoryValid(Employee employee) {
        if (employee.getServiceCategory() == null) {

        }
        return true;
    }
}