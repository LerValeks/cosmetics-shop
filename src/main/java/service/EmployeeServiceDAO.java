package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import service.exceptions.EmployeeException;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeServiceDAO {


    Employee add(Employee employee) throws EmployeeException;

    Employee update(Employee employee) throws EmployeeException;

    Employee delete(Employee employee) throws EmployeeException;

    List<Employee> ServiceCategoryEmployees(ServiceCategory serviceCategory, Employee employee) throws EmployeeException;

    List<Employee> FreeEmployees(ServiceCategory serviceCategory, Employee employee, Reservation reservation) throws EmployeeException;

    List<Reservation> reservationByEmployeeInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate) throws EmployeeException;

    List<Reservation> reservationByEmployeeByServiceCategoryInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate, ServiceCategory serviceCategory) throws EmployeeException;


}
