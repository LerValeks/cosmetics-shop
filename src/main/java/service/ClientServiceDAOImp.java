package service;

import models.Client;
import models.Reservation;
import repository.ClientDAO;
import service.Exceptions.ClientException;
import service.Validators.ClientValidator;

import java.time.LocalDateTime;
import java.util.List;

public class ClientServiceDAOImp implements ClientServiceDao {

    private final ClientServiceDao clientServiceDao;

    public ClientServiceDAOImp(ClientServiceDao clientServiceDao) {
        this.clientServiceDao = clientServiceDao;
    }

    @Override
    public Client add(Client client) throws ClientException {
        if(!ClientValidator.IsClientValid(client)){
            throw new ClientException("Client data is invalid");
        }
        return clientServiceDao.add(client);
    }

    @Override
    public Client update(Client client)throws ClientException {
        if(!ClientValidator.IsClientValid(client)){
            throw new ClientException("Client data is invalid");
        }

        return clientServiceDao.add(client);
    }

    @Override
    public List<Reservation> reservationByClient(Client client) throws ClientException {
        if(!ClientValidator.IsClientValid(client)){
            throw new ClientException("Client data is invalid");
        }
        return null;
    }

    @Override
    public Client delete(Client client)throws ClientException {
        if(!ClientValidator.IsClientValid(client)){
            throw new ClientException("Client data is invalid");
        }

        return clientServiceDao.delete(client);
    }

    @Override
    public List<Reservation> reservationByClientInSpecificPeriod(Client client, LocalDateTime startDate, LocalDateTime endDate) throws ClientException{
        if(!ClientValidator.IsClientValid(client)){
            throw new ClientException("Client data is invalid");
        }
        return null;
    }
}
