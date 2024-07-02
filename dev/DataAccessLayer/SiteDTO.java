package DataAccessLayer;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class SiteDTO {
    private String address;
    private String phoneNumber;
    private String contactName;
    private String shippingArea;
    private List<EmployeeShiftDTO> employees;
    private SiteController controller;


    public SiteDTO(String address,String phoneNumber,String contactName,String shippingArea,List<EmployeeShiftDTO> employees)
    {
        this.address=address;
        this.phoneNumber = phoneNumber;
        this.contactName=contactName;
        this.shippingArea=shippingArea;
        this.employees=employees;
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


    

    public boolean addSite(){
        for (EmployeeShiftDTO es : this.employees)
            es.add();
        return controller.add(this);
    }
    public List<EmployeeShiftDTO> getEmployees() {
        return employees;
    }

}
