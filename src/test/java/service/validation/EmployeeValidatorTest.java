package service.validation;

import models.Employee;
import models.EmploymentStatus;
import models.ServiceCategory;
import org.junit.Assert;
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

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class EmployeeValidatorTest {

    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeValidator employeeValidator;

    private static Employee createEmployee() {

        return new Employee("Alfa", "Employee", "937 99 92", ServiceCategory.HAIRCUT);
    }

    private static Set<Employee> createEmployees() {

        Set<Employee> employees = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            Employee employee = createEmployee();
            employee.setPhoneNumber(Integer.toString(i));
            employees.add(employee);
        }
        return employees;
    }

    @Test
    public void validateEmployeeParameters_shouldReturnTrueIfEmployeeIsNotNullAndAllParametersAreNotNull() throws EmployeeException {

        //given
        Employee employee = createEmployee();

        //when
        boolean employeeValidation = EmployeeValidator.validateEmployeeParameters(employee);

        //then
        Assert.assertNotNull(employee);
        assertTrue(employeeValidation);
    }

    @Test
    public void validateEmployeeParameters_shouldReturnFalseIfEmployeeIsNull() throws EmployeeException {

        //given
        Employee employee = null;

        //when
        boolean employeeValidation = EmployeeValidator.validateEmployeeParameters(employee);

        //then
        assertFalse(employeeValidation);
    }

    @Test
    public void validateEmployeeParameters_shouldReturnFalseIfEmployeeHasOneNullParameter() throws EmployeeException {

        //given
        Employee employee1 = createEmployee();
        employee1.setName(null);

        Employee employee2 = createEmployee();
        employee2.setSurname(null);

        Employee employee3 = createEmployee();
        employee3.setPhoneNumber(null);

        Employee employee4 = createEmployee();
        employee4.setServiceCategory(null);

        //when
        boolean employeeValidation1 = EmployeeValidator.validateEmployeeParameters(employee1);
        boolean employeeValidation2 = EmployeeValidator.validateEmployeeParameters(employee2);
        boolean employeeValidation3 = EmployeeValidator.validateEmployeeParameters(employee3);
        boolean employeeValidation4 = EmployeeValidator.validateEmployeeParameters(employee4);

        //then
        Assert.assertNotNull(employee1);
        assertFalse(employeeValidation1);

        Assert.assertNotNull(employee2);
        assertFalse(employeeValidation2);

        Assert.assertNotNull(employee3);
        assertFalse(employeeValidation3);

        Assert.assertNotNull(employee4);
        assertFalse(employeeValidation4);
    }

    //TODO: Robert to advise why cannot do .setEmploymentstatus on employees.add(employee.setsmth...)?
    @Test
    public void validateIfCurrentEmployeeIsEmployed_shouldReturnTrue_IfEmployeeIsEmployed() throws EmployeeException {

        //given
        Set<Employee> employees = createEmployees();
        Employee employee = createEmployee();
        employees.add(employee);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        boolean employeeAvailableInDAO = employeeValidator.validateIfCurrentEmployeeIsEmployed(employee);

        //then
        Assert.assertTrue(employeeAvailableInDAO);
        assertEquals(6, employees.size());
    }

    @Test
    public void validateIfCurrentEmployeeIsEmployed_shouldReturnFalse_IfEmployeeIsNotEmployed() throws EmployeeException {

        //given
        Set<Employee> employees = createEmployees();
        Employee employee = createEmployee();
        employee.setEmploymentStatus(EmploymentStatus.UNEMPLOYED);
        employees.add(employee);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        boolean employeeAvailableInDAO = employeeValidator.validateIfCurrentEmployeeIsEmployed(employee);

        //then
        Assert.assertFalse(employeeAvailableInDAO);
        assertEquals(6, employees.size());
    }
}