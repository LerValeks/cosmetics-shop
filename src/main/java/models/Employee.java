package models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static models.EmploymentStatus.EMPLOYED;

@Setter
@Getter
//TODO: add employee ID for easier search.
public class Employee extends User {

    private List<Reservation> reservations;
    private EmploymentStatus employmentStatus;
    private ServiceCategory serviceCategory;

    public Employee(String name, String surname, String phoneNumber, ServiceCategory serviceCategory) {
        super(name, surname, phoneNumber);
        this.serviceCategory = serviceCategory;
        this.reservations = new ArrayList<>();
        this.employmentStatus = EMPLOYED;
    }
}