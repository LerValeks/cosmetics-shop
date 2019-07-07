package service;

import models.Client;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repository.ClientDAO;
import service.exceptions.ClientException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceDAOImplTest {

    @InjectMocks
    private ClientServiceDAOImpl clientServiceDAO;

    @Mock
    private ClientDAO clientDAO;

    @Before
    public void setup() {
        this.clientServiceDAO = new ClientServiceDAOImpl(clientDAO);
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

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
    public void update() {
    }

    @Test
    public void reservationByClient() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void reservationByClientInSpecificPeriod() {
    }
}