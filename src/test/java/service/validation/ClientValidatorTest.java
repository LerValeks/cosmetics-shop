package service.validation;

import models.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import service.exceptions.ClientException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)

public class ClientValidatorTest {

    private static Client createClient() {

        return new Client("Alfa", "Client", "777 77 77");
    }

    @Test
    public void validateClientParameters_shouldReturnTrue_IfClientIsNotNullAndAllParametersAreNotNull() throws ClientException {

        //given
        Client client = createClient();

        //when
        boolean clientValidation = ClientValidator.validateClientParameters(client);

        //then
        Assert.assertNotNull(client);
        assertTrue(clientValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalse_IfClientIsNull() throws ClientException {

        //given
        Client client = null;

        //when
        boolean clientValidation = ClientValidator.validateClientParameters(client);

        //then
        assertFalse(clientValidation);
    }

    @Test
    public void validateClientParameters_shouldReturnFalse_IfClientHasOneNullParameter() throws ClientException {

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
}