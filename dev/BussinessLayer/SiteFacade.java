package BussinessLayer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SiteFacade {

    private Map<String , Site> sites;

    public SiteFacade()
    {
        this.sites = new HashMap<>();
    }

    public List<Site> getSites()
    {
        return new LinkedList<Site>(this.sites.values());
    }

    public Site addSite(String address,String phoneNumber,String ContactName) throws Exception
    {
        if (!sites.containsKey(address)){
            Site s = new Site(address, phoneNumber, ContactName);
            sites.put(address,s);
            return s;
            }
    
        throw new Exception("Site with this address already exists");
    }

    public void removeSite(String address)
    {

        sites.remove(address);
    }
    
    public String getPhoneNumber(String address) throws Exception
    {
        return getSite(address).getPhoneNumber();
    }

    public String getContactName(String address) throws Exception
    {
        return getSite(address).getContactName();
    }

    public void editPhonenNumber(String address,String newPhoneNumber) throws Exception
    {
        getSite(address).setPhoneNumber(newPhoneNumber);
    }

    public void editContactName(String address,String newContactName)  throws Exception
    {
         getSite(address).setContactName(newContactName);
    }

    public Site getSite (String address) throws Exception
    {
        if (sites.containsKey(address))
            return sites.get(address);
            
        throw new Exception("NO sites with this address");
    }

    

}
