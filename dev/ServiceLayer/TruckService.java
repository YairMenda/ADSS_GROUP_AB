package ServiceLayer;
import BussinessLayer.Truck;
import BussinessLayer.TruckFacade;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List; 

public class TruckService {

    private TruckFacade tf;

    public TruckService(TruckFacade tf){
        this.tf=tf;
    }


    public Response addNewTruck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,String licenseCategory){
        try
        {
            Truck t = tf.addTruck(licenseNumber, model, weightWithoutCargo, maxWeight, licenseCategory);
            return new Response(t.getLicenseNumber(),null);
            
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    public Response getTruck(int licenseNumber){
        try
        {
            Truck t = tf.getTruck(licenseNumber);
            return new Response(new TruckToSend(t),null);
            
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    }

    public Response getAllTrucks(){
        try
        {
            List<Truck> truckList = tf.getTrucks();
            List<Integer> listLicenseNumbers = new LinkedList<Integer>(); 
            for (Truck t : truckList)
                listLicenseNumbers.add(t.getLicenseNumber());

            return new Response(listLicenseNumbers,null);
            
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    }

    public Response getAvialibleTrucks(LocalDateTime date){
        try
        {
            List<Truck> truckList = tf.getAvialibleTrucks(date);
            List<Integer> listLicenseNumbers = new LinkedList<Integer>(); 
            for (Truck t : truckList)
                listLicenseNumbers.add(t.getLicenseNumber());

            return new Response(listLicenseNumbers,null);
            
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }


    }
}




