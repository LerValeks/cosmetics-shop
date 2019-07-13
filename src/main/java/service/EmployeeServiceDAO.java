package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import service.exceptions.EmployeeException;

import java.time.LocalDateTime;
import java.util.List;

//TODO: Robert to advise if this can be merged into 1 interface and used both for client and employee
public interface EmployeeServiceDAO {

    Employee add(Employee employee) throws EmployeeException;

    Employee update(Employee employee) throws EmployeeException;

    Employee delete(Employee employee) throws EmployeeException;

    List<Employee> showListOfEmployeesByServiceCategory(ServiceCategory serviceCategory);

    List<Employee> showListOfEmployeesByServiceCategoryFreeAtSpecificTimeOfReservation(ServiceCategory serviceCategory, Reservation reservation);
   }