package models;

import lombok.EqualsAndHashCode;

import static models.UserCategory.CLIENT;

@EqualsAndHashCode
public class Client extends User {

    public Client(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber, CLIENT);
    }
}