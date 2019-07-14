package models;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)

public class Client extends User {

    public Client(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }
}