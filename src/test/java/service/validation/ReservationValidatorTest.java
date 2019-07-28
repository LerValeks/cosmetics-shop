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

@RunWith(MockitoJUnitRunner.class)
public class ReservationValidatorTest {

    private static Employee createEmployee() {

        return new Employee("Alfa", "Employee", "937 99 92", ServiceCategory.HAIRCUT);
    }

    private static Client createClient() {

        return new Client("Alfa", "Client", "777 77 77");
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
}