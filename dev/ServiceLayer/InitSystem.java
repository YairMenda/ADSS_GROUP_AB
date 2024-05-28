package ServiceLayer;

import java.util.LinkedList;
import java.util.List;

import BussinessLayer.*;

public class InitSystem {
    TruckFacade tf;
    SiteFacade sf;
    DriverFacade df;
    DeliveryFacade deliveryFacade;

    TruckService ts;
    DriverService ds;
    SiteService ss;
    DeliveryService deliveryService;

    public InitSystem()
    {
      
        tf = new TruckFacade();
        sf = new SiteFacade();
        
        List<Driver> ld = new LinkedList<Driver>();
        List<String> licenseList = new LinkedList<String>();

        licenseList.add("a");
        licenseList.add("b");
        ld.add(new Driver("1","omer", new LinkedList<String>(licenseList)));
        ld.add(new Driver("2","roi", new LinkedList<String>(licenseList)));

        df = new DriverFacade(ld);
        deliveryFacade = new DeliveryFacade(tf, df, sf);

        ts = new TruckService(tf);
        ds = new DriverService(df);
        ss = new SiteService(sf);
        deliveryService = new DeliveryService(deliveryFacade);
    }
    
    public TruckService getTs() {
        return ts;
    }

    public DriverService getDs() {
        return ds;
    }
    
    public SiteService getSs() {
        return ss;
    }
    
    public DeliveryService getDeliveryService() {
        return deliveryService;
    }


    

}
