package service;

import models.Client;
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
import service.validation.ClientValidator;

import java.util.HashSet;
import java.util.Set;

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
        this.clientServiceDAO = new ClientServiceDAOImpl(clientServiceDAO);
    }

    @Test
    public void addClient_shouldThrowException_whenClientIsNull() throws ClientException {
        thrown.expect(ClientException.class);
        thrown.expectMessage("Client is null");
        clientServiceDAO.add(null);
    }

    @Test
    public void addClient_shouldThrowException_whenClientAttributesAreNull() throws ClientException {
        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attributes are null");
        Client client = new Client("Aleks", "Valuyskov", null);
        clientServiceDAO.add(client);
        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attributes are null");
        Client client2 = new Client(null, null, null);
        clientServiceDAO.add(client2);
    }

    @Test
    public void addClient_shouldThrowException_whenClientIsDuplicate() throws ClientException {
        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Client client2 = new Client("Danyl", "Tkachenko", "+345436");
        Client client3 = new Client("Aleks", "Valuyskov", "+3459435234");

        Set clients = new HashSet();
        clients.add(client);
        clients.add(client2);


        //when
        thrown.expect(ClientException.class);
        thrown.expectMessage("Such client already exist in Database");
        Mockito.when(clientDAO.getAllItems()).thenReturn(clients);
        Mockito.when(ClientValidator.validateIfCurrentClient(client3)).thenReturn(true);
        Client AddClientToDAO = clientServiceDAO.add(client3);

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
    public void updateClient_shouldThrowException_whenClientAttributesAreNull() throws ClientException {
        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attributes are null");
        Client client = new Client("Aleks", "Valuyskov", null);
        clientServiceDAO.update(client);
        thrown.expect(ClientException.class);
        thrown.expectMessage("Client attributes are null");
        Client client2 = new Client(null, null, null);
        clientServiceDAO.update(client2);
    }

    public void updateClient_shouldThrowException_whenClientIsDuplicate() throws ClientException {
        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Client client2 = new Client("Danyl", "Tkachenko", "+345436");
        Client client3 = new Client("Aleks", "Valuyskov", "+3459435234");

        Set clients = new HashSet();
        clients.add(client);
        clients.add(client2);


        //when
        thrown.expect(ClientException.class);
        thrown.expectMessage("Such client already exist in Database");
        Mockito.when(clientDAO.getAllItems()).thenReturn(clients);
        Mockito.when(ClientValidator.validateIfCurrentClient(client3)).thenReturn(true);
        Client UpdateClientIbDAO = clientServiceDAO.update(client3);
    }

    @Test
    public void addClient_whenClientIsCorrect_shouldAddClient() throws ClientException {
        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");

        // when
        clientServiceDAO.add(client);

        //then
        Mockito.verify(clientDAO, Mockito.times(1)).add(client);
    }

    @Test
    public void updateClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {
        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.update(any(Client.class))).thenReturn(client);

        // when
        Client client2 = clientServiceDAO.update(client);

        //then
        assertNotNull(client2);
        Mockito.verify(clientDAO, Mockito.times(1)).update(any(Client.class));
    }

    @Test
    public void deleteClient_whenClientIsCorrect_shouldReturnClient() throws ClientException {
        //given
        Client client = new Client("Aleks", "Valuyskov", "+3459435234");
        Mockito.when(clientDAO.delete(any(Client.class))).thenReturn(client);

        // when
        Client client2 = clientServiceDAO.delete(client);

        //then
        assertNotNull(client2);
        Mockito.verify(clientDAO, Mockito.times(1)).delete(any(Client.class));
    }

    @Test
    public void reservationByClient() {
    }

    @Test
    public void reservationByClientInSpecificPeriod() {
    }
}