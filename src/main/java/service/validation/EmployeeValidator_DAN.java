package service.validation;

import models.EmploymentStatus;
import models.Reservation;
import repository.EmployeeDAO;

public class EmployeeValidator_DAN {

    private static EmployeeDAO employeeDAO;

    //TODO: Robert to advise how making employeedatabase object "Static" will affect the code? In reservation service this isn't static, shall be? Why in general it's prohibited calling statics from non-static context?

    public static boolean validateIfCurrentEmployee(Reservation reservation) {

        return employeeDAO.getAllItems().contains(reservation.getEmployee().getEmploymentStatus() == EmploymentStatus.EMPLOYED);
    }
}