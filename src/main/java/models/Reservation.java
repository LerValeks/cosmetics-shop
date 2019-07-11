package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//TODO: add reservation ID for easier search.
@AllArgsConstructor
@Getter
@Setter
public class Reservation {

    ServiceCategory serviceCategory;
    Employee employee;
    Client client;
    LocalDate reservationTime;
}