package models;

import java.util.ArrayList;
import java.util.List;

public class Worker extends User {
    private List<String> absences;
    private List<String> reservations;


    public Worker(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
        this.absences = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

}
