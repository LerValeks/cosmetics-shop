package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import static models.EmploymentStatus.EMPLOYED;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)

public class Employee extends User {

    private Set<Reservation> reservations;
    private EmploymentStatus employmentStatus;
    private ServiceCategory serviceCategory;

    public Employee(String name, String surname, String phoneNumber, ServiceCategory serviceCategory) {
        super(name, surname, phoneNumber);
        this.serviceCategory = serviceCategory;
        this.reservations = new HashSet<>();
        this.employmentStatus = EMPLOYED;
    }
}