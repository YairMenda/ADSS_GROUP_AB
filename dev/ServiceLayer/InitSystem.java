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

    public InitSystem()
    {
      

        List<Truck> lt = new LinkedList<Truck>();
        try{
            lt.add(new Truck(1,"A",10.0,20.0,"a"));
            lt.add(new Truck(2, "S", 10.0,20.0, "b"));
        }catch (Exception e){}

        List<LocalDateTime> shiftsDates = new LinkedList<>();
        shiftsDates.add(LocalDateTime.of(2024,8,1,12,0));
        shiftsDates.add(LocalDateTime.of(2024,9,1,12,0));

        List<EmployeeShift> shifts = new LinkedList<>();
        EmployeeShift e1 = new EmployeeShift(1,shiftsDates);
        shifts.add(e1);

        EmployeeShift e2 = new EmployeeShift(2,shiftsDates);
        shifts.add(e2);

        List<Site> ls = new LinkedList<Site>();
        ls.add(new Site("Matan","0300301212","yair","south",shifts));
        ls.add(new Site("Ness Ziona","0355501212","omer","south",shifts));

        List<Driver> ld = new LinkedList<Driver>();
        List<String> licenseList = new LinkedList<String>();

        licenseList.add("a");
        licenseList.add("b");
        ld.add(new Driver("1","omer", new LinkedList<String>(licenseList),e1));
        ld.add(new Driver("2","roi", new LinkedList<String>(licenseList),e2));

        
        tf = new TruckFacade(lt);
        sf = new SiteFacade(ls);
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
