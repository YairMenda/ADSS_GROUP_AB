package BussinessLayer;

public class Site {
    private String address;
    private String phoneNumber;
    private String ContactName;

    public Site(String address,String phoneNumber,String ContactName)
    {
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.ContactName = ContactName;
    }


    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;}


    public String getContactName(){
        return ContactName;}


    public void setAddress(String address){
        this.address=address;}

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;}

    public void setContactName(String contactName){
        this.ContactName=contactName;}

}
