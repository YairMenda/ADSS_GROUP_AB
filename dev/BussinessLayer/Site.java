package BussinessLayer;

import DataAccessLayer.EmployeeShiftDTO;
import DataAccessLayer.SiteDTO;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Site {
    private String address;
    private String phoneNumber;
    private String ContactName;
    private String shippingArea;

    private List<EmployeeShift> shopKeeperShifts;

    private SiteDTO sDTO;

    public Site(String address,String phoneNumber,String ContactName,String shippingArea,List<EmployeeShift> shopKeeperShifts)
    {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ContactName = ContactName;
        this.shippingArea = shippingArea;
        this.shopKeeperShifts = shopKeeperShifts;
        List<EmployeeShiftDTO> employeesShifts = new LinkedList<>();
        for(EmployeeShift es : this.shopKeeperShifts)
            for (EmployeeShiftDTO esDTO : es.getShiftsDTO())
                employeesShifts.add(esDTO);
        this.sDTO=new SiteDTO(this.address,this.phoneNumber,this.ContactName,this.shippingArea,employeesShifts);
        this.sDTO.addSite();
    }

    public Site(SiteDTO sDTO)
    {
        this.address=sDTO.getAddress();
        this.phoneNumber= sDTO.getPhoneNumber();
        this.ContactName=sDTO.getContactName();
        this.shippingArea=sDTO.getShippingArea();

        //this.shopkeeperShifts

        this.sDTO=sDTO;
    }
    public boolean isShopKeeperAvailable(LocalDateTime estimatedArrivalTime)
    {
        for (EmployeeShift es : shopKeeperShifts)
        {
            if(es.isAvailable(estimatedArrivalTime))
                return true;
        }
        return  false;
    }
    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;}


    public String getContactName(){
        return ContactName;}

    
    public String getShippingArea() {
            return shippingArea;
        }
    
    
    public void setShippingArea(String shippingArea) {
            this.shippingArea = shippingArea;
        }    

    public void setAddress(String address){
        this.address=address;}

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
        this.sDTO.setPhoneNumber(phoneNumber);
    }

    public void setContactName(String contactName){
        this.ContactName=contactName;
        this.sDTO.setContactName(contactName);
    }

    public SiteDTO getsDTO() {
        return sDTO;
    }
}
