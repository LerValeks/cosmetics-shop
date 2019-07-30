package service;

import models.Client;
import models.Employee;
import models.Reservation;
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

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private static Employee createEmployee(Integer numberOfReservations) {

        Client client = new Client("Client",
                "Test",
                "937 99 91");

        Employee employee = new Employee("Employee",
                "Test",
                "937 99 92",
                ServiceCategory.HAIRCUT);

        for (int i = 0; i < numberOfReservations; i++) {

            Reservation reservation = new Reservation(ServiceCategory.HAIRCUT,
                    employee,
                    client,
                    LocalDateTime.now().plusDays(i));
            employee.getReservations().add(reservation);
        }
        return employee;
    }

    private static Set<Employee> createEmployees(Integer numberOfEmployees) {

        Set<Employee> employees = new HashSet<>();

        for (int i = 0; i < numberOfEmployees; i++) {

            Client client = new Client("Client",
                    Integer.toString(i),
                    "937 99 9" + i);

            Employee employee = new Employee("Employee",
                    Integer.toString(i),
                    "937 99 9" + i,
                    ServiceCategory.HAIRCUT);

            for (int j = 0; j < numberOfEmployees; j++) {

                Reservation reservation = new Reservation(ServiceCategory.HAIRCUT,
                        employee,
                        client,
                        LocalDateTime.now().plusDays(j));

                employee.getReservations().add(reservation);
            }
            employees.add(employee);
        }
        return employees;
    }

    //TODO: Aleks to advise why using @Before?
    @Before
    public void setup() {
        this.employeeService = new EmployeeServiceImpl(employeeDAO);
    }

    @Test
    public void addEmployee_shouldThrowException_whenEmployeeIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee object is null or employee parameters are incorrectly initialized");
        employeeService.add(null);
    }

    @Test
    public void updateEmployee_shouldThrowException_whenEmployeeIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee object is null or employee parameters are incorrectly initialized");
        employeeService.update(null);
    }

    @Test
    public void deleteEmployee_shouldThrowException_whenEmployeeIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee object is null or employee parameters are incorrectly initialized");
        employeeService.delete(null);
    }

    @Test
    public void addEmployee_shouldThrowException_whenEmployeeParameterIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee object is null or employee parameters are incorrectly initialized");
        Employee employee = new Employee("Aleks", "Valuyskov", null, ServiceCategory.HAIRCUT);
        employeeService.add(employee);

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee object is null or employee parameters are incorrectly initialized");
        Employee employee2 = new Employee(null, null, null, null);
        employeeService.add(employee2);
    }

    @Test
    public void updateEmployee_shouldThrowException_whenEmployeeParameterIsNull() throws EmployeeException {

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee object is null or employee parameters are incorrectly initialized");
        Employee employee = new Employee("Aleks", "Valuyskov", null, ServiceCategory.HAIRCUT);
        employeeService.update(employee);

        thrown.expect(EmployeeException.class);
        thrown.expectMessage("Employee object is null or employee parameters are incorrectly initialized");
        Employee employee2 = new Employee(null, null, null, ServiceCategory.HAIRCUT);
        employeeService.update(employee2);
    }

    @Test
    public void addEmployee_whenEmployeeIsCorrect_shouldAddEmployee() throws EmployeeException {

        //given
        Employee employee = new Employee("Aleks", "Valuyskov", "+3459435234", ServiceCategory.HAIRCUT);
        Mockito.when(employeeDAO.add(any(Employee.class))).thenReturn(employee);

        // when
        Employee employeeTest = employeeService.add(employee);

        //then
        Assert.assertNotNull(employeeTest);
        Mockito.verify(employeeDAO, Mockito.times(1)).add(employee);
    }

    @Test
    public void updateEmployee_whenClientIsCorrect_shouldReturnEmployee() throws EmployeeException {

        //given
        Employee employee = new Employee("Aleks", "Valuyskov", "+3459435234", ServiceCategory.HAIRCUT);
        Mockito.when(employeeDAO.update(any(Employee.class))).thenReturn(employee);

        // when
        Employee employee2 = employeeService.update(employee);

        //then
        assertNotNull(employee2);
        Mockito.verify(employeeDAO, Mockito.times(1)).update(any(Employee.class));
    }

    @Test
    public void deleteEmployee_whenEmployeeIsCorrect_shouldReturnEmployee() throws EmployeeException {

        //given
        Employee employee = new Employee("Aleks", "Valuyskov", "+3459435234", ServiceCategory.HAIRCUT);
        Mockito.when(employeeDAO.delete(any(Employee.class))).thenReturn(employee);

        // when
        Employee employee2 = employeeService.delete(employee);

        //then
        assertNotNull(employee2);
        Mockito.verify(employeeDAO, Mockito.times(1)).delete(any(Employee.class));
    }

    @Test
    //TODO: Robert to advise why cannot do .setEmploymentstatus on employees.add(employee.setsmth...)?
    public void checkIfCurrentEmployeeIsEmployed() throws EmployeeException {

        //given
        Set<Employee> employees = createEmployees(5);
        Employee employee = createEmployee(5);
        employees.add(employee);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        boolean employeeAvailableInDAO = employeeService.checkIfCurrentEmployeeIsEmployed(employee);

        //then
        Assert.assertTrue(employeeAvailableInDAO);
        assertEquals(6, employees.size());
    }

    @Test
    public void showListOfEmployeesByServiceCategory() {
    }

    @Test
    public void showListOfEmployeesByServiceCategoryFreeAtSpecificTimeOfReservation() {
    }
}