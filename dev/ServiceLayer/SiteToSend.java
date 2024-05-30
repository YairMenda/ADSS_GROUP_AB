package ServiceLayer;
import BussinessLayer.*;
public class SiteToSend {
    private String address;
    private String phoneNumber;
    private String ContactName;
    private String shippingArea;
    public SiteToSend(Site other)
    {
        this.address = other.getAddress();
        this.phoneNumber = other.getContactName();
        this.ContactName = other.getPhoneNumber();
        this.shippingArea = other.getShippingArea();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String toString(){
        return "Address -"+address + " PhoneNumber -" + phoneNumber +" Contact Name - "+ContactName + " shipping Area - " + shippingArea;
    }

}
