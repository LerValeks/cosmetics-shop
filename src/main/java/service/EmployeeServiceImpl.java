package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;
import service.validation.EmployeeValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee add(Employee employee) throws EmployeeException {

        if (EmployeeValidator.validateEmployeeParameters(employee)) {
            throw new EmployeeException("Employee object is null or employee parameters are incorrectly initialized");
        }
        return employeeDAO.add(employee);
    }

    @Override
    public Employee update(Employee employee) throws EmployeeException {

        if (EmployeeValidator.validateEmployeeParameters(employee)) {
            throw new EmployeeException("Employee object is null or employee parameters are incorrectly initialized");
        }
        return employeeDAO.update(employee);
    }

    @Override
    public Employee delete(Employee employee) throws EmployeeException {

        if (EmployeeValidator.validateEmployeeParameters(employee)) {
            throw new EmployeeException("Employee object is null or employee parameters are incorrectly initialized");
        }
        return employeeDAO.delete(employee);
    }

    @Override
    public List<Employee> showListOfEmployeesByServiceCategory(ServiceCategory serviceCategory) {

        return employeeDAO.getAllItems().stream()
                .filter(employee -> employee.getServiceCategory().equals(serviceCategory))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> showListOfEmployeesByServiceCategoryFreeAtSpecificTimeOfReservation(ServiceCategory serviceCategory, Reservation reservation) {

        List<Employee> EmployeesBusy = employeeDAO.getAllItems().stream()
                .filter(employee -> employee.getServiceCategory().equals(serviceCategory))
                .map(employee -> employee.getReservations())
                .flatMap(List::stream)
                .filter(reservation1 -> reservation1.getReservationTime().compareTo(reservation.getReservationTime()) == 0)
                .collect(Collectors.mapping(Reservation::getEmployee, Collectors.toList()));

        List<Employee> allEmployeesOfThisCategory = showListOfEmployeesByServiceCategory(serviceCategory);

        List<Employee> freeEmployee = new ArrayList<>(allEmployeesOfThisCategory);
        freeEmployee.removeAll(EmployeesBusy);
        return freeEmployee;
    }
}