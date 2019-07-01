package service;

import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import repository.EmployeeDAO;
import service.Exceptions.EmployeeException;
import service.Validators.EmployeeValidator;

import java.time.LocalDateTime;
import java.util.List;

public class EmployeeServiceDAOImpl implements EmployeeServiceDAO {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceDAOImpl(EmployeeDAO employeeDAO, EmployeeDAO employeeDAO1) throws EmployeeException {
        this.employeeDAO = employeeDAO1;
    }

    @Override
    public Employee add(Employee employee) throws EmployeeException {
        if(!EmployeeValidator.IsEmployeeValid(employee)){
            throw new EmployeeException("Employee data is invalid");
        }

        return employeeDAO.add(employee);
    }

    @Override
    public Employee update(Employee employee)throws EmployeeException
    {
        if(!EmployeeValidator.IsEmployeeValid(employee)){
            throw new EmployeeException("Employee data is invalid");
        }
        return employeeDAO.update(employee);
    }

    @Override
    public Employee delete(Employee employee)throws EmployeeException {
        if(!EmployeeValidator.IsEmployeeValid(employee)){
            throw new EmployeeException("Employee data is invalid");
        }
        return employeeDAO.delete(employee);
    }

    @Override
    public List<Employee> ServiceCategoryEmployees(ServiceCategory serviceCategory, Employee employee) throws EmployeeException{
        if(!EmployeeValidator.IsEmployeeValid(employee)){
            throw new EmployeeException("Employee data is invalid");
        }
        return null;
    }

    @Override
    public List<Employee> FreeEmployees(ServiceCategory serviceCategory, Employee employee, Reservation reservation) throws EmployeeException{
        if(!EmployeeValidator.IsEmployeeValid(employee)){
            throw new EmployeeException("Employee data is invalid");
        }
        return null;
    }

    @Override
    public List<Reservation> reservationByEmployeeInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate) throws EmployeeException{
        return null;
    }

    @Override
    public List<Reservation> reservationByEmployeeByServiceCategoryInSpecificPeriod(Employee employee, LocalDateTime startDate, LocalDateTime endDate, ServiceCategory serviceCategory) throws EmployeeException{
        return null;
    }
}
