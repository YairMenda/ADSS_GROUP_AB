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

}
