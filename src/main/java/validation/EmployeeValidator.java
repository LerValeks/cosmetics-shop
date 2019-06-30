package validation;

import models.Employee;
import models.Reservation;

import java.util.Set;

public class EmployeeValidator {

    private static boolean validateIfAvailableEmployee(Reservation reservation, Set<Employee> availableEmployees) {

        Employee employee = reservation.getEmployee();

        return availableEmployees.contains(employee);
    }

    private static boolean validateIfAvailableReservatopTime(Reservation reservation, Employee availableEmployees) {
        return false;
    }
}