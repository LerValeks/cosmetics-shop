package service;

import models.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation bookVisit(Reservation reservation);

    List<Reservation> displayVisit(String phoneNumber);

    Long cancelVisit(Reservation reservation);
}