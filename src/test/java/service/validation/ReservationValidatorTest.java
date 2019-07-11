package service.validation;

import models.Client;
import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import org.junit.Assert;
import org.junit.Test;
import service.exceptions.ReservationException;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ReservationValidatorTest {

    private static Client createClient() {
        return new Client("Sarah", "Doe", "5532203");
    }

    private static Employee createEmployee() {
        return new Employee("John", "Snow", "9379992", ServiceCategory.HAIRCUT);
    }

    private static Reservation createReservation(ServiceCategory serviceCategory) {
        return new Reservation(serviceCategory, createEmployee(), createClient(), LocalDate.now());
    }

    @Test
    public void validateReservationParameters_shouldReturnTrueIfReservationIsNotNullAndAllParametersAreNotNullAndReservationServiceMatchesEmployeeSpecialization() throws ReservationException {

        //given
        Reservation reservation = createReservation(ServiceCategory.HAIRCUT);

        //when
        boolean reservationValidation = ReservationValidator.validateReservationParameters(reservation);

        //then
        Assert.assertNotNull(reservation);
        assertTrue(reservationValidation);
    }

    @Test
    public void validateReservationParameters_shouldReturnFalseIfReservationIsNull() throws ReservationException {

        //given
        Reservation reservation = null;

        //when
        boolean reservationValidation = ReservationValidator.validateReservationParameters(reservation);

        //then
        assertFalse(reservationValidation);
    }

    @Test
    public void validateEmployeeParameters_shouldReturnFalseIfEmployeeHasOneNullParameter() throws ReservationException {

        //given
        Reservation reservation1 = createReservation(ServiceCategory.HAIRCUT);
//       reservation1.setServiceCategory(null);

        Reservation reservation2 = createReservation(ServiceCategory.HAIRCUT);
//        reservation2.setClient(null);

        Reservation reservation3 = createReservation(ServiceCategory.HAIRCUT);
//        reservation3.setEmployee(null);

        Reservation reservation4 = createReservation(ServiceCategory.HAIRCUT);
//        reservation4.setReservationTime(null);

        Reservation reservation5 = createReservation(ServiceCategory.MASSAGE);
//        reservation5.setReservationTime(null);

        //when
        boolean reservationValidation1 = ReservationValidator.validateReservationParameters(reservation1);
        boolean reservationValidation2 = ReservationValidator.validateReservationParameters(reservation2);
        boolean reservationValidation3 = ReservationValidator.validateReservationParameters(reservation3);
        boolean reservationValidation4 = ReservationValidator.validateReservationParameters(reservation4);
        boolean reservationValidation5 = ReservationValidator.validateReservationParameters(reservation4);

        //then
        Assert.assertNotNull(reservation1);
        assertFalse(reservationValidation1);

        Assert.assertNotNull(reservation2);
        assertFalse(reservationValidation2);

        Assert.assertNotNull(reservation3);
        assertFalse(reservationValidation3);

        Assert.assertNotNull(reservation4);
        assertFalse(reservationValidation4);

        Assert.assertNotNull(reservation5);
        assertFalse(reservationValidation5);
    }


    @Test
    public void validateReservationTimeIsAvailable() {
    }

    @Test
    public void validateReservationIsAvailable() {
    }

    @Test
    public void validateReservationIsTimeNotInPast_shouldReturnTrueWhenReservationIsNotInThePast() {

        //given
        Reservation reservation = createReservation(ServiceCategory.HAIRCUT);

        //when
        boolean reservationTimeValidation = ReservationValidator.validateReservationIsTimeNotInPast(reservation);

        //then
        assertTrue(reservationTimeValidation);
    }

    @Test
    public void validateReservationIsTimeNotInPast_shouldReturnFalseWhenReservationIsInThePast() {

        //given
        Reservation reservation = createReservation(ServiceCategory.HAIRCUT);
        reservation.setReservationTime(LocalDate.now().minusDays(3));

        //when
        boolean reservationTimeValidation = ReservationValidator.validateReservationIsTimeNotInPast(reservation);

        //then
        assertFalse(reservationTimeValidation);
    }
}