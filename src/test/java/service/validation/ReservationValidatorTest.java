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
import repository.EmployeeDAO;
import service.exceptions.ReservationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(MockitoJUnitRunner.class)

public class ReservationValidatorTest {

    @Mock
    EmployeeDAO employeeDAO;

    @InjectMocks
    ReservationValidator reservationValidator;

    private static Employee createEmployee() {

        return new Employee("Alfa", "Employee", "937 99 92", ServiceCategory.HAIRCUT);
    }

    private static Client createClient() {

        return new Client("Alfa", "Client", "777 77 77");
    }

    private static Reservation createReservation(Employee employee, Client client) {

        return new Reservation(ServiceCategory.HAIRCUT, employee, client, LocalDateTime.now());
    }

    @Test
    public void validateReservationParameters_shouldReturnTrue_IfReservationIsNotNullAndAllParametersAreNotNull() throws ReservationException {

        //given
        Reservation reservation = new Reservation(ServiceCategory.HAIRCUT, createEmployee(), createClient(), LocalDateTime.now());

        //when
        boolean correctReservation = ReservationValidator.validateReservationParameters(reservation);

        //then
        Assert.assertNotNull(reservation);
        Assert.assertTrue(correctReservation);
    }

    @Test
    public void validateReservationIsTimeNotInPast_shouldReturnTrue_IfTimeIsNotEarlierThanFiveMinutesBeforeNow() throws ReservationException {

        //given
        Reservation reservation = new Reservation(ServiceCategory.HAIRCUT, createEmployee(), createClient(), LocalDateTime.now());

        //when
        boolean correctReservationTime = ReservationValidator.validateReservationIsTimeNotInPast(reservation);

        //then
        Assert.assertNotNull(reservation);
        Assert.assertTrue(correctReservationTime);
    }

    @Test
    public void validateReservationStatus_shouldReturnTrue_IfReservationStatusIsActive() throws ReservationException {

        //given
        Reservation reservation = new Reservation(ServiceCategory.HAIRCUT, createEmployee(), createClient(), LocalDateTime.now());

        //when
        boolean correctReservationTime = ReservationValidator.validateReservationStatus(reservation);

        //then
        Assert.assertNotNull(reservation);
        Assert.assertTrue(correctReservationTime);
    }

    @Test
    public void validateIfReservationTimeIsFree_shouldReturnTrue_IfEmployeeHasFreeTimeSlot() throws ReservationException {

        //given
        Client client = createClient();
        Employee employee = createEmployee();

        Reservation newReservation = createReservation(employee, client);
        Reservation existingReservation = createReservation(employee, client);
        existingReservation.setReservationTime(LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30)));

        employee.getReservations().add(existingReservation);

        //when
        Mockito.when(employeeDAO.getItem(Mockito.anyString())).thenReturn(employee);
        boolean availableTime = reservationValidator.validateIfReservationTimeIsFree(newReservation);

        //then
        Assert.assertFalse(availableTime);
    }
}