package repository;

import models.Employee;

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
}
