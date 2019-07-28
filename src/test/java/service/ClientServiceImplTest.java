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
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private ClientDAO clientDAO;

    @Mock
    private EmployeeDAO employeeDAO;

    @InjectMocks
    private ClientServiceImpl clientService;

    private static Set<Client> createClients(Integer numberOfClients) {

        Set<Client> clients = new HashSet<>();

        for (int i = 0; i < numberOfClients; i++) {

            Client client = new Client("Client",
                    Integer.toString(i),
                    "95 55 11" + i);

            clients.add(client);
        }
        return clients;
    }

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

    @Before
    public void setup() {
        this.clientService = new ClientServiceImpl(clientDAO, employeeDAO);
    }

    @Test
    public void addClient_shouldThrowException_whenClientIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client object is null or client parameters are incorrectly initialized");
        clientService.add(null);
    }

    @Test
    public void updateClient_shouldThrowException_whenClientIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client object is null or client parameters are incorrectly initialized");
        clientService.update(null);
    }

    @Test
    public void deleteClient_shouldThrowException_whenClientIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client object is null or client parameters are incorrectly initialized");
        clientService.delete(null);
    }

    @Test
    public void addClient_shouldThrowException_whenClientParameterIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client object is null or client parameters are incorrectly initialized");
        Client client = new Client("Aleks", "Valuyskov", null);
        clientService.add(client);

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client object is null or client parameters are incorrectly initialized");
        Client client2 = new Client(null, null, null);
        clientService.add(client2);
    }

    @Test
    public void updateClient_shouldThrowException_whenClientParameterIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client object is null or client parameters are incorrectly initialized");
        Client client = new Client("Aleks", "Valuyskov", null);
        clientService.update(client);

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client object is null or client parameters are incorrectly initialized");
        Client client2 = new Client(null, null, null);
        clientService.update(client2);
    }

    @Test
    public void addClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {

        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.add(any(Client.class))).thenReturn(client);

        // when
        Client clientTest = clientService.add(client);

        //then
        Mockito.verify(clientDAO, Mockito.times(1)).add(client);
        Assert.assertNotNull(clientTest);
    }

    @Test
    public void updateClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {

        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.update(any(Client.class))).thenReturn(client);

        // when
        Client clientTest = clientService.update(client);

        //then
        Assert.assertNotNull(clientTest);
        Mockito.verify(clientDAO, Mockito.times(1)).update(any(Client.class));
    }

    @Test
    public void deleteClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {

        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.delete(any(Client.class))).thenReturn(client);

        // when
        Client clientTest = clientService.delete(client);

        //then
        assertNotNull(clientTest);
        Mockito.verify(clientDAO, Mockito.times(1)).delete(any(Client.class));
    }

    @Test
    public void validateIfExistingClient() throws ClientException {

        //given
        Set<Client> clients = createClients(7);
        Client client = new Client("Dan",
                "TK",
                "957509");
        clients.add(client);

        //when
        Mockito.when(clientDAO.getAllItems()).thenReturn(clients);
        boolean clientAvailableInDAO = clientService.checkIfExistingClient(client);

        //then
        Assert.assertTrue(clientAvailableInDAO);
        assertEquals(8, clients.size());
    }

    @Test
    public void validateIfExistingClientHasReservationAtTheSameTime() throws ClientException {

        //given
        Set<Employee> employees = createEmployees(5);
        Employee employee = createEmployee(5);

        Client client = new Client("Dan",
                "Tk",
                "365 75 00");

        Reservation existingReservation = new Reservation(ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        Reservation newReservation = new Reservation(ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        employee.getReservations().add(existingReservation);

        employees.add(employee);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        boolean clientWithBookedReservations = clientService.checkIfExistingClientHasReservationAtTheSameTime(newReservation);

        //then
        Assert.assertTrue(clientWithBookedReservations);
    }
}