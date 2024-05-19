package ServiceLayer;

import BussinessLayer.SiteFacade;

public class SiteService {
    public SiteFacade sf;



    public SiteService(SiteFacade sf){
        this.sf=sf;
    }



    public String addNewSite(String address,String phoneNumber,String ContactName){
        throw new UnsupportedOperationException();
    }
}
