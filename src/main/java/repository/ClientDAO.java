package repository;

import models.Client;

import java.util.Set;

public class ClientDAO implements DAOItem<Client> {

    @Override
    public Client add(Client domain) {
        return domain;
    }

    @Override
    public Client delete(Client domain) {
        return domain;
    }

    @Override
    public Client update(Client domain) {
        return domain;
    }

    @Override
    public Client getItem(Long id) {
        return null;
    }

    @Override
    public Set<Client> getAllItems() {
        return null;
    }
}