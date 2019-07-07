package service;

import models.Client;
import models.Reservation;
import repository.ClientDAO;
import service.exceptions.ClientException;
import service.validation.ClientValidator;

import java.time.LocalDateTime;
import java.util.List;

public class ClientServiceDAOImpl implements ClientServiceDAO {

    private final ClientDAO clientDAO;

    public ClientServiceDAOImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client add(Client client) throws ClientException{
        ClientValidator.validateClientParameters(client);
        return clientDAO.add(client);
    }

    @Override
    public Client update(Client client) throws ClientException {

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client data is invalid");
        }
        return clientDAO.add(client);
    }

    @Override
    public List<Reservation> reservationByClient(Client client) throws ClientException {

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client data is invalid");
        }
        return null;
    }

    @Override
    public Client delete(Client client) throws ClientException {

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client data is invalid");
        }
        return clientDAO.delete(client);
    }

    @Override
    public List<Reservation> reservationByClientInSpecificPeriod(Client client, LocalDateTime startDate, LocalDateTime endDate) throws ClientException {

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client data is invalid");
        }
        return null;
    }
}