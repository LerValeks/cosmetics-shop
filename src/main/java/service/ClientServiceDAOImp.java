package service;

import models.Client;
import models.Reservation;
import repository.ClientDAO;

import java.time.LocalDateTime;
import java.util.List;

public class ClientServiceDAOImp implements ClientServiceDao {

    private final ClientServiceDao clientServiceDao;

    public ClientServiceDAOImp(ClientServiceDao clientServiceDao) {
        this.clientServiceDao = clientServiceDao;
    }

    @Override
    public Client add(Client client) {
        return clientServiceDao.add(client);
    }

    @Override
    public Client update(Client client) {
        return clientServiceDao.add(client);
    }

    @Override
    public List<Reservation> reservationByClient(ClientDAO clientDAO) {
        return null;
    }

    @Override
    public Client delete(Client client) {
        return clientServiceDao.delete(client);
    }

    @Override
    public List<Reservation> reservationByClientInSpecificPeriod(Client client, LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }
}
