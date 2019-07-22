package service;

import models.Client;
import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)

public class ReservationServiceImplTest {

    @Mock
    ClientDAO clientDAO;

    @Mock
    EmployeeDAO employeeDAO;

    @InjectMocks
    ReservationServiceImpl reservationService;

    private static Employee createEmployee() {

        return new Employee("Alfa", "Employee", "937 99 92", ServiceCategory.HAIRCUT);
    }

    private static Client createClient() {

        return new Client("Alfa", "Client", "777 77 77");
    }

    private static Reservation createReservation(Employee employee, Client client) {

        return new Reservation(ServiceCategory.HAIRCUT, employee, client, LocalDateTime.now());
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
    public void makeReservation() throws ReservationException, EmployeeException, ClientException {

        //given
        Set<Employee> employees = createEmployees();
        Employee employee = createEmployee();
        Client client = createClient();
        Reservation reservation = createReservation(employee, client);
        employees.add(employee);

        //when
        Reservation bookedReservation = reservationService.makeReservation(reservation);


    }

    @Test
    public void showActiveReservations() {
    }

    @Test
    public void showReservationsForSpecicTimePeriod() {
    }

    @Test
    public void cancelClientReservation() {
    }
}