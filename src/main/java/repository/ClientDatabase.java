package repository;

import models.Client;

import java.util.Set;

public class ClientDatabase implements DatabaseInteraction<Client> {

    @Override
    public Client addToDatabase(Client databaseItem) {
        return null;
    }

    @Override
    public Client deleteFromDatabase(Client databaseItem) {
        return null;
    }

    @Override
    public Client updateDatabase(Client databaseItem) {
        return null;
    }

    @Override
    public Client getItemFromDatabase(Integer itemID) {
        return null;
    }

    @Override
    public Set<Client> getAllItemsFromDatabase() {
        return null;
    }
}