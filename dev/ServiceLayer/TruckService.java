package ServiceLayer;
import BussinessLayer.Truck;
import BussinessLayer.TruckFacade;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List; 

public class TruckService {

    private TruckFacade tf;

    public TruckService(TruckFacade tf){
        this.tf=tf;
    }


    public String addNewTruck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,String licenseCategory){
        try
        {
            Truck t = tf.addTruck(licenseNumber, model, weightWithoutCargo, maxWeight, licenseCategory);
            return JsonSerializer.Serialize(new Response(t.getLicenseNumber),null);
            
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }

    public String getTruck(int licenseNumber){
        try
        {
            Truck t = tf.getTruck(licenseNumber);
            return JsonSerializer.Serialize(new Response(t.getLicenseNumber),null);
            
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }    }

    public String getAllTrucks(){
        try
        {
            List<Truck> truckList = tf.getTrucks();
            List<Integer> listLicenseNumbers = new LinkedList<Integer>(); 
            for (Truck t : truckList)
                listLicenseNumbers.add(t.getLicenseNumber());

            return JsonSerializer.Serialize(new Response(listLicenseNumbers,null));
            
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }    }

    public String getAvialibleTrucks(Date date){
        try
        {
            List<Truck> truckList = tf.getAvialibleTrucks(date);
            List<Integer> listLicenseNumbers = new LinkedList<Integer>(); 
            for (Truck t : truckList)
                listLicenseNumbers.add(t.getLicenseNumber());

            return JsonSerializer.Serialize(new Response(listLicenseNumbers,null));
            
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }


}




// public string CreateBoard(string email, string boardName, int maxBackLog, int maxInProggress)
//         {
//             try
//             {
//                 Board b=boardFacade.CreateBoard(email, boardName, maxBackLog, maxInProggress);
//                 Log.Info("User " + email + " Succeed in Create Board - " + boardName);
//                 return JsonSerializer.Serialize(new Response(b.BoardID, null));
//             }
//             catch (Exception ex)
//             {
//                 Log.Error("User " + email + " Failed in create Board - " + boardName + " ERROR - " + ex.Message);
//                 return JsonSerializer.Serialize(new Response(null, ex.Message));
//             }
//         }