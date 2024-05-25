package ServiceLayer;
import BussinessLayer.Delivery;
import BussinessLayer.DeliveryFacade;
import BussinessLayer.DstDoc;

import java.time.LocalDateTime;
import java.util.List;
public class DeliveryService {
    private DeliveryFacade df;



    public DeliveryService(DeliveryFacade df){
        this.df=df;
    }

    public String addNewDelivery(LocalDateTime depTime, int licenseNumber, String driverID, String originAddress){
        try
        {
            Delivery d = df.addNewDelivery(depTime, licenseNumber, driverID, originAddress);
            return JsonSerializer.Serialize(new Response(d.getDeliveryNumber(), null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    
    
    }

    public String removeDelivery(int deliveryNum){
        try
        {
            df.removeDelivery(deliveryNum);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }


    public String updateWeight(int deliveryNum, double weight){
        try
        {
            df.weightUpdate(deliveryNum,weight);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }    
    }

    public String replaceTruck(int deliveryNum , int truckNum){
        try
        {
            df.replaceTruck(deliveryNum,truckNum);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }    }


    public String removeDestination(int deliveryNum , String adress){
        try
        {
            df.removeDestination(deliveryNum, adress);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }

    public String removeProduct(int deliveryNum , int docNumber, List<Integer> itemsTODelte){
        try
        {
            df.removeProducts(deliveryNum, docNumber,itemsTODelte);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }

    public String completeDelivery(int deliveryNum){
        try
        {
            df.completeDelivery(deliveryNum);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }

    //only by docNumber
    public String getDestinationDocument(int deliveryNum , int docNumber){
        try
        {
            DstDoc dd = df.getDstDoc(deliveryNum,docNumber);
            return JsonSerializer.Serialize(new Response(dd.getDocNumber(), null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }

    //by deliveryNumber and address
    public String getDestinationDocument(int deliveryNum, String address){
        try
        {
            DstDoc dd = df.getDstDoc(deliveryNum,address);
            return JsonSerializer.Serialize(new Response(dd.getDocNumber(), null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }    
    }

    public String inProgressDelivery(int deliveryNum){
        try
        {
            df.inProgress(deliveryNum);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }    
    }

    public String disapproveDelivery(int deliveryNum){
        try
        {
            df.disapproveDelivery(deliveryNum);
            return JsonSerializer.Serialize(new Response(deliveryNum, null));
        }
        catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        } 
    }



}
