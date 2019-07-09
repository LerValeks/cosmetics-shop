package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//TODO: add reservation ID
@AllArgsConstructor
@Getter
@Setter
public class Reservation {

    ServiceCategory serviceCategory;
    Employee employee;
    Client client;
    LocalDateTime reservationTime;
}