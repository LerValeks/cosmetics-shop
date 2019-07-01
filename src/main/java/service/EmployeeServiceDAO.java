package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import repository.EmployeeDAO;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeServiceDAO {


    Employee add(Employee employee);

    Employee update(Employee employee);

    Employee delete(Employee employee);

    List<Employee> ServiceCategoryEmployees(ServiceCategory serviceCategory, EmployeeDAO employeeDAO);

    List<Employee> FreeEmployees(ServiceCategory serviceCategory, EmployeeDAO employeeDAO, Reservation reservation);

    List<Reservation> reservationByEmployeeInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate);

    List<Reservation> reservationByEmployeeByServiceCategoryInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate, ServiceCategory serviceCategory);


}
