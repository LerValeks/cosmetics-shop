package service;

import models.Employee;
import models.Reservation;
import repository.EmployeeDatabase;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    EmployeeDatabase employeeDatabase;

    public ReservationServiceImpl(EmployeeDatabase employeeDatabase) {
        this.employeeDatabase = employeeDatabase;
    }

    @Override
    public Reservation bookVisit(Reservation reservation) {

        List<Employee> employees = employeeDatabase.getAllItemsFromDatabase();

        return reservation;
    }

    @Override
    public List<Reservation> displayVisit(String phoneNumber) {
        return null;
    }

    @Override
    public Long cancelVisit(Reservation reservation) {
        return null;
    }
}