package service;

import models.Client;
import models.Reservation;
import models.ServiceCategory;
import repository.ClientDAO;
import service.Exceptions.ClientException;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientServiceDao {
    Client add(Client client) throws ClientException;

    Client update(Client client) throws ClientException;

    Client delete(Client client) throws ClientException;

    List<Reservation> reservationByClient(Client client) throws ClientException;

    List<Reservation> reservationByClientInSpecificPeriod(Client client, LocalDateTime startDate, LocalDateTime endDate) throws ClientException;


}
