package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;
import service.validation.EmployeeValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeServiceImpl {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Employee add(Employee employee) throws EmployeeException {

        if (EmployeeValidator.validateEmployeeParameters(employee)) {
            throw new EmployeeException("Employee object is null or employee parameters are incorrectly initialized");
        }
        return employeeDAO.add(employee);
    }

    public Employee update(Employee employee) throws EmployeeException {

        if (EmployeeValidator.validateEmployeeParameters(employee)) {
            throw new EmployeeException("Employee object is null or employee parameters are incorrectly initialized");
        }
        return employeeDAO.update(employee);
    }

    public Employee delete(Employee employee) throws EmployeeException {

        if (EmployeeValidator.validateEmployeeParameters(employee)) {
            throw new EmployeeException("Employee object is null or employee parameters are incorrectly initialized");
        }
        return employeeDAO.delete(employee);
    }

    public List<Employee> showListOfEmployeesByServiceCategory(ServiceCategory serviceCategory) {

        Set<Employee> allEmployees = employeeDAO.getAllItems();

        return allEmployees.stream()
                .filter(employee -> employee.getServiceCategory().equals(serviceCategory))
                .collect(Collectors.toList());
    }

    public List<Employee> showListOfEmployeesByServiceCategoryFreeAtSpecificTimeOfReservation(ServiceCategory serviceCategory, Reservation reservation) {

        Set<Employee> allEmployees = employeeDAO.getAllItems();

        List<Employee> EmployeesBusy = allEmployees.stream()
                .filter(employee -> employee.getServiceCategory().equals(serviceCategory))
                .map(Employee::getReservations)
                .flatMap(Set::stream)
                .filter(reservation1 -> reservation1.getReservationTime().compareTo(reservation.getReservationTime()) == 0)
                .map(Reservation::getEmployee)
                .collect(Collectors.toList());

        List<Employee> allEmployeesOfThisCategory = showListOfEmployeesByServiceCategory(serviceCategory);

        List<Employee> freeEmployee = new ArrayList<>(allEmployeesOfThisCategory);
        freeEmployee.removeAll(EmployeesBusy);
        return freeEmployee;
    }
}