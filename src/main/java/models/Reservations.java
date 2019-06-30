package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter

public class Reservations {

    ServiceCategory serviceCategory;
    Employee employee;
    Client client;
    LocalDateTime reservationTime;
}