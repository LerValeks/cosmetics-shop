package service.validation;

import models.Client;
import models.Employee;
import models.EmploymentStatus;
import models.Reservation;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * DAOValidator class is devoted to all data checks where reference to Databases is involved
 */

public class DAOValidator {

    private final ClientDAO clientDAO;
    private final EmployeeDAO employeeDAO;

    public DAOValidator(ClientDAO clientDAO, EmployeeDAO employeeDAO) {
        this.clientDAO = clientDAO;
        this.employeeDAO = employeeDAO;
    }

    public boolean validateIfExistingClient(Client client) throws ClientException {

        return clientDAO.getAllItems().contains(client);
    }

    public boolean validateIfCurrentEmployeeIsEmployed(Employee employee) throws EmployeeException {

        return employeeDAO.getAllItems().stream()
                .filter(employee1 -> employee1.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .collect(Collectors.toSet())
                .contains(employee);
    }

    public boolean validateIfReservationTimeIsFree(Reservation reservation) throws ReservationException {

        return employeeDAO.getItem(reservation.getEmployee().getPhoneNumber())
                .getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime == reservation.getReservationTime());
    }

    public boolean validateIfExistingClientHasReservationAtTheSameTime(Reservation reservation) throws ClientException {

        return employeeDAO.getAllItems().stream()
                .filter(employee -> employee.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .map(Employee::getReservations)
                .flatMap(Collection::stream)
                .filter(reservation1 -> reservation1.getClient().equals(reservation.getClient()))
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime == reservation.getReservationTime());
    }
}