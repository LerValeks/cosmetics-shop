package service.validation;

import models.Client;
import models.Employee;
import models.EmploymentStatus;
import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;

import java.util.Collection;
import java.util.Set;

public class ClientValidator {

    private final ClientDAO clientDAO;
    private final EmployeeDAO employeeDAO;

    public ClientValidator(ClientDAO clientDAO, EmployeeDAO employeeDAO) {
        this.clientDAO = clientDAO;
        this.employeeDAO = employeeDAO;
    }

    public static boolean validateClientParameters(Client client) throws ClientException {

        return validateClientIsNotNull(client)
                && validateClientNameIsNotNull(client)
                && validateClientSurnameIsNotNull(client)
                && validateClientPhoneIsNotNull(client);
    }

    private static boolean validateClientIsNotNull(Client client) {

        return client != null;
    }

    private static boolean validateClientNameIsNotNull(Client client) {

        return client.getName() != null;
    }

    private static boolean validateClientSurnameIsNotNull(Client client) {

        return client.getSurname() != null;
    }

    private static boolean validateClientPhoneIsNotNull(Client client) {

        return client.getPhoneNumber() != null;
    }

    public boolean validateIfExistingClient(Client client) throws ClientException {

        Set<Client> allClients = clientDAO.getAllItems();

        return allClients.contains(client);
    }

    public boolean validateIfExistingClientHasReservationAtTheSameTime(Reservation reservation) throws ClientException {

        Set<Employee> allEmployees = employeeDAO.getAllItems();

        return allEmployees.stream()
                .filter(employee -> employee.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .map(Employee::getReservations)
                .flatMap(Collection::stream)
                .filter(reservation1 -> reservation1.getClient().equals(reservation.getClient()))
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime.equals(reservation.getReservationTime()));
    }
}