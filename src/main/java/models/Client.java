package models;

import static models.UserCategory.CLIENT;

public class Client extends User {

    public Client(String name, String surname, String phoneNumber, UserCategory userCategory) {
        super(name, surname, phoneNumber, CLIENT);
    }
}