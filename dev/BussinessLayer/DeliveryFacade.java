package BussinessLayer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class DeliveryFacade {

private List<Delivery> deliveries;
private TruckFacade tf;
private DriverFacade df;
private SiteFacade sf;

public DeliveryFacade(TruckFacade tf , DriverFacade df, SiteFacade sf)
{
    this.deliveries = new LinkedList<Delivery>();
    this.tf = tf;
    this.df = df;
    this.sf = sf;
    //needs to handle Deliverys ID
}



public void addNewDelivery(Date date, Date depTime , int truckNumber, String driverName,Site origin){
    throw new UnsupportedOperationException();
}

public boolean weightUpdate(int deliveryNumber,double weight)
{
    Delivery currDelivery = getDelivery(deliveryNumber);
    return weight <= tf.getMaxWeight(currDelivery.getTruckNumber());
    
}

//assume delivery exists
public Delivery getDelivery(int deliveryNumber)
{
    for (Delivery d : deliveries)
        if (d.getDeliveryNumber() == deliveryNumber)
               return d;

    return null;

}

public boolean addDstDoc(int deliveryNumber, DstDoc dd)
{
    return getDelivery(deliveryNumber).addDstDoc(dd);
}

public boolean removeDstDoc(int deliveryNumber,  int dstDocNumber)
{
    return getDelivery(deliveryNumber).removeDstDoc(dstDocNumber);
}

public boolean replaceTruck(int deliveryNumber, int truckNumber)
{
    Delivery currDelivery = getDelivery(deliveryNumber);
    Date currDate = currDelivery.getDate();

    if (tf.isValidTruck(truckNumber ,currDate)){
        tf.removeDelivery(currDelivery.getTruckNumber(), currDate);
        currDelivery.replaceTruck(truckNumber);
        tf.addDelivery(truckNumber, currDate);
        return true;
    }
    else
        return false;
}

public void removeProducts(int deliveryNumber,int dstDocNumber ,List<Integer> deletedProducts)
{
    getDelivery(deliveryNumber).removeProductsByDocNumber(dstDocNumber, deletedProducts);
}
}
