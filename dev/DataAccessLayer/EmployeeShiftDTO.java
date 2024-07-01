package DataAccessLayer;

import java.time.LocalDateTime;

public class EmployeeShiftDTO {
    private LocalDateTime shift;
    private String employeeID;

    public EmployeeShiftDTO(String employeeID,LocalDateTime shift)
    {
        this.employeeID=employeeID;
        this.shift=shift;
    }


    public LocalDateTime getShift() {
        return shift;
    }

    public String getEmployeeID() {
        return employeeID;
    }
}
