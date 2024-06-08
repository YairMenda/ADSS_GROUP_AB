package BussinessLayer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SiteFacade {

    private Map<String , Site> sites;

    public SiteFacade(List<Site> list)
    {
        this.sites = new HashMap<>();
        for (Site s: list){
            sites.put(s.getAddress(),s);
        }
        
    }

    /// <summary>
    /// the method gets all the sites on the system
    /// </summary>
    /// <returns>list of sites, throws exception if fails</returns>
    public List<Site> getSites()
    {
        return new LinkedList<Site>(this.sites.values());
    }


    /// <summary>
    /// the method adds a new site to the system
    /// </summary>
    /// <param name="address"> the address of the new site</param>
    /// <param name="phoneNumber"> the phone number of the site</param>
    /// <param name="ContactName"> the contact name of the site</param>
    /// <param name="sa"> the shipping area of the site</param>
    /// <returns>the new site, throws exception if fails</returns>
    public Site addSite(String address,String phoneNumber,String ContactName,String sa) throws Exception
    {
        if (!sites.containsKey(address)){
            Site s = new Site(address, phoneNumber, ContactName,sa);
            sites.put(address,s);
            return s;
            }
    
        throw new Exception("Site with this address already exists");
    }

    /// <summary>
    /// the method removes a new site from the system
    /// </summary>
    /// <param name="address"> the address of the site we want to remove</param>
    /// <returns>throws exception if fails</returns>
    public void removeSite(String address)
    {

        sites.remove(address);
    }

    /// <summary>
    /// the method gets the phone number of a site
    /// </summary>
    /// <param name="address"> the address of the site we want to get</param>
    /// <returns>the phone number of the site, throws exception if fails</returns>
    public String getPhoneNumber(String address) throws Exception
    {
        return getSite(address).getPhoneNumber();
    }

    /// <summary>
    /// the method gets a contact name of a site
    /// </summary>
    /// <param name="address"> the address of the site we want to get</param>
    /// <returns>the contact name of the site, throws exception if fails</returns>
    public String getContactName(String address) throws Exception
    {
        return getSite(address).getContactName();
    }

    /// <summary>
    /// the method changes the phone number to a site
    /// </summary>
    /// <param name="address"> the address of the site we want to update</param>
    /// <param name="newPhoneNumber"> the phone number we want to update to</param>
    /// <returns>throws exception if fails</returns>
    public void editPhonenNumber(String address,String newPhoneNumber) throws Exception
    {
        getSite(address).setPhoneNumber(newPhoneNumber);
    }

    /// <summary>
    /// the method changes the contact name to a site
    /// </summary>
    /// <param name="address"> the address of the site we want to update</param>
    /// <param name="newContactName"> the contact name we want to update to</param>
    /// <returns>throws exception if fails</returns>
    public void editContactName(String address,String newContactName)  throws Exception
    {
         getSite(address).setContactName(newContactName);
    }

    /// <summary>
    /// the method gets a site
    /// </summary>
    /// <param name="address"> the address of the site we want</param>
    /// <returns>the site, throws exception if fails</returns>
    public Site getSite(String address) throws Exception
    {
        if (sites.containsKey(address))
            return sites.get(address);
            
        throw new Exception("NO sites with this address");
    }

    

}
