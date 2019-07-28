package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)

public class Client extends User {

    public Client(String name,
                  String surname,
                  String phoneNumber) {
        super(name, surname, phoneNumber);
    }
}