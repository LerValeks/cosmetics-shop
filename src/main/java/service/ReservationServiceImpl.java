package service;

import models.*;
import repository.ClientDAO;
import repository.EmployeeDAO;
import service.exceptions.ClientException;
import service.exceptions.EmployeeException;
import service.exceptions.ReservationException;
import service.validation.ClientValidator;
import service.validation.EmployeeValidator;
import service.validation.ReservationValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class ReservationServiceImpl {

    private final EmployeeDAO employeeDAO;
    private final ClientDAO clientDAO;
    private EmployeeValidator employeeValidator;
    private ClientValidator clientValidator;
    private ReservationValidator reservationValidator;

    public ReservationServiceImpl(EmployeeDAO employeeDAO,
                                  ClientDAO clientDAO,
                                  EmployeeValidator employeeValidator,
                                  ClientValidator clientValidator,
                                  ReservationValidator reservationValidator) {
        this.employeeDAO = employeeDAO;
        this.clientDAO = clientDAO;
        this.employeeValidator = employeeValidator;
        this.clientValidator = clientValidator;
        this.reservationValidator = reservationValidator;
    }

    public Reservation makeReservation(Reservation reservation) throws ClientException, EmployeeException, ReservationException {

        validateReservationParameters(reservation);
        validateReservationStatus(reservation);
        validateIfEmployeeIsEmployed(reservation);
        validateIfReservationTimeIsAvailable(reservation);
        validateClientParameters(reservation);
        validateIfClientHasReservation(reservation);

        addReservationToEmployeeBook(reservation);

        return reservation;
    }

    //TODO: Consider removing exception
    public Set<Reservation> showActiveReservations(String phoneNumber) {

        Set<Employee> allEmployees = employeeDAO.getAllItems();
        LocalDateTime today = LocalDateTime.now();

        return allEmployees.stream()
                .filter(employee -> employee.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .map(Employee::getReservations)
                .flatMap(Set::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .filter(reservation -> reservation.getReservationTime().isAfter(LocalDateTime.now().minusMinutes(5)))
                .collect(Collectors.toSet());
    }

    //TODO: Consider removing exception
    public Set<Reservation> showReservationsForSpecificTimePeriod(String phoneNumber, LocalDate fromDate, LocalDate toDate) throws ClientException {

        Set<Employee> allEmployees = employeeDAO.getAllItems();

        return allEmployees.stream()
                .map(Employee::getReservations)
                .flatMap(Set::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .filter(reservation -> reservation.getReservationTime().toLocalDate().compareTo(fromDate) == 0 || reservation.getReservationTime().toLocalDate().compareTo(fromDate) > 0)
                .filter(reservation -> reservation.getReservationTime().toLocalDate().compareTo(toDate) == 0 || reservation.getReservationTime().toLocalDate().compareTo(toDate) < 0)
                .collect(Collectors.toSet());
    }

    public boolean cancelClientReservation(Reservation reservation) throws ReservationException {

        validateReservationParameters(reservation);
        String phoneNumber = reservation.getEmployee().getPhoneNumber();
        Employee employee = employeeDAO.getItem(phoneNumber);
        Set<Reservation> listOfEmployeeReservations = employee.getReservations();
        listOfEmployeeReservations.remove(reservation);
        reservation.setReservationStatus(ReservationStatus.CANCELLED);

        return listOfEmployeeReservations.add(reservation);
    }

    private void validateReservationParameters(Reservation reservation) throws ReservationException {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
            throw new ReservationException("Reservation object is null or reservation parameters are incorrectly initialized");
        }
    }

    private void validateClientParameters(Reservation reservation) throws ClientException {

        Client client = reservation.getClient();

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
    }

    private void validateIfEmployeeIsEmployed(Reservation reservation) throws EmployeeException {

        Employee employee = reservation.getEmployee();

        if (!checkIfCurrentEmployeeIsEmployed(employee)) {
            throw new EmployeeException("Employee not found!");
        }
    }

    //TODO: To consider if this shall be part of reservationParameters validator
    private void validateIfReservationTimeIsAvailable(Reservation reservation) throws ReservationException {

        if (checkIfReservationTimeIsBooked(reservation)) {
            throw new ReservationException("This time is already booked. Please try another time");
        }
    }

    private void validateIfClientHasReservation(Reservation reservation) throws ClientException {

        Client client = reservation.getClient();

        if (!checkIfExistingClient(client)) {
            clientDAO.add(client);
        } else if (checkIfExistingClientHasReservationAtTheSameTime(reservation)) {
            throw new ClientException("Client has reservation at requested time, please choose another reservation time");
        }
    }

    private void validateReservationStatus(Reservation reservation) throws ReservationException {

        if (!ReservationValidator.validateReservationStatus(reservation)) {
            throw new ReservationException("Cannot make reservation with non-active status!");
        }
    }

    private void addReservationToEmployeeBook(Reservation reservation) {

        Employee employee = reservation.getEmployee();
        Set<Reservation> listOfEmployeeReservations = employee.getReservations();

        listOfEmployeeReservations.add(reservation);
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

    public boolean checkIfExistingClient(Client client) throws ClientException {

        Set<Client> allClients = clientDAO.getAllItems();

        return allClients.contains(client);
    }

    public boolean checkIfCurrentEmployeeIsEmployed(Employee employee) throws EmployeeException {

        Set<Employee> allEmployees = employeeDAO.getAllItems();

        return allEmployees.stream()
                .filter(employee1 -> employee1.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .collect(Collectors.toSet())
                .contains(employee);
    }

    //TODO: Make sure  validation time trims seconds
    public boolean checkIfReservationTimeIsBooked(Reservation reservation) throws ReservationException {

        String phoneNumber = reservation.getEmployee().getPhoneNumber();
        Employee employee = employeeDAO.getItem(phoneNumber);
        LocalDateTime reservationTime = reservation.getReservationTime();

        return employee.getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime.equals(reservationTime));
    }
}