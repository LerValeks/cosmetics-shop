package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import repository.EmployeeDAO;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeServiceDAOImpl implements EmployeeServiceDAO {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceDAOImpl(EmployeeDAO employeeDAO, EmployeeDAO employeeDAO1) {
        this.employeeDAO = employeeDAO1;
    }

    @Override
    public Employee add(Employee employee) {
        return null;
    }

    @Override
    public Employee update(Employee employee) {
        return null;
    }

    @Override
    public Employee delete(Employee employee) {
        return null;
    }

    @Override
    public List<Employee> ServiceCategoryEmployees(ServiceCategory serviceCategory, EmployeeDAO employeeDAO) {
        return null;
    }

    @Override
    public List<Employee> FreeEmployees(ServiceCategory serviceCategory, EmployeeDAO employeeDAO, Reservation reservation) {
        return null;
    }

    @Override
    public List<Reservation> reservationByEmployeeInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public List<Reservation> reservationByEmployeeByServiceCategoryInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate, ServiceCategory serviceCategory) {
        return null;
    }
}
