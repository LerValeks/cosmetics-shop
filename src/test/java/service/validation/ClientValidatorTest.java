package service.validation;

import models.Client;
import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class ClientValidatorTest {

    @Mock
    private ClientDAO clientDAO;

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private ClientValidator clientValidator;

    private static Client createClient() {

        return new Client("Alfa", "Client", "777 77 77");
    }

    private static Set<Client> createClients() {

        Set<Client> clients = new HashSet<>();

        for (int i = 0; i < 5; i++) {

            Client client = createClient();
            client.setPhoneNumber(Integer.toString(i));
            clients.add(client);
        }
        return clients;
    }

    private static Employee createEmployee() {

        return new Employee("Alfa", "Employee", "937 99 92", ServiceCategory.HAIRCUT);
    }

    private static Set<Employee> createEmployees() {

        Set<Employee> employees = new HashSet<>();

        for (int i = 0; i < 5; i++) {

            Employee employee = createEmployee();
            employee.setPhoneNumber(i + "e");
            Set<Reservation> reservations = createReservations();
            employee.getReservations().addAll(reservations);

            employees.add(employee);
        }
        return employees;
    }

    private static Reservation createReservation(Employee employee, Client client) {

        return new Reservation(ServiceCategory.HAIRCUT, employee, client, LocalDateTime.now());
    }

    private static Set<Reservation> createReservations() {

        Set<Reservation> reservations = new HashSet<>();

        for (int i = 0; i < 5; i++) {

            Reservation reservation = createReservation(createEmployee(), createClient());
            reservation.getEmployee().setPhoneNumber(Integer.toString(i));
            reservation.getClient().setPhoneNumber(Integer.toString(i));
            reservations.add(reservation);
        }
        return reservations;
    }

    @Test
    public void validateClientParameters_shouldReturnTrue_IfClientIsNotNullAndAllParametersAreNotNull() throws ClientException {

        //given
        Client client = createClient();

        //when
        boolean clientValidation = ClientValidator.validateClientParameters(client);

        //then
        Assert.assertNotNull(client);
        assertTrue(clientValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalse_IfClientIsNull() throws ClientException {

        //given
        Client client = null;

        //when
        boolean clientValidation = ClientValidator.validateClientParameters(client);

        //then
        assertFalse(clientValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalse_IfClientHasOneNullParameter() throws ClientException {

        //given
        Client client1 = createClient();
        client1.setName(null);

        Client client2 = createClient();
        client2.setSurname(null);

        Client client3 = createClient();
        client3.setPhoneNumber(null);

        //when
        boolean clientValidation1 = ClientValidator.validateClientParameters(client1);
        boolean clientValidation2 = ClientValidator.validateClientParameters(client2);
        boolean clientValidation3 = ClientValidator.validateClientParameters(client3);

        //then
        Assert.assertNotNull(client1);
        assertFalse(clientValidation1);

        Assert.assertNotNull(client2);
        assertFalse(clientValidation2);

        Assert.assertNotNull(client3);
        assertFalse(clientValidation3);
    }

    @Test
    public void validateIfExistingClient_ShouldReturnTrue_IfClientExistsInClientDAO() throws ClientException {

        //given
        Set<Client> clients = createClients();
        Client client = createClient();
        clients.add(client);

        //when
        Mockito.when(clientDAO.getAllItems()).thenReturn(clients);
        boolean clientAvailableInDAO = clientValidator.validateIfExistingClient(client);

        //then
        Assert.assertTrue(clientAvailableInDAO);
        assertEquals(6, clients.size());
    }

    @Test
    public void validateIfExistingClientHasReservationAtTheSameTime_shouldReturnTrue_IfSameTimeExists() throws ClientException {

        //given
        Set<Employee> allEmployees = createEmployees();

        Client client = createClient();
        client.setName("Checked");
        client.setSurname("Client");
        client.setPhoneNumber("3658990");

        Employee employeeOne = createEmployee();
        employeeOne.setName("First");
        employeeOne.setSurname("Employee");

        Employee employeeTwo = createEmployee();
        employeeTwo.setName("Second");
        employeeTwo.setSurname("Employee");


        Reservation existingReservation = createReservation(employeeOne, client);
        existingReservation.setReservationTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        Reservation newReservation = createReservation(employeeTwo, client);
        newReservation.setReservationTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        employeeOne.getReservations().add(existingReservation);

        allEmployees.add(employeeOne);


        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(allEmployees);
        boolean clientTimeCheck = clientValidator.validateIfExistingClientHasReservationAtTheSameTime(newReservation);

        //then
        Assert.assertNotNull(clientTimeCheck);
        Assert.assertTrue(clientTimeCheck);
    }

    @Test
    public void validateIfExistingClientHasReservationAtTheSameTime_shouldReturnFalse_IfSameTimeDoesNotExist() throws ClientException {

        //given
        Set<Employee> allEmployees = createEmployees();

        Client client = createClient();
        client.setName("Checked");
        client.setSurname("Client");
        client.setPhoneNumber("3658990");

        Employee employeeOne = createEmployee();
        employeeOne.setName("First");
        employeeOne.setSurname("Employee");

        Employee employeeTwo = createEmployee();
        employeeTwo.setName("Second");
        employeeTwo.setSurname("Employee");


        Reservation existingReservation = createReservation(employeeOne, client);
        existingReservation.setReservationTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        Reservation newReservation = createReservation(employeeTwo, client);
        newReservation.setReservationTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 31)));

        employeeOne.getReservations().add(existingReservation);

        allEmployees.add(employeeOne);


        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(allEmployees);
        boolean clientTimeCheck = clientValidator.validateIfExistingClientHasReservationAtTheSameTime(newReservation);

        //then
        Assert.assertNotNull(clientTimeCheck);
        Assert.assertFalse(clientTimeCheck);
    }
}