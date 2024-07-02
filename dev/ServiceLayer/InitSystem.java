package ServiceLayer;

import java.time.LocalDateTime;
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

    public InitSystem() throws Exception
    {
        deliveryFacade = new DeliveryFacade();
        tf = deliveryFacade.tf;
        sf = deliveryFacade.sf;
        df = deliveryFacade.df;


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
