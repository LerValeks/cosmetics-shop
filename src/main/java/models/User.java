package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class User {

    private String name;
    private String surname;
    private String phoneNumber;
    private Integer id;

    public User(String name, String surname, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
}