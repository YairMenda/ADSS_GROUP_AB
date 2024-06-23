package BussinessLayer;
import java.time.LocalDateTime;
import java.util.*;

public class DstDoc {
    private int docNumber;
    private int deliveryNumber;
    private List<Integer> items;
    private Site destination;

    private LocalDateTime estimatedArrivalTime;
    

    public DstDoc(int docNumber, int deliveryNumber, List<Integer> items,Site s,LocalDateTime arrivalTime) throws Exception{
        this.docNumber=docNumber;
        this.deliveryNumber=deliveryNumber;
        this.items=items;
        this.destination = s;
        if (s.isShopKeeperAvailable(arrivalTime)){
            this.estimatedArrivalTime = arrivalTime;
        }
        else { throw new Exception("There is no storekeeper available on destination : " + s.getAddress());}
    }

    public void removeProducts(List<Integer> deletedProducts)
    {
        for (Integer i : deletedProducts)
            items.remove(i);
    }

    public int getDocNumber() {
        return docNumber;
    }


    public void setDocNumber(int docNumber) {
        this.docNumber = docNumber;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public Site getDestination() {
        return destination;
    }

    public void setDestination(Site destination) {
        this.destination = destination;
    }

    public LocalDateTime getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }

    public void setEstimatedArrivalTime(LocalDateTime estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }
}