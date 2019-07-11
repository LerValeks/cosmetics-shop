package service.validation;

import models.Client;
import org.junit.Assert;
import org.junit.Test;
import service.exceptions.ClientException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ClientValidatorTest {

    private static Client createClient() {
        return new Client("John", "Snow", "9379992");
    }

    @Test
    public void validateClientParameters_shouldReturnTrueIfClientIsNotNullAndAllParametersAreNotNull() throws ClientException {

        //given
        Client client = createClient();

        //when
        boolean clientValidation = ClientValidator.validateClientParameters(client);

        //then
        Assert.assertNotNull(client);
        assertTrue(clientValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalseIfClientIsNull() throws ClientException {

        //given
        Client client = null;

        //when
        boolean clientValidation = ClientValidator.validateClientParameters(client);

        //then
        assertFalse(clientValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalseIfClientHasOneNullParameter() throws ClientException {

        //given
        Client client1 = createClient();
        client1.setName(null);

        Client client2 = createClient();
        client2.setSurname(null);

        Client client3 = createClient();
        client3.setPhoneNumber(null);

        //when
        boolean clientValidation1 = ClientValidator.validateClientParameters(client1);
        boolean clientValidation2 = ClientValidator.validateClientParameters(client2);
        boolean clientValidation3 = ClientValidator.validateClientParameters(client3);

        //then
        Assert.assertNotNull(client1);
        assertFalse(clientValidation1);

        Assert.assertNotNull(client2);
        assertFalse(clientValidation2);

        Assert.assertNotNull(client3);
        assertFalse(clientValidation3);
    }

    //TODO: Require mock static object DAO - mocking not allowed with statics. Robert to advise
    @Test
    public void validateIfCurrentClient() {
    }

    //TODO: Require mock static object DAO - mocking not allowed with statics. Robert to advise
    @Test
    public void validateClientHasReservationAtTheSameTime() {
    }
}