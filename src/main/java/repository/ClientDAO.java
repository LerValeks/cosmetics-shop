package repository;

import models.Client;

import java.util.List;

public class ClientDAO extends DAOitemAbstr<Client> implements DAOitem<Client> {

    @Override
    public Client add(Client domain) {
        return super.add(domain);
    }


    @Override
    public Client delete(Client domain) {
        return super.delete(domain);
    }

    @Override
    public Client update(Client domain) {
        return super.update(domain);
    }

    @Override
    public Client get(Number id) {
        return super.get(id);
    }

    @Override
    public List<Client> getAllItemsFromDAO() {
        return super.getAllItemsFromDAO();
    }
}