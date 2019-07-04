package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static models.EmploymentStatus.EMPLOYED;
import static models.UserCategory.EMPLOYEE;

@Setter
@Getter
@EqualsAndHashCode
public class Employee extends User {

    private List<Reservation> reservations;
    private EmploymentStatus employmentStatus;
    private ServiceCategory serviceCategory;

    public Employee(String name, String surname, String phoneNumber, ServiceCategory serviceCategory) {
        super(name, surname, phoneNumber, EMPLOYEE);
        this.serviceCategory = serviceCategory;
        this.reservations = new ArrayList<>();
        this.employmentStatus = EMPLOYED;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public void setId(Integer id) {
        super.setId(id);
    }

    public void setServiceCategory(ServiceCategory serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public ServiceCategory getServiceCategory() {
        return serviceCategory;
    }
}