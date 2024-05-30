package BussinessLayer;

public class Site {
    private String address;
    private String phoneNumber;
    private String ContactName;
    private String shippingArea;


    public Site(String address,String phoneNumber,String ContactName,String shippingArea)
    {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ContactName = ContactName;
        this.shippingArea = shippingArea;
    
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
        this.phoneNumber=phoneNumber;}

    public void setContactName(String contactName){
        this.ContactName=contactName;}

}
