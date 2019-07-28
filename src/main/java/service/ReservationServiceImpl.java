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
import java.util.Set;
import java.util.stream.Collectors;

public class ReservationServiceImpl {

    //TODO: Why validators are in grey? Why employee and client services don't require a parameter?
    private final EmployeeDAO employeeDAO;
    private final ClientDAO clientDAO;

    private EmployeeServiceImpl employeeService;
    private ClientServiceImpl clientService;

    private EmployeeValidator employeeValidator;
    private ClientValidator clientValidator;
    private ReservationValidator reservationValidator;

    public ReservationServiceImpl(EmployeeDAO employeeDAO,
                                  ClientDAO clientDAO,
                                  EmployeeValidator employeeValidator,
                                  ClientValidator clientValidator,
                                  ReservationValidator reservationValidator,
                                  EmployeeServiceImpl employeeService,
                                  ClientServiceImpl clientService) {

        this.employeeDAO = employeeDAO;
        this.clientDAO = clientDAO;
        this.employeeValidator = employeeValidator;
        this.clientValidator = clientValidator;
        this.reservationValidator = reservationValidator;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    public Reservation makeReservation(Reservation reservation) throws ClientException, EmployeeException, ReservationException {

        validateReservationParameters(reservation);
        validateEmployeeParameters(reservation);
        checkIfEmployeeIsEmployed(reservation);
        checkIfReservationTimeIsAvailable(reservation);
        validateClientParameters(reservation);
        checkIfClientHasOtherReservations(reservation);

        addReservationToEmployeeBook(reservation);

        return reservation;
    }

    public Set<Reservation> showActiveReservations(String phoneNumber) {

        Set<Employee> allEmployees = employeeDAO.getAllItems();
        LocalDateTime today = LocalDateTime.now();

        return allEmployees.stream()
                .filter(employee -> employee.getEmploymentStatus().equals(EmploymentStatus.EMPLOYED))
                .map(Employee::getReservations)
                .flatMap(Set::stream)
                .filter(reservation -> reservation.getClient().getPhoneNumber().equals(phoneNumber))
                .filter(reservation -> reservation.getReservationTime().isAfter(today.minusMinutes(5)))
                .filter(reservation -> reservation.getReservationStatus().equals(ReservationStatus.ACTIVE))
                .collect(Collectors.toSet());
    }

    public Set<Reservation> showAllReservationsForSpecificTimePeriod(String phoneNumber, LocalDate fromDate, LocalDate toDate) {

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

        if (!listOfEmployeeReservations.contains(reservation)) {
            throw new ReservationException("Such reservation doesn't exist therefore cannot be cancelled");
        }

        listOfEmployeeReservations.remove(reservation);
        reservation.setReservationStatus(ReservationStatus.CANCELLED);

        return listOfEmployeeReservations.add(reservation);
    }

    private void validateReservationParameters(Reservation reservation) throws ReservationException {

        if (!ReservationValidator.validateReservationParameters(reservation)) {
            throw new ReservationException("Reservation object is null or reservation parameters are incorrectly initialized");
        }
    }

    private void validateEmployeeParameters(Reservation reservation) throws EmployeeException {

        Employee employee = reservation.getEmployee();

        if (!EmployeeValidator.validateEmployeeParameters(employee)) {
            throw new EmployeeException("Employee object is null or employee parameters are incorrectly initialized");
        }
    }

    private void validateClientParameters(Reservation reservation) throws ClientException {

        Client client = reservation.getClient();

        if (!ClientValidator.validateClientParameters(client)) {
            throw new ClientException("Client object is null or client parameters are incorrectly initialized");
        }
    }

    private void checkIfEmployeeIsEmployed(Reservation reservation) throws EmployeeException {

        Employee employee = reservation.getEmployee();

        if (!employeeService.checkIfCurrentEmployeeIsEmployed(employee)) {
            throw new EmployeeException("Employee not found! Please check if you've chosen correct employee");
        }
    }

    //TODO: How to test exception throwing
    private void checkIfReservationTimeIsAvailable(Reservation reservation) throws ReservationException {

        if (checkIfReservationTimeIsBooked(reservation)) {
            throw new ReservationException("This time is already booked. Please try another time");
        }
    }

    //TODO: Make sure  validation time trims seconds
    //TODO: Robert to advise if makes sense wrapping this method into method checkIfReservationTimeIsAvailable?
    public boolean checkIfReservationTimeIsBooked(Reservation reservation) throws ReservationException {

        String phoneNumber = reservation.getEmployee().getPhoneNumber();
        Employee employee = employeeDAO.getItem(phoneNumber);
        LocalDateTime reservationTime = reservation.getReservationTime();

        return employee.getReservations().stream()
                .map(Reservation::getReservationTime)
                .anyMatch(localDateTime -> localDateTime.equals(reservationTime));
    }

    private void checkIfClientHasOtherReservations(Reservation reservation) throws ClientException {

        Client client = reservation.getClient();

        if (!clientService.checkIfExistingClient(client)) {
            clientDAO.add(client);
        } else if (clientService.checkIfExistingClientHasReservationAtTheSameTime(reservation)) {
            throw new ClientException("Client has reservation at requested time, please choose another reservation time");
        }
    }

    private void addReservationToEmployeeBook(Reservation reservation) {

        Employee employee = reservation.getEmployee();
        Set<Reservation> listOfEmployeeReservations = employee.getReservations();

        listOfEmployeeReservations.add(reservation);
    }
}