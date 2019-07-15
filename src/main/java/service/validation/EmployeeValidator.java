package service.validation;

import models.Employee;
import models.EmploymentStatus;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;

import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeValidator {

    private final EmployeeDAO employeeDAO;

    public EmployeeValidator(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

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

    public boolean validateIfCurrentEmployeeIsEmployed(Employee employee) throws EmployeeException {

        Set<Employee> allEmployees = employeeDAO.getAllItems();

        return allEmployees.stream()
                .filter(employee1 -> employee1.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .collect(Collectors.toSet())
                .contains(employee);
    }
}