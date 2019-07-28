package service;

import models.Client;
import models.Employee;
import models.EmploymentStatus;
import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.validation.ClientValidator;

import java.util.Collection;
import java.util.Set;

public class ClientServiceImpl {

    private final ClientDAO clientDAO;
    private final EmployeeDAO employeeDAO;

    public ClientServiceImpl(ClientDAO clientDAO, EmployeeDAO employeeDAO) {
        this.clientDAO = clientDAO;
        this.employeeDAO = employeeDAO;
    }

    public Client add(Client client) throws ClientException {

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
        return clientDAO.add(client);
    }

    public Client update(Client client) throws ClientException {

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
        return clientDAO.update(client);
    }

    public Client delete(Client client) throws ClientException {

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
        return clientDAO.delete(client);
    }

    public boolean checkIfExistingClient(Client client) {

        Set<Client> allClients = clientDAO.getAllItems();

        return allClients.contains(client);
    }

    public boolean checkIfExistingClientHasReservationAtTheSameTime(Reservation reservation) throws ClientException {

        Set<Employee> allEmployees = employeeDAO.getAllItems();
        String clientPhoneNumber = reservation.getClient().getPhoneNumber();

        return allEmployees.stream()
                .filter(employee -> employee.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .map(Employee::getReservations)
                .flatMap(Collection::stream)
                .filter(reservation1 -> reservation1.getClient().getPhoneNumber().equals(clientPhoneNumber))
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime.equals(reservation.getReservationTime()));
    }
}