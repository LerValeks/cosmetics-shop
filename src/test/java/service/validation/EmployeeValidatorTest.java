package service.validation;

import models.Client;
import models.Employee;
import models.Reservation;
import models.ServiceCategory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.exceptions.EmployeeException;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)

public class EmployeeValidatorTest {

    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    private static Client createClient() {
        return new Client("Sarah", "Doe", "5532203");
    }

    private static Employee createEmployee() {
        return new Employee("John", "Snow", "9379992", ServiceCategory.HAIRCUT);
    }

    private static Reservation createReservation(ServiceCategory serviceCategory) {
        return new Reservation(serviceCategory, createEmployee(), createClient(), LocalDateTime.now());
    }

    @Test
    public void validateClientParameters_shouldReturnTrueIfEmployeeIsNotNullAndAllParametersAreNotNull() throws EmployeeException {

        //given
        Employee employee = createEmployee();
        boolean employeeValidation = EmployeeValidator.validateEmployeeParameters(employee);

        //then
        Assert.assertNotNull(employee);
        Assert.assertTrue(employeeValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalseIfEmployeeIsNull() throws EmployeeException {

        //given
        Employee employee = null;
        boolean employeeValidation = EmployeeValidator.validateEmployeeParameters(employee);

        //then
        Assert.assertFalse(employeeValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalseIfEmployeeHasOneNullParameter() throws EmployeeException {

        //given
        Employee employee1 = createEmployee();
        employee1.setName(null);

        //when
        boolean employeeValidation1 = EmployeeValidator.validateEmployeeParameters(employee1);

        //then
        Assert.assertNotNull(employee1);
        Assert.assertFalse(employeeValidation1);

        /////////////////////////////////////

        //given
        Employee employee2 = createEmployee();
        employee2.setSurname(null);

        //when
        boolean employeeValidation2 = EmployeeValidator.validateEmployeeParameters(employee2);

        //then
        Assert.assertNotNull(employee2);
        Assert.assertFalse(employeeValidation2);

        /////////////////////////////////////

        //given
        Employee employee3 = createEmployee();
        employee3.setPhoneNumber(null);

        //when
        boolean employeeValidation3 = EmployeeValidator.validateEmployeeParameters(employee3);

        //then
        Assert.assertNotNull(employee3);
        Assert.assertFalse(employeeValidation3);

        /////////////////////////////////////

        //given
        Employee employee4 = createEmployee();
        employee4.setServiceCategory(null);

        //when
        boolean employeeValidation4 = EmployeeValidator.validateEmployeeParameters(employee4);

        //then
        Assert.assertNotNull(employee4);
        Assert.assertFalse(employeeValidation4);
    }


}