package BussinessLayer;
import java.util.*;

public class DstDoc {
    private int docNumber;
    private int deliveryNumber;
    private List<Integer> items;
    

    public DstDoc(int docNumber, int deliveryNumber, List<Integer> items){
        this.docNumber=docNumber;
        this.deliveryNumber=deliveryNumber;
        this.items=items;
    }
}
