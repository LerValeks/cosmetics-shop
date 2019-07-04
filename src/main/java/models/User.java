package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class User {

    private String name;
    private String surname;
    private String phoneNumber;
    private Long id;

    //TODO: Client / Employee ID method to be created. Alex / Dan to understand how ID shall be implemented for 2 different users (Client / Employee)

    public User(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.id = null;
    }
}