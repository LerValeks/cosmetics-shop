package service.validation;

import models.Employee;
import models.EmploymentStatus;
import models.Reservation;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;

import java.util.stream.Collectors;

public class EmployeeValidator {

    private static EmployeeDAO employeeDAO;

    //TODO: Robert to advise how making employeedatabase object "Static" will affect the code? In reservation service this isn't static, shall be? Why in general it's prohibited calling statics from non-static context?

    public static boolean validateClientParameters(Employee employee) throws EmployeeException {

        return validateEmployeeName(employee)
                && validateEmployeeSurname(employee)
                && validateEmployeePhone(employee)
                && validateEmployeeserviceCategory(employee);
    }

    public static boolean validateIfCurrentEmployee(Reservation reservation) throws ClientException {

        return employeeDAO.getAllItems().stream()
                .filter(employee -> employee.getEmploymentStatus() == EmploymentStatus.EMPLOYED)
                .collect(Collectors.toSet()).contains(reservation.getEmployee());
    }

    private static boolean validateEmployeeName(Employee employee) {

        return employee.getName() != null;
    }

    private static boolean validateEmployeeSurname(Employee employee) {

        return employee.getSurname() != null;
    }

    private static boolean validateEmployeePhone(Employee employee) {

        return employee.getPhoneNumber() != null;
    }

    private static boolean validateEmployeeserviceCategory(Employee employee) {

        return employee.getServiceCategory() != null;
    }
}
