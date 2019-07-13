package service;

import models.Client;
import models.Reservation;
import service.exceptions.ClientException;

import java.time.LocalDateTime;
import java.util.List;

//TODO: Robert to advise if this can be merged into 1 interface and used both for client and employee
public interface ClientServiceDAO {

    Client add(Client client) throws ClientException;

    Client update(Client client) throws ClientException;

    Client delete(Client client) throws ClientException;

    List<Reservation> reservationByClient(Client client) throws ClientException;

    List<Reservation> reservationByClientInSpecificPeriod(Client client, LocalDateTime startDate, LocalDateTime endDate) throws ClientException;
}