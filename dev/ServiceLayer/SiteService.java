package ServiceLayer;

import java.util.LinkedList;
import java.util.List;

import BussinessLayer.Site;
import BussinessLayer.SiteFacade;
import BussinessLayer.Truck;

public class SiteService {
    public SiteFacade sf;



    public SiteService(SiteFacade sf){
        this.sf=sf;
    }



    public String addNewSite(String address,String phoneNumber,String ContactName){
        try
        {
            Site s= sf.addSite(address, phoneNumber, ContactName);
            return JsonSerializer.Serialize(new Response(s,null));
            
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }
}
