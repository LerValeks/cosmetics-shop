package service;

import models.Client;
import repository.ClientDAO;
import service.exceptions.ClientException;
import service.validation.ClientValidator;

public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;

    public ClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public Client add(Client client) throws ClientException {

        if (ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
        return clientDAO.add(client);
    }

    @Override
    public Client update(Client client) throws ClientException {

        if (ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
        return clientDAO.update(client);
    }

    @Override
    public Client delete(Client client) throws ClientException {

        if (ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
        return clientDAO.delete(client);
    }
}