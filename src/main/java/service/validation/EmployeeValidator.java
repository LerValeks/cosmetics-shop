package service.validation;

import models.Employee;
import models.EmploymentStatus;
import models.Reservation;
import repository.EmployeeDAO;
import service.exceptions.EmployeeException;

public class EmployeeValidator {

    private static EmployeeDAO employeeDAO;

    //TODO: Robert to advise how making employeedatabase object "Static" will affect the code? In reservation service this isn't static, shall be? Why in general it's prohibited calling statics from non-static context?

    public static boolean validateClientParameters(Employee employee) throws EmployeeException {
        validateClientIsNull(employee);

               if ( validateEmployeeName(employee)
                || validateEmployeeSurname(employee)
                || validateEmployeePhone(employee)
                || validateEmployeeserviceCategory(employee)) {
                   throw new EmployeeException("Employee attributes are null");
               }
        return true;
    }

    public static boolean validateClientIsNull(Employee employee) throws EmployeeException  {
        if (employee==null) {
            throw new EmployeeException("Employee is null");
        }
        return true;
    }
    public static boolean validateIfCurrentEmployee(Reservation reservation) {

        return employeeDAO.getAllItems().contains(reservation.getEmployee().getEmploymentStatus() == EmploymentStatus.EMPLOYED);
    }

    private static boolean validateEmployeeName(Employee employee) {

        return employee.getName() == null;
    }

    private static boolean validateEmployeeSurname(Employee employee) {

        return employee.getSurname() == null;
    }

    private static boolean validateEmployeePhone(Employee employee) {

        return employee.getPhoneNumber() == null;
    }

    private static boolean validateEmployeeserviceCategory(Employee employee) {

        return employee.getServiceCategory() ==null;
    }
}
