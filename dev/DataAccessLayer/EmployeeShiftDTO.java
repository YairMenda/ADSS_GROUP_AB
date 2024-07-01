package DataAccessLayer;

import java.time.LocalDateTime;

public class EmployeeShiftDTO {
    private LocalDateTime shift;
    private int employeeID;

    public EmployeeShiftDTO(int employeeID,LocalDateTime shift)
    {
        this.employeeID=employeeID;
        this.shift=shift;
    }


    public LocalDateTime getShift() {
        return shift;
    }

    public int getEmployeeID() {
        return employeeID;
    }
}
