package BussinessLayer;
import java.io.EOFException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import BussinessLayer.Delivery.status;


public class DeliveryFacade {

private Map<Integer , Delivery> deliveries;
private TruckFacade tf;
private DriverFacade df;
private SiteFacade sf;
private Integer currentID;
private Integer currentDocNumber;

public DeliveryFacade(TruckFacade tf , DriverFacade df, SiteFacade sf)
{
    this.deliveries = new HashMap<>();
    this.tf = tf;
    this.df = df;
    this.sf = sf;
    this.currentID = 0;
    this.currentDocNumber = 0 ;
}



public Delivery addNewDelivery(LocalDateTime depTime , int licenseNumber, String driverID ,String originAddress) throws Exception{
    
    if (!df.isAvailable(driverID,depTime))
        throw new Exception("driver with id - " + driverID + " Doesn't exist Or Doesn't Aval");
    
    if (!tf.isAvailable(licenseNumber,depTime))
        throw new Exception("Truck with License Number - " + licenseNumber + " Doesn't exist Or Doesn't Aval");
    if (!df.hasLicense(driverID,tf.getLicenseCat(licenseNumber)))
        throw new Exception("Driver with id - " + driverID + " can't drive in truck - "+licenseNumber+" because he doen't the relevant license");
    Site origin = sf.getSite(originAddress);
    this.currentID++;
    Delivery newDelivery = new Delivery(this.currentID, LocalDateTime.now() , depTime, licenseNumber, driverID, origin);
    this.deliveries.put(this.currentID ,newDelivery);
    tf.addDelivery(licenseNumber, depTime);
    df.addDelivery(driverID, depTime);
    return newDelivery;
    }

public boolean weightUpdate(int deliveryNumber,double weight) throws Exception
{
    Delivery currDelivery = getDelivery(deliveryNumber);
    if (! (weight <= tf.getMaxWeight(currDelivery.getTruckNumber())))
        throw new Exception("weight out of bounderies, please remove products, change truck or destination");
    
    currDelivery.setTruckWeight(weight);
    //currDelivery.approveDelivery();
    return true;

    }

public Delivery getDelivery(int deliveryNumber) throws Exception
{
    Delivery d = this.deliveries.get(deliveryNumber);
    if (d == null)
        throw new Exception("Delivery with number - " + deliveryNumber + " doesnt exist");
    return d;
}

public DstDoc addDstDoc(int deliveryNumber, List<Integer> items ,String address ) throws Exception
{
    this.currentDocNumber++;
    DstDoc dd = new DstDoc(currentDocNumber, deliveryNumber, items, sf.getSite(address));
    getDelivery(deliveryNumber).addDstDoc(dd);
    return dd;

} 


public boolean removeDstDoc(int deliveryNumber,  int dstDocNumber) throws Exception
{
    return getDelivery(deliveryNumber).removeDstDoc(dstDocNumber);
}

public boolean replaceTruck(int deliveryNumber, int newTruckNumber) throws Exception
{
    Delivery currDelivery = getDelivery(deliveryNumber);
    LocalDateTime currDate = currDelivery.getDate();

    if (tf.isAvailable(newTruckNumber ,currDate)){
        tf.removeDelivery(currDelivery.getTruckNumber(), currDate);
        currDelivery.replaceTruck(newTruckNumber);
        tf.addDelivery(newTruckNumber, currDate);
        return true;
    }
    else
        return false;
}

public void removeProducts(int deliveryNumber,int dstDocNumber ,List<Integer> deletedProducts) throws Exception
{
    getDelivery(deliveryNumber).removeProductsByDocNumber(dstDocNumber, deletedProducts);
}

public void inProgress(int deliveryNumber) throws Exception
{
    getDelivery(deliveryNumber).inProgressDelivery();
}

public void completeDelivery(int deliveryNumber) throws Exception
{
    Delivery d = getDelivery(deliveryNumber);
    d.completeDelivery();
    tf.deliveryAcomplishment(d.getTruckNumber(),d.getDepartureTime());
    df.deliveryAcomplishment(d.getDriverID(), d.getDepartureTime());

}

public void disapproveDelivery(int deliveryNumber) throws Exception
{
    getDelivery(deliveryNumber).disapproveDelivery();
}

public void removeDelivery(int deliveryNumber) throws Exception
{
    Delivery currDelivery = getDelivery(deliveryNumber);
    if (currDelivery.getDeliveryStatus() == status.inProgress || currDelivery.getDeliveryStatus() == status.complete)
        throw new Exception("Cant remove ongoing or finished delivery");
    
    LocalDateTime removeDate = currDelivery.getDepartureTime();
    df.removeDelivery(currDelivery.getDriverID(), removeDate);
    tf.removeDelivery(currDelivery.getTruckNumber(), removeDate);

    deliveries.remove(deliveryNumber);

}

public void removeDestination(int deliveryNum,String address) throws Exception
{
    deliveries.get(deliveryNum).remove(sf.getSite(address));
}

public DstDoc getDstDoc(int deliveryNumber,int docNumber) throws Exception{
    return getDelivery(deliveryNumber).getDstDocByNumber(docNumber);
}

public DstDoc getDstDoc(int deliveryNumber,String address) throws Exception{
    return getDelivery(deliveryNumber).getDstDocByNumber(address);
}

public void approveDelivery(int deliveryNumber) throws Exception
{
    getDelivery(deliveryNumber).approveDelivery();
}
}
