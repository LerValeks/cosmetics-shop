package service.validation;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.exceptions.EmployeeException;

@RunWith(MockitoJUnitRunner.class)

public class EmployeeValidatorTest {

    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    @Test(expected = EmployeeException.class)
    public void validateClientParameters_shouldThrowEmployeeException_IfEmployeeObjectIsNotInitialized() throws EmployeeException {

    }

    @Test(expected = EmployeeException.class)
    public void validateClientParameters_shouldThrowEmployeeException_IfEmployeeContainsNullParameters() {

    }

    @Test(expected = EmployeeException.class)
    public void validateIfCurrentEmployee_shouldThrowEmployeeException_IfEmployeeIsUnemployed() {
    }
}