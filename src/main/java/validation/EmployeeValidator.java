package validation;

import models.EmploymentStatus;
import models.Reservation;
import repository.EmployeeDatabase;

public class EmployeeValidator {

    private static EmployeeDatabase employeeDatabase;

    //TODO: Robert to advise how making employeedatabase object "Static" will affect the code? In reservation service this isn't static, shall be? Why in general it's prohibited calling statics from non-static context?

    public static boolean validateIfCurrentEmployee(Reservation reservation) {

        return employeeDatabase.getAllItemsFromDatabase().contains(reservation.getEmployee().getEmploymentStatus() == EmploymentStatus.EMPLOYED);
    }
}