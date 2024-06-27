package BussinessLayer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class EmployeeShift {

    private int employeeID;
    private List<LocalDateTime> shiftsDates;

    final static int shiftDuration = 8;

    public EmployeeShift(int employeeID,List<LocalDateTime> shiftsDates)
    {
        this.employeeID = employeeID;
        this.shiftsDates = shiftsDates;
    }

    public int getEmployeeID() {
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
}
