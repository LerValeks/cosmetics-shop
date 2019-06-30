package validation;

import models.Client;
import models.Reservation;

import java.util.Set;

public class ClientValidator {

    private static boolean validateIfNewClient(Reservation reservation, Set<Client> registeredClients) {

        Client client = reservation.getClient();

        return registeredClients.contains(client);
    }
}