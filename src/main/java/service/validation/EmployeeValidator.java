package service.validation;

import models.Employee;
import models.EmploymentStatus;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;

import java.util.stream.Collectors;

public class EmployeeValidator {

    private static EmployeeDAO employeeDAO;

    //TODO: Robert to advise how making employeedatabase object "Static" will affect the code? In reservation service this isn't static, shall be? Why in general it's prohibited calling statics from non-static context?

    public static boolean validateEmployeeParameters(Employee employee) throws EmployeeException {

        if (employee == null) return false;

        return validateEmployeeNameIsNotNull(employee)
                && validateEmployeeSurnameIsNotNull(employee)
                && validateEmployeePhoneIsNotNull(employee)
                && validateEmployeeserviceCategoryIsNotNull(employee);
    }

    public static boolean validateIfCurrentEmployee(Employee employee) throws EmployeeException {

        return employeeDAO.getAllItems().stream()
                .filter(employee1 -> employee1.getEmploymentStatus() == EmploymentStatus.EMPLOYED)
                .collect(Collectors.toSet()).contains(employee);
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

    private static boolean validateEmployeeserviceCategoryIsNotNull(Employee employee) {

        return employee.getServiceCategory() != null;
    }
}
