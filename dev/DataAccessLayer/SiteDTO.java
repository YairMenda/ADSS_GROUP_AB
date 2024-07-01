package DataAccessLayer;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SiteDTO {
    private String address;
    private String phoneNumber;
    private String contactName;
    private String shippingArea;
    private List<EmployeeShiftDTO> employeesShifts;
    private SiteController controller;


    public SiteDTO(String address,String phoneNumber,String contactName,String shippingArea,List<EmployeeShiftDTO> employees)
    {
        this.address=address;
        this.phoneNumber = phoneNumber;
        this.contactName=contactName;
        this.shippingArea=shippingArea;
        this.employeesShifts=employees;
        this.controller=new SiteController();
    }
    public boolean setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return update();
    }

    public boolean setContactName(String contactName)
    {
        this.contactName = contactName;
        return update();
    }

    public String getShippingArea() {
        return shippingArea;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public boolean update(){
        return controller.update(this);
    }

    //Note
    public List<LocalDateTime> getAllShifts(){
        List<LocalDateTime> shifts = new LinkedList<>();
        for(EmployeeShiftDTO es : this.getEmployees())
        {
            shifts.add(es.getShift());
        }
        return shifts;
    }

    public boolean addSite(){
        return controller.add(this);
    }
    public List<EmployeeShiftDTO> getEmployees() {
        return employeesShifts;
    }

}
