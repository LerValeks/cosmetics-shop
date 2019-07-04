package repository;

import models.Employee;

import java.util.List;

public class EmployeeDAO extends DAOitemAbstr<Employee> implements DAOitem<Employee> {

    @Override
    public Employee add(Employee domain) {
        return super.add(domain);
    }

    @Override
    public Employee delete(Employee domain) {
        return super.delete(domain);
    }

    @Override
    public Employee update(Employee domain) {
        return super.update(domain);
    }

    @Override
    public Employee get(Number id) {
        return super.get(id);
    }


    @Override
    public List<Employee> getAllItemsFromDAO() {
        return super.getAllItemsFromDAO();
    }
}