package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import service.exceptions.EmployeeException;

import java.util.List;

public interface EmployeeService {

    Employee add(Employee employee) throws EmployeeException;

    Employee update(Employee employee) throws EmployeeException;

    Employee delete(Employee employee) throws EmployeeException;

    List<Employee> showListOfEmployeesByServiceCategory(ServiceCategory serviceCategory);

    List<Employee> showListOfEmployeesByServiceCategoryFreeAtSpecificTimeOfReservation(ServiceCategory serviceCategory, Reservation reservation);
}