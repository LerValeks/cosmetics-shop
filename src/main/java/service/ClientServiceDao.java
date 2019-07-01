package service;

import models.Client;
import models.Reservation;
import models.ServiceCategory;
import repository.ClientDAO;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientServiceDao {
    Client add(Client client);

    Client update(Client client);

    Client delete(Client client);

    List<Reservation> reservationByClient(ClientDAO clientDAO);

    List<Reservation> reservationByClientInSpecificPeriod(Client client, LocalDateTime startDate, LocalDateTime endDate);


}
