package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer extends User {
    public Customer(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }
}
