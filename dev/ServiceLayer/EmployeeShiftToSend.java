package ServiceLayer;

import BussinessLayer.Delivery;
import BussinessLayer.DeliveryDates;
import BussinessLayer.EmployeeShift;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class EmployeeShiftToSend {

    private String employeeID;
    private List<LocalDateTime> shifts;

    public EmployeeShiftToSend(EmployeeShift other){
        employeeID = other.getEmployeeID();
        shifts = new LinkedList<>();
        for (LocalDateTime d : other.getShiftsDates())
            shifts.add(d);
    }

    public List<LocalDateTime> getShifts(){
        return shifts;
    }
    public String getEmployeeID(){ return employeeID;}

}
