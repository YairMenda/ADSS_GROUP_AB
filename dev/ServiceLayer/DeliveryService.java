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


    //return a response with the appropriate delieryToSend, or with an error massege
    public Response getDeliveryInfo(int deliveryNum){
        try
        {
            Delivery dd = df.getDelivery(deliveryNum);
            DeliveryToSend ds = new DeliveryToSend(dd);

            return new Response(ds, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    
    }



    // returns a response with the new deliveryNumber or error if occurs
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

    //returns Response with deliveryNumber if it was removed seccssesfully, and with error message if not
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

    //returns response with deliveryNumber, or error message if error occurs
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

    //returns response with deliveryNumber, or error message if error occurs
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

    //returns response with deliveryNumber, or error message if error occurs
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

    //returns response with deliveryNumber, or error message if error occurs
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

    //returns response with deliveryNumber, or error message if error occurs
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
    //returns response with DstDocToSend, or error message if error occurs
    public Response getDestinationDocument(int deliveryNum , int docNumber){
        try
        {
            DstDoc dd = df.getDstDoc(deliveryNum,docNumber);
            return new Response(new DstDocToSend(dd), null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    //returns response with DstDocToSend, or error message if error occurs
    public Response addDestinationDoc(int deliveryNum ,List<Integer> items, String adderss,LocalDateTime estimatedArrivalTime){
        try
        {
            DstDoc dd = df.addDstDoc(deliveryNum,items,adderss,estimatedArrivalTime);
            return new Response(dd.getDocNumber(), null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }
    //returns response with deliveryNumber, or error message if error occurs
    public Response removeDestinationDoc(int deliveryNum , int docNumber){
        try
        {
            df.removeDstDoc(deliveryNum,docNumber);
            return new Response(docNumber, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    //by deliveryNumber and address
    //returns response with DstDocToSend, or error message if error occurs
    public Response getDestinationDocument(int deliveryNum, String address){
        try
        {
            DstDoc dd = df.getDstDoc(deliveryNum,address);
            return new Response(new DstDocToSend(dd), null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }    
    }

    public Response getSiteName(int deliveryNum){
        try
        {
            String s = df.getDelivery(deliveryNum).getSite().getAddress();
            return new Response(s, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }
    //returns response with deliveryNumber, or error message if error occurs
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

    //returns response with deliveryNumber, or error message if error occurs
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

    //returns response with deliveryNumber, or error message if error occurs
    public Response approveDelivery(int deliveryNum){
        try
        {
            df.approveDelivery(deliveryNum);
            return new Response(deliveryNum, null);
        }
        catch(Exception e)
        {
            return new Response(null, e.getMessage());
        } 
    }



}
