package BussinessLayer;

import DataAccessLayer.EmployeeShiftDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class EmployeeShift {

    private String employeeID;
    private List<LocalDateTime> shiftsDates;

    private String site;
    private List<EmployeeShiftDTO> shiftsDTO;

    final static int shiftDuration = 8;

    public EmployeeShift(String employeeID,List<LocalDateTime> shiftsDates,String address)
    {
        this.employeeID = employeeID;
        this.shiftsDates = shiftsDates;
        this.site=address;
        this.shiftsDTO=new LinkedList<>();
        for (LocalDateTime date : this.shiftsDates)
        {
            this.shiftsDTO.add(new EmployeeShiftDTO(employeeID,date,address));
        }
    }

    public EmployeeShift(List<EmployeeShiftDTO> shiftsDTO)
    {
        this.employeeID=shiftsDTO.get(0).getEmployeeID();
        this.shiftsDTO=shiftsDTO;
        this.shiftsDates = new LinkedList<>();
        for (EmployeeShiftDTO es : this.shiftsDTO)
            shiftsDates.add(es.getShift());
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public List<LocalDateTime> getShiftsDates() {
        return shiftsDates;
    }

    public boolean isAvailable(LocalDateTime estimatedArrivalTime)
    {

        for(LocalDateTime shiftDate : shiftsDates)
        {
             if ((Duration.between(shiftDate,estimatedArrivalTime).toSeconds()< (shiftDuration*3600)) && (Duration.between(shiftDate,estimatedArrivalTime).toSeconds()>=0))
                return true;
        }
        return  false;
    }

    public List<EmployeeShiftDTO> getShiftsDTO()
    {
        return this.shiftsDTO;
    }
}
