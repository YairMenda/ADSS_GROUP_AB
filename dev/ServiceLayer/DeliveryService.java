package ServiceLayer;
import BussinessLayer.DeliveryFacade;

public class DeliveryService {
    private DeliveryFacade df;



    public DeliveryService(DeliveryFacade df){
        this.df=df;
    }
    //what parameters????
    public String addNewDelivery(){
        throw new UnsupportedOperationException();
    }

    public String removeDelivery(int deliveryNum){
        throw new UnsupportedOperationException();
    }


    public String updateWeight(int deliveryNum, double weight){
        throw new UnsupportedOperationException();
    }

    public String replaceTruck(int deliveryNum , int truckNum){
        throw new UnsupportedOperationException();
    }


    public String removeDestination(int deliveryNum , String adress){
        throw new UnsupportedOperationException();
    }

    public String removeProduct(int deliveryNum , String adress, int item){
        throw new UnsupportedOperationException();
    }

    public String completeDelivery(int deliveryNum){
        throw new UnsupportedOperationException();
    }

    //only by docNumber
    public String getDestinationDocument(int docNumber){
        throw new UnsupportedOperationException();
    }

    //by deliveryNumber and address
    public String getDestinationDocument(int deliveryNum, String address){
        throw new UnsupportedOperationException();
    }



}
