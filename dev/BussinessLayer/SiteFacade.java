package BussinessLayer;
import java.util.LinkedList;
import java.util.List;

public class SiteFacade {

    private List<Site> sites;

    public SiteFacade()
    {
        this.sites = new LinkedList<Site>();
    }

    public List<Site> getSites()
    {
        return this.sites;
    }

    public void addSite(Site s)
    {
        sites.add(s);
    }

    public void removeSite(Site s)
    {
        sites.remove(s);
    }

}
