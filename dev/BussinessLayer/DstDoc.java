package BussinessLayer;
import java.util.*;

public class DstDoc {
    private int docNumber;
    private int deliveryNumber;
    private List<Integer> items;
    private Site destination;
    

    public DstDoc(int docNumber, int deliveryNumber, List<Integer> items,Site s){
        this.docNumber=docNumber;
        this.deliveryNumber=deliveryNumber;
        this.items=items;
        this.destination = s;
    }

    public void removeProducts(List<Integer> deletedProducts)
    {
        for (Integer i : deletedProducts)
            items.remove(i);
    }

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
