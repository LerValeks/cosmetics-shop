package service.validation;

import models.Client;
import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.exceptions.ReservationException;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class ReservationValidatorTest {

    private static Employee createEmployee() {

        return new Employee("Alfa", "Employee", "937 99 92", ServiceCategory.HAIRCUT);
    }

    private static Client createClient() {

        return new Client("Alfa", "Client", "777 77 77");
    }

    private static Reservation createReservation() {

        return new Reservation(
                ServiceCategory.HAIRCUT,
                createEmployee(),
                createClient(),
                LocalDateTime.now());
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
    public void validateReservationParameters_shouldReturnFalse_IfReservationIsNull() throws ReservationException {

        //given
        Reservation reservation = null;

        //when
        boolean reservationValidation = ReservationValidator.validateReservationParameters(reservation);

        //then
        assertFalse(reservationValidation);
    }

    @Test
    public void validateReservationParameters_shouldReturnFalseIfReservationHasOneNullParameter() throws ReservationException {

        //given
        Reservation reservation1 = createReservation();
        reservation1.setServiceCategory(null);

        Reservation reservation2 = createReservation();
        reservation2.setEmployee(null);

        Reservation reservation3 = createReservation();
        reservation3.setClient(null);

        Reservation reservation4 = createReservation();
        reservation4.setReservationTime(null);

        //when
        boolean reservationValidation1 = ReservationValidator.validateReservationParameters(reservation1);
        boolean reservationValidation2 = ReservationValidator.validateReservationParameters(reservation2);
        boolean reservationValidation3 = ReservationValidator.validateReservationParameters(reservation3);
        boolean reservationValidation4 = ReservationValidator.validateReservationParameters(reservation4);

        //then
        Assert.assertNotNull(reservation1);
        assertFalse(reservationValidation1);

        Assert.assertNotNull(reservation2);
        assertFalse(reservationValidation2);

        Assert.assertNotNull(reservation3);
        assertFalse(reservationValidation3);

        Assert.assertNotNull(reservation4);
        assertFalse(reservationValidation4);
    }
}