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
    private UserCategory userCategory;


    public User(String name, String surname, String phoneNumber, UserCategory userCategory) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.userCategory = userCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}