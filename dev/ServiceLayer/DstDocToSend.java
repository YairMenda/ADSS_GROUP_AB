package ServiceLayer;

import BussinessLayer.DstDoc;

import java.time.LocalDateTime;
import java.util.List;

public class DstDocToSend {
    private int docNumber;
    private int deliveryNumber;
    private List<Integer> items;
    private SiteToSend destination;

    private LocalDateTime endTime;

    public DstDocToSend(DstDoc doc){
        this.docNumber = doc.getDocNumber();
        this.deliveryNumber=doc.getDeliveryNumber();
        this.items=doc.getItems();
        this.destination=new SiteToSend(doc.getDestination());
        this.endTime = doc.getEstimatedArrivalTime();
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

    public SiteToSend getDestination(){
        return destination;
    }

    @Override
    public String toString() {
        return "Document Number = " + docNumber + ", Delivery Number = " + deliveryNumber 
        + ", items : " + items
        + ", destination - " + destination + ", Estimated Arrival Time - "+endTime+"]";
    }

}
