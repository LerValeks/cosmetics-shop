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
        validateEmployeeIsNull(employee);
        validateEmployeeNameIsNotNull(employee);
        validateEmployeeSurnameIsNotNull(employee);
        validateEmployeePhoneIsNotNull(employee);
        validateEmployeeserviceCategoryIsNotNull(employee);
        return true;
    }

    public static boolean validateEmployeeIsNull(Employee employee) throws EmployeeException {
        if (employee == null) {
            throw new EmployeeException("Employee is null");
        }
        return true;
    }


    public static boolean validateIfCurrentEmployeeEmployed(Employee employee) throws EmployeeException {
        if (!employeeDAO.getAllItems().stream()
                .filter(employee1 -> employee1.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .collect(Collectors.toSet())
                .contains(employee)) {
            throw new EmployeeException("Employee is not employed");
        }
        return true;
    }

    private static boolean validateEmployeeNameIsNotNull(Employee employee) throws EmployeeException {
        if (employee.getName() == null) {
            throw new EmployeeException("Employee attribute is null");
        }
        return true;
    }

    private static boolean validateEmployeeSurnameIsNotNull(Employee employee) throws EmployeeException {
        if (employee.getSurname() == null) {
            throw new EmployeeException("Employee attribute is null");
        }
        return true;
    }

    private static boolean validateEmployeePhoneIsNotNull(Employee employee) throws EmployeeException {
        if (employee.getPhoneNumber() == null) {
            throw new EmployeeException("Employee attribute is null");
        }
        return true;
    }

    private static boolean validateEmployeeserviceCategoryIsNotNull(Employee employee) throws EmployeeException {
        if (employee.getServiceCategory() == null) {
            throw new EmployeeException("Employee attribute is null");
        }
        return true;
    }
}
