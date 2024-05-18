package ServiceLayer;

import BussinessLayer.DstDoc;
import java.util.List;

public class DstDocToSend {
    private int docNumber;
    private int deliveryNumber;
    private List<Integer> items;
    private SiteToSend destination;
    

    public DstDocToSend(DstDoc doc){
        this.docNumber = doc.getDocNumber();
        this.deliveryNumber=doc.getDeliveryNumber();
        this.items=doc.getItems();
        this.destination=new SiteToSend(doc.getDestination());
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
}
