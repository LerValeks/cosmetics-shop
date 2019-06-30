package repository;

import models.Employee;

import java.util.List;

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
    public List<Employee> getAllItemsFromDatabase() {
        return null;
    }
}