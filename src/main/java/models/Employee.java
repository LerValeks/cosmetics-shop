package models;

import java.util.ArrayList;
import java.util.List;

import static models.UserCategory.EMPLOYEE;

public class Employee extends User {

    List<Reservation> reservations;

    private ServiceCategory serviceCategory;

    public Employee(String name, String surname, String phoneNumber, ServiceCategory serviceCategory) {
        super(name, surname, phoneNumber, EMPLOYEE);
        this.serviceCategory = serviceCategory;
        this.reservations = new ArrayList<>();
    }
}