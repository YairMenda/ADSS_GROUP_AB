package ServiceLayer;

import java.util.LinkedList;
import java.util.List;

import BussinessLayer.EmployeeShift;
import BussinessLayer.Site;
import BussinessLayer.SiteFacade;
import BussinessLayer.Truck;

public class SiteService {
    public SiteFacade sf;



    public SiteService(SiteFacade sf){
        this.sf=sf;
    }



    public Response addNewSite(String address, String phoneNumber, String ContactName, String sa, List<EmployeeShift> shifts){
        try
        {
            Site s = sf.addSite(address, phoneNumber, ContactName,sa,shifts);
            return new Response(new SiteToSend(s),null);
            
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    public Response getSite(String address){
        try
        {
            Site s = sf.getSite(address);
            return new Response(new SiteToSend(s) ,null);
            
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }
    public Response getAllSites(){
        try{
            List<Site> sites = sf.getSites();
            List<String> addresses = new LinkedList<String>();
            for (Site s:sites){
                addresses.add(s.getAddress());  
            }
            return new Response(addresses,null);
        }
        catch(Exception e){
            return new Response(null, e.getMessage());
        }  
    }
    
    public Response getPhoneNumber(String address){
        try{
            String phoneNum = sf.getPhoneNumber(address);
        return new Response(phoneNum,null);  
        }
        catch(Exception e){
            return new Response(null, e.getMessage());
        }  
    }

    public Response getContactName(String address){
        try{
            String contactName = sf.getContactName(address);
        return new Response(contactName,null);  
        }
        catch(Exception e){
            return new Response(null, e.getMessage());
        }  

}

    public Response editPhoneNumber(String address,String newPhone){
        try{
            sf.editPhonenNumber(address,newPhone);
            return new Response(address,null);  
        }
        catch(Exception e){
            return new Response(null, e.getMessage());
        }  

        }


    public Response editContactName(String address,String newContact){
        try{
            sf.editContactName(address,newContact);
            return new Response(address,null);  
        }
        catch(Exception e){
            return new Response(null, e.getMessage());
        }  

}    
}
