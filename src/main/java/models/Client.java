package models;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
//TODO: add client ID for easier search.
public class Client extends User {

    public Client(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }
}