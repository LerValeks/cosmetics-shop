package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;
import service.validation.EmployeeValidator;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeServiceDAOImpl implements EmployeeServiceDAO {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceDAOImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee add(Employee employee)  throws EmployeeException {

        EmployeeValidator.validateEmployeeParameters(employee);
        return employeeDAO.add(employee);
    }

    @Override
    public Employee update(Employee employee) throws EmployeeException {

        EmployeeValidator.validateEmployeeParameters(employee);
        return employeeDAO.update(employee);
    }

    @Override
    public Employee delete(Employee employee) throws EmployeeException {

        EmployeeValidator.validateEmployeeParameters(employee);
        return employeeDAO.delete(employee);
    }

    @Override
    public List<Employee> serviceCategoryEmployees(ServiceCategory serviceCategory, Employee employee) throws EmployeeException {

        EmployeeValidator.validateEmployeeParameters(employee);
        return null;
    }

    @Override
    public List<Employee> freeEmployees(ServiceCategory serviceCategory, Employee employee, Reservation reservation) throws EmployeeException {

        EmployeeValidator.validateEmployeeParameters(employee);
        return null;
    }

    @Override
    public List<Reservation> reservationByEmployeeInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate) throws EmployeeException {
        return null;
    }

    @Override
    public List<Reservation> reservationByEmployeeByServiceCategoryInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate, ServiceCategory serviceCategory) throws EmployeeException {
        return null;
    }
}