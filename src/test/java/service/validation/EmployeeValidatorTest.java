package service.validation;

import models.Employee;
import models.ServiceCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.exceptions.EmployeeException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)

public class EmployeeValidatorTest {

    private static Employee createEmployee() {

        return new Employee("Alfa", "Employee", "937 99 92", ServiceCategory.HAIRCUT);
    }

    @Test
    public void validateEmployeeParameters_shouldReturnTrueIfEmployeeIsNotNullAndAllParametersAreNotNull() throws EmployeeException {

        //given
        Employee employee = createEmployee();

        //when
        boolean employeeValidation = EmployeeValidator.validateEmployeeParameters(employee);

        //then
        Assert.assertNotNull(employee);
        assertTrue(employeeValidation);
    }

    @Test
    public void validateEmployeeParameters_shouldReturnFalseIfEmployeeIsNull() throws EmployeeException {

        //given
        Employee employee = null;

        //when
        boolean employeeValidation = EmployeeValidator.validateEmployeeParameters(employee);

        //then
        assertFalse(employeeValidation);
    }

    @Test
    public void validateEmployeeParameters_shouldReturnFalseIfEmployeeHasOneNullParameter() throws EmployeeException {

        //given
        Employee employee1 = createEmployee();
        employee1.setName(null);

        Employee employee2 = createEmployee();
        employee2.setSurname(null);

        Employee employee3 = createEmployee();
        employee3.setPhoneNumber(null);

        Employee employee4 = createEmployee();
        employee4.setServiceCategory(null);

        //when
        boolean employeeValidation1 = EmployeeValidator.validateEmployeeParameters(employee1);
        boolean employeeValidation2 = EmployeeValidator.validateEmployeeParameters(employee2);
        boolean employeeValidation3 = EmployeeValidator.validateEmployeeParameters(employee3);
        boolean employeeValidation4 = EmployeeValidator.validateEmployeeParameters(employee4);

        //then
        Assert.assertNotNull(employee1);
        assertFalse(employeeValidation1);

        Assert.assertNotNull(employee2);
        assertFalse(employeeValidation2);

        Assert.assertNotNull(employee3);
        assertFalse(employeeValidation3);

        Assert.assertNotNull(employee4);
        assertFalse(employeeValidation4);
    }
}