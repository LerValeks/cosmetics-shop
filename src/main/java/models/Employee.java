package models;

import static models.UserCategory.EMPLOYEE;

public class Employee extends User {

    private ServiceCategory serviceCategory;

    public Employee(String name, String surname, String phoneNumber, ServiceCategory serviceCategory) {
        super(name, surname, phoneNumber, EMPLOYEE);
        this.serviceCategory = serviceCategory;
    }
}