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
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceImplTest {

    @Mock
    EmployeeDAO employeeDAO;

    @InjectMocks
    ReservationServiceImpl reservationService;

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

    @Test
    //TODO: Robert to advise how method checkIfExistingClient works and why error is not being thrown?
    //TODO: Robert to help on mocking
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
    public void showActiveReservations_shouldReturnAllReservationsWithActiveStatusInTheFuture() {

        //given
        Set<Employee> employees = createEmployees(5);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        Set<Reservation> activeReservations = reservationService.showActiveReservations("937 99 91");

        //then
        Assert.assertEquals(5, activeReservations.size());
    }

    @Test
    public void showReservationsForSpecificTimePeriod_shouldReturnAllReservationsForTimePeriod() {

        //given
        Set<Employee> employees = createEmployees(5);
        LocalDate from = LocalDate.now().plusDays(2);
        LocalDate to = LocalDate.now().plusDays(5);

        //when
        Mockito.when(employeeDAO.getAllItems()).thenReturn(employees);
        Set<Reservation> activeReservations = reservationService.showAllReservationsForSpecificTimePeriod(
                "937 99 91",
                from,
                to);

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
                LocalDateTime.now());

        employee.getReservations().add(reservation);

        //when
        Mockito.when(employeeDAO.getItem(Mockito.anyString())).thenReturn(employee);
        boolean cancelledReservation = reservationService.cancelClientReservation(reservation);

        //then
        Assert.assertTrue(cancelledReservation);
    }

    @Test
    public void checkIfReservationTimeIsBooked_shouldReturnTrue_whenSameReservationTimeExists() throws ReservationException {

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

        employee.getReservations().add(existingReservation);

        //when
        Mockito.when(employeeDAO.getItem(Mockito.anyString())).thenReturn(employee);
        boolean availableTime = reservationService.checkIfReservationTimeIsBooked(newReservation);

        //then
        Assert.assertTrue(availableTime);
    }

    @Test
    public void checkIfReservationTimeIsBooked_shouldReturnFalse_whenNoSameReservationTimeExists() throws ReservationException {

        //given
        Client client = new Client("Al",
                "Val",
                "957509");
        Employee employee = createEmployee(5);

        Reservation newReservation = new Reservation(ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        Reservation existingReservation = new Reservation(ServiceCategory.HAIRCUT,
                employee,
                client,
                LocalDateTime.now());

        employee.getReservations().add(existingReservation);

        //when
        Mockito.when(employeeDAO.getItem(Mockito.anyString())).thenReturn(employee);
        boolean availableTime = reservationService.checkIfReservationTimeIsBooked(newReservation);

        //then
        Assert.assertFalse(availableTime);
    }
}