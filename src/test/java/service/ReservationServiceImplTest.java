package service;

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
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Mock
    EmployeeDAO employeeDAO;

    @Mock
    ClientDAO clientDAO;

    @InjectMocks
    ReservationServiceImpl reservationService;

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

    @Test
    //TODO: Robert to advise how method checkIfExistingClient works and why error is not being thrown?
    public void makeReservation() throws ClientException, ReservationException, EmployeeException {

        //given
        Set<Employee> employees = createEmployees(5);

        Employee employee = new Employee("Al",
                "Val",
                "365 75 09",
                ServiceCategory.HAIRCUT);

        Client client = new Client("Dan",
                "TK",
                "957509");

        Reservation reservation = new Reservation(ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.now());

        employees.add(employee);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        Mockito.when(employeeDAO.getItem(Mockito.anyString())).thenReturn(employee);
        Reservation bookedReservation = reservationService.makeReservation(reservation);

        //then
        Assert.assertEquals(reservation, bookedReservation);
    }

    @Test
    public void showActiveReservations() {

        //given
        Set<Employee> employees = createEmployees(5);
        Employee employee = createEmployee(5);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        Set<Reservation> activeReservations = reservationService.showActiveReservations("937 99 91");

        //then
        Assert.assertEquals(5, activeReservations.size());
    }

    @Test
    public void showReservationsForSpecificTimePeriod() throws ClientException {

        //given
        Set<Employee> employees = createEmployees(5);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        Set<Reservation> activeReservations = reservationService.showReservationsForSpecificTimePeriod("937 99 91",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(5));

        //then
        Assert.assertEquals(3, activeReservations.size());
    }

    @Test
    public void cancelClientReservation() throws ReservationException {

        //given
        Client client = new Client("Al",
                "Val",
                "957509");
        Employee employee = createEmployee(5);

        Reservation reservation = new Reservation(
                ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        employee.getReservations().add(reservation);

        //when
        Mockito.when(employeeDAO.getItem(Mockito.anyString())).thenReturn(employee);
        boolean cancelledReservation = reservationService.cancelClientReservation(reservation);

        //then
        Assert.assertTrue(cancelledReservation);
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
        boolean clientWithBookedReservations = reservationService.checkIfExistingClientHasReservationAtTheSameTime(newReservation);

        //then
        Assert.assertTrue(clientWithBookedReservations);
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
        boolean clientAvailableInDAO = reservationService.checkIfExistingClient(client);

        //then
        Assert.assertTrue(clientAvailableInDAO);
        assertEquals(8, clients.size());
    }

    @Test
    //TODO: Robert to advise why cannot do .setEmploymentstatus on employees.add(employee.setsmth...)?
    public void validateIfCurrentEmployeeIsEmployed() throws EmployeeException {

        //given
        Set<Employee> employees = createEmployees(5);
        Employee employee = createEmployee(5);
        employees.add(employee);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        boolean employeeAvailableInDAO = reservationService.checkIfCurrentEmployeeIsEmployed(employee);

        //then
        Assert.assertTrue(employeeAvailableInDAO);
        assertEquals(6, employees.size());
    }

    @Test
    public void checkIfReservationTimeIsBooked() throws ReservationException {

        //given
        Client client = new Client("Al",
                "Val",
                "957509");
        Employee employee = createEmployee(5);

        Reservation newReservation = new Reservation(ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.now());

        Reservation existingReservation = new Reservation(ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.now());

        existingReservation.setReservationTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        employee.getReservations().add(existingReservation);

        //when
        Mockito.when(employeeDAO.getItem(Mockito.anyString())).thenReturn(employee);
        boolean availableTime = reservationService.checkIfReservationTimeIsAlreadyTaken(newReservation);

        //then
        Assert.assertFalse(availableTime);
    }
}