package service;

import models.Client;
import service.exceptions.ClientException;

public interface ClientServiceDAO {

    Client add(Client client) throws ClientException;

    Client update(Client client) throws ClientException;

    Client delete(Client client) throws ClientException;
}