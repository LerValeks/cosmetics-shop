package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static models.ReservationStatus.ACTIVE;

@Setter
@Getter
@EqualsAndHashCode

public class Reservation {

    ServiceCategory serviceCategory;
    Employee employee;
    Client client;
    LocalDateTime reservationTime;
    ReservationStatus reservationStatus;

    public Reservation(ServiceCategory serviceCategory,
                       Employee employee,
                       Client client,
                       LocalDateTime reservationTime) {
        this.serviceCategory = serviceCategory;
        this.employee = employee;
        this.client = client;
        this.reservationTime = reservationTime;
        this.reservationStatus = ACTIVE;
    }
}