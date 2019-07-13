package service;

import models.Client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.ClientDAO;
import service.exceptions.ClientException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceDAOImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private ClientServiceDAOImpl clientServiceDAO;

    @Mock
    private ClientDAO clientDAO;

    @Before
    public void setup() {
        this.clientServiceDAO = new ClientServiceDAOImpl(clientDAO);
    }

    @Test
    public void addClient_shouldThrowException_whenClientIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client is null");
        clientServiceDAO.add(null);
    }

    @Test
    public void deleteClient_shouldThrowException_whenClientIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client is null");
        clientServiceDAO.delete(null);
    }

    @Test
    public void updateClient_shouldThrowException_whenClientIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client is null");
        clientServiceDAO.update(null);
    }

    @Test
    public void addClient_shouldThrowException_whenClientParameterIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attribute is null");
        Client client = new Client("Aleks", "Valuyskov", null);
        clientServiceDAO.add(client);

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attribute is null");
        Client client2 = new Client(null, null, null);
        clientServiceDAO.add(client2);
    }

    @Test
    public void updateClient_shouldThrowException_whenClientParameterIsNull() throws ClientException {

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attribute is null");
        Client client = new Client("Aleks", "Valuyskov", null);
        clientServiceDAO.update(client);

        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attribute is null");
        Client client2 = new Client(null, null, null);
        clientServiceDAO.update(client2);
    }

    @Test
    public void addClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {

        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.add(any(Client.class))).thenReturn(client);

        // when
        Client clientTest = clientServiceDAO.add(client);

        //then
        Mockito.verify(clientDAO, Mockito.times(1)).add(client);
        Assert.assertNotNull(clientTest);
    }

    @Test
    public void updateClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {

        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.update(any(Client.class))).thenReturn(client);

        // when
        Client clientTest = clientServiceDAO.update(client);

        //then
        Assert.assertNotNull(clientTest);
        Mockito.verify(clientDAO, Mockito.times(1)).update(any(Client.class));
    }

    @Test
    public void deleteClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {

        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.delete(any(Client.class))).thenReturn(client);

        // when
        Client clientTest = clientServiceDAO.delete(client);

        //then
        assertNotNull(clientTest);
        Mockito.verify(clientDAO, Mockito.times(1)).delete(any(Client.class));
    }

    @Test
    public void reservationByClient() {
    }

    @Test
    public void reservationByClientInSpecificPeriod() {
    }
}