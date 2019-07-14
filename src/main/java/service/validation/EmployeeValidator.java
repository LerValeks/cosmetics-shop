package service.validation;

import models.Employee;
import service.exceptions.EmployeeException;

public class EmployeeValidator {

    public static boolean validateEmployeeParameters(Employee employee) throws EmployeeException {

        return validateEmployeeIsNotNull(employee)
                && validateEmployeeNameIsNotNull(employee)
                && validateEmployeeSurnameIsNotNull(employee)
                && validateEmployeePhoneIsNotNull(employee)
                && validateEmployeeServiceCategoryIsNotNull(employee);
    }

    private static boolean validateEmployeeIsNotNull(Employee employee) {

        return employee != null;
    }

    private static boolean validateEmployeeNameIsNotNull(Employee employee) {

        return employee.getName() != null;
    }

    private static boolean validateEmployeeSurnameIsNotNull(Employee employee) {

        return employee.getSurname() != null;
    }

    private static boolean validateEmployeePhoneIsNotNull(Employee employee) {

        return employee.getPhoneNumber() != null;
    }

    private static boolean validateEmployeeServiceCategoryIsNotNull(Employee employee) {

        return employee.getServiceCategory() != null;
    }
}