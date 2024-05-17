package ServiceLayer;

import java.util.List;

public class DstDocToSend {
    private int docNumber;
    private int deliveryNumber;
    private List<Integer> items;
    

    public DstDocToSend(int docNumber, int deliveryNumber, List<Integer> items){
        this.docNumber=docNumber;
        this.deliveryNumber=deliveryNumber;
        this.items=items;
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

}
