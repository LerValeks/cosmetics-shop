package service;

import models.Employee;
import models.ServiceCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceDAOImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private EmployeeServiceDAOImpl employeeServiceDAO;

    @Mock
    private EmployeeDAO employeeDAO;

    @Before
    public void setup() {
        this.employeeServiceDAO = new EmployeeServiceDAOImpl(employeeDAO);
    }

    @Test
    public void addEmployee_shouldThrowException_whenEmployeeIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee is null");
        employeeServiceDAO.add(null);
    }

    @Test
    public void deleteEmployee_shouldThrowException_whenEmployeeIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee is null");
        employeeServiceDAO.delete(null);
    }

    @Test
    public void updateEmployee_shouldThrowException_whenEmployeeIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee is null");
        employeeServiceDAO.update(null);
    }

    @Test
    public void addEmployee_shouldThrowException_whenEmployeeParameterIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee attribute is null");
        Employee employee = new Employee("Aleks", "Valuyskov", null, ServiceCategory.HAIRCUT);
        employeeServiceDAO.add(employee);

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee attribute is null");
        Employee employee2 = new Employee(null, null, null, null);
        employeeServiceDAO.add(employee2);
    }

    @Test
    public void updateClient_shouldThrowException_whenEmployeeParameterIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee attribute is null");
        Employee employee = new Employee("Aleks", "Valuyskov", null, ServiceCategory.HAIRCUT);
        employeeServiceDAO.update(employee);

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee attribute is null");
        Employee employee2 = new Employee(null, null, null, ServiceCategory.HAIRCUT);
        employeeServiceDAO.update(employee2);
    }

    @Test
    public void addClient_whenEmployeeIsCorrect_shouldAddEmployee() throws EmployeeException {

        //given
        Employee employee = new Employee("Aleks", "Valuyskov", "+3459435234", ServiceCategory.HAIRCUT);
        Mockito.when(employeeDAO.add(any(Employee.class))).thenReturn(employee);

        // when
        Employee employeeTest = employeeServiceDAO.add(employee);

        //then
        Assert.assertNotNull(employeeTest);
        Mockito.verify(employeeDAO, Mockito.times(1)).add(employee);
    }

    @Test
    public void updateClient_whenClientIsCorrect_shouldReturnClient() throws EmployeeException {

        //given
        Employee employee = new Employee("Aleks", "Valuyskov", "+3459435234", ServiceCategory.HAIRCUT);
        Mockito.when(employeeDAO.update(any(Employee.class))).thenReturn(employee);

        // when
        Employee employee2 = employeeServiceDAO.update(employee);

        //then
        assertNotNull(employee2);
        Mockito.verify(employeeDAO, Mockito.times(1)).update(any(Employee.class));
    }

    @Test
    public void deleteClient_whenClientIsCorrect_shouldReturnClient() throws EmployeeException {

        //given
        Employee employee = new Employee("Aleks", "Valuyskov", "+3459435234", ServiceCategory.HAIRCUT);
        Mockito.when(employeeDAO.delete(any(Employee.class))).thenReturn(employee);

        // when
        Employee employee2 = employeeServiceDAO.delete(employee);

        //then
        assertNotNull(employee2);
        Mockito.verify(employeeDAO, Mockito.times(1)).delete(any(Employee.class));
    }


}