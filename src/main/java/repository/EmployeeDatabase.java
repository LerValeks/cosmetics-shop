package repository;

import models.Employee;

import java.util.Set;

public class EmployeeDatabase implements DatabaseInteraction<Employee> {

    @Override
    public Employee addToDatabase(Employee databaseItem) {
        return null;
    }

    @Override
    public Employee deleteFromDatabase(Employee databaseItem) {
        return null;
    }

    @Override
    public Employee updateDatabase(Employee databaseItem) {
        return null;
    }

    @Override
    public Employee getItemFromDatabase(Integer itemID) {
        return null;
    }

    @Override
    public Set<Employee> getAllItemsFromDatabase() {
        return null;
    }
}