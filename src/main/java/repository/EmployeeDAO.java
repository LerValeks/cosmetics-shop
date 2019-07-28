package repository;

import models.Employee;

import java.util.Set;

public class EmployeeDAO implements DAOItem<Employee> {

    @Override
    public Employee add(Employee domain) {
        return domain;
    }

    @Override
    public Employee update(Employee domain) {
        return domain;
    }

    @Override
    public Employee delete(Employee domain) {
        return domain;
    }

    @Override
    public Employee getItem(String phoneNumber) {
        return null;
    }

    @Override
    public Set<Employee> getAllItems() {
        return null;
    }
}