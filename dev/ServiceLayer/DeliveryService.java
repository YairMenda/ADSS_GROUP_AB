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

    public Response addNewDelivery(LocalDateTime depTime, int licenseNumber, String driverID, String originAddress){
        try
        {
            Delivery d = df.addNewDelivery(depTime, licenseNumber, driverID, originAddress);
            return new Response(d.getDeliveryNumber(), null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    
    
    }

    public Response removeDelivery(int deliveryNum){
        try
        {
            df.removeDelivery(deliveryNum);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }


    public Response updateWeight(int deliveryNum, double weight){
        try
        {
            df.weightUpdate(deliveryNum,weight);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    
    }

    public Response replaceTruck(int deliveryNum , int truckNum){
        try
        {
            df.replaceTruck(deliveryNum,truckNum);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    }


    public Response removeDestination(int deliveryNum , String adress){
        try
        {
            df.removeDestination(deliveryNum, adress);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    public Response removeProduct(int deliveryNum , int docNumber, List<Integer> itemsTODelte){
        try
        {
            df.removeProducts(deliveryNum, docNumber,itemsTODelte);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    public Response completeDelivery(int deliveryNum){
        try
        {
            df.completeDelivery(deliveryNum);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    //only by docNumber
    public Response getDestinationDocument(int deliveryNum , int docNumber){
        try
        {
            DstDoc dd = df.getDstDoc(deliveryNum,docNumber);
            return new Response(dd.getDocNumber(), null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    //by deliveryNumber and address
    public Response getDestinationDocument(int deliveryNum, String address){
        try
        {
            DstDoc dd = df.getDstDoc(deliveryNum,address);
            return new Response(dd.getDocNumber(), null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    
    }

    public Response inProgressDelivery(int deliveryNum){
        try
        {
            df.inProgress(deliveryNum);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    
    }

    public Response disapproveDelivery(int deliveryNum){
        try
        {
            df.disapproveDelivery(deliveryNum);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        } 
    }



}
