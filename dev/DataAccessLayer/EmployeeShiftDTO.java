package DataAccessLayer;

import java.time.LocalDateTime;

public class EmployeeShiftDTO {
    private LocalDateTime shift;
    private String employeeID;
    private String site;
    private EmployeeShiftController controller;

    public EmployeeShiftDTO(String employeeID,LocalDateTime shift,String site)
    {
        this.employeeID=employeeID;
        this.shift=shift;
        this.site=site;
        this.controller = new EmployeeShiftController();
    }


    public LocalDateTime getShift() {
        return shift;
    }

    public String getEmployeeID() {
        return employeeID;
    }
    public boolean add()
    {
        return this.controller.add(this);
    }

    public String getSite() {
        return site;
    }
}
