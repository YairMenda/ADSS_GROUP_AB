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


    /// <summary>
    /// the method adds new delivery to the system
    /// </summary>
    /// <param name="depTime"> the departure time of the delivery</param>
    ///<param name="licenseNumber"> the license number of the truck</param>
    ///<param name="driverID"> the driver's ID</param>
    ///<param name="originAdress"> the origin adress(from where is the delivery)</param>
    /// <returns>the new delivery, throws exception if fails</returns>

    /*public Delivery addNewDelivery(LocalDateTime depTime , int licenseNumber, String driverID ,String originAddress) throws Exception{

    if (!df.isAvailableNew(driverID,depTime))
        throw new Exception("driver with id - " + driverID + " Doesn't exist Or Doesn't Aval");

    if (!tf.isAvailableNew(licenseNumber,depTime))
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
    }*/

    public Delivery addNewDelivery(LocalDateTime depTime , int licenseNumber, String driverID ,String originAddress) throws Exception{
        if(!df.avialableShift(driverID,depTime) || !df.availableDeliveryDate(driverID,depTime))
        {
            throw new Exception("Driver - " + driverID + "  Doesnt avialable at this date -" + depTime);
        }

        if(!tf.availableDeliveryDate(licenseNumber,depTime))
        {
            throw new Exception("Truck - " + licenseNumber + "  Doesnt avialable at this date -" + depTime);
        }

        if (!df.hasLicense(driverID,tf.getLicenseCat(licenseNumber)))
            throw new Exception("Driver with id - " + driverID + " can't drive in truck - "+licenseNumber+" because he doen't the relevant license");

        Site origin = sf.getSite(originAddress);
        this.currentID++;
        Delivery newDelivery = new Delivery(this.currentID, LocalDateTime.now() , depTime, licenseNumber, driverID, origin);
        this.deliveries.put(this.currentID ,newDelivery);

        tf.addDelivery(licenseNumber, newDelivery);
        df.addDelivery(driverID, newDelivery);

        return newDelivery;


    }

    /// <summary>
    /// the method updates the weight of a delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery we what to update </param>
    ///<param name="weight"> the new weight of the delivery</param>
    /// <returns>true if succeeded, throws exception if fails</returns>
public boolean weightUpdate(int deliveryNumber,double weight) throws Exception
{
    Delivery currDelivery = getDelivery(deliveryNumber);
    if (! (weight <= tf.getMaxWeight(currDelivery.getTruckNumber())))
        throw new Exception("weight out of bounderies, please remove products, change truck or destination");
    
    currDelivery.setTruckWeight(weight);
    return true;

    }

    /// <summary>
    /// the method gets a specific delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery we want to get</param>
    /// <returns>the delivery, throws exception if fails</returns>
public Delivery getDelivery(int deliveryNumber) throws Exception
{
    Delivery d = this.deliveries.get(deliveryNumber);
    if (d == null)
        throw new Exception("Delivery with number - " + deliveryNumber + " doesnt exist");
    return d;
}


    /// <summary>
    /// the method add new destination document to a specific delivery and adds a new destination to the delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery we want to add to</param>
    /// <param name="items"> list of items we want to add</param>
    /// <param name="address"> the address we want to deliver those items</param>
    /// <returns>the new document, throws exception if fails</returns>
public DstDoc addDstDoc(int deliveryNumber, List<Integer> items ,String address,LocalDateTime estimatedArrivalTime ) throws Exception
{
        validEstimatedTime(deliveryNumber,getDelivery(deliveryNumber).getDriverID(),getDelivery(deliveryNumber).getTruckNumber(), estimatedArrivalTime);
        this.currentDocNumber++;
        DstDoc dd = new DstDoc(currentDocNumber, deliveryNumber, items, sf.getSite(address), estimatedArrivalTime);
        getDelivery(deliveryNumber).addDstDoc(dd);
        return dd;
}

    /// <summary>
    /// the method removes destination document and destination from a specific delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery we want to add to</param>
    /// <param name="dstDocNumber"> the number of the document we what to remove</param>
    /// <returns>true or throws exception if fails</returns>
public boolean removeDstDoc(int deliveryNumber,  int dstDocNumber) throws Exception
{
    return getDelivery(deliveryNumber).removeDstDoc(dstDocNumber);
}


    /// <summary>
    /// the method replaces a truck to a specific delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery which we want to replace the truck </param>
    /// <param name="newTruckNumber"> the new truck we want</param>
    /// <returns>true or throws exception if fails</returns>
public boolean replaceTruck(int deliveryNumber, int newTruckNumber) throws Exception
{
    Delivery currDelivery = getDelivery(deliveryNumber);

    if (tf.availableForReplace(newTruckNumber ,currDelivery)){
        tf.removeDelivery(currDelivery.getTruckNumber(), deliveryNumber);
        currDelivery.replaceTruck(newTruckNumber);
        tf.addDelivery(newTruckNumber, currDelivery);
        return true;
    }
    else
        return false;
}


    /// <summary>
    /// the method removes products from a delivery(only for a specific destination)
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <param name="dstDocNumber"> the number of the destination document</param>
    /// <param name="deletedProducts"> list of items we want to remove</param>
    /// <returns>throws exception if fails</returns>
public void removeProducts(int deliveryNumber,int dstDocNumber ,List<Integer> deletedProducts) throws Exception
{
    getDelivery(deliveryNumber).removeProductsByDocNumber(dstDocNumber, deletedProducts);
}


    /// <summary>
    /// the method changes the status to "inProgress" to a delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <returns>throws exception if fails</returns>
public void inProgress(int deliveryNumber) throws Exception
{
    getDelivery(deliveryNumber).inProgressDelivery();
}

    /// <summary>
    /// the method changes the status to "complete" to a delivery,and updates the avialabillity of the truck and the driver
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <returns>throws exception if fails</returns>
public void completeDelivery(int deliveryNumber) throws Exception
{
    Delivery d = getDelivery(deliveryNumber);
    d.completeDelivery();
    tf.deliveryAcomplishment(d.getTruckNumber(),deliveryNumber);
    df.deliveryAcomplishment(d.getDriverID(), deliveryNumber);

}
    /// <summary>
    /// the method changes the status to "waiting" to a delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <returns>throws exception if fails</returns>
public void disapproveDelivery(int deliveryNumber) throws Exception
{
    getDelivery(deliveryNumber).disapproveDelivery();
}

    /// <summary>
    /// the method removes a delivery,and updates the avialabillity of the truck and the driver
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <returns>throws exception if fails</returns>
public void removeDelivery(int deliveryNumber) throws Exception
{
    Delivery currDelivery = getDelivery(deliveryNumber);
    if (currDelivery.getDeliveryStatus() == status.inProgress || currDelivery.getDeliveryStatus() == status.complete)
        throw new Exception("Cant remove ongoing or finished delivery");

    df.removeDelivery(currDelivery.getDriverID(), deliveryNumber);
    tf.removeDelivery(currDelivery.getTruckNumber(), deliveryNumber);
    deliveries.remove(deliveryNumber);
}
    /// <summary>
    /// the method removes a destination and deletes the destination document of this destination
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <param name="address"> the address of the destination we want to remove</param>
    /// <returns>throws exception if fails</returns>
public void removeDestination(int deliveryNum,String address) throws Exception
{
    deliveries.get(deliveryNum).remove(sf.getSite(address));
}

    /// <summary>
    /// the method gets a specific destination document
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <param name="docNumber"> the number of the destination document we want</param>
    /// <returns>the destination document, throws exception if fails</returns>
public DstDoc getDstDoc(int deliveryNumber,int docNumber) throws Exception{
    return getDelivery(deliveryNumber).getDstDocByNumber(docNumber);
}
    /// <summary>
    /// the method gets a specific destination document
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <param name="address"> the address of the destination document we want</param>
    /// <returns>the destination document, throws exception if fails</returns>
public DstDoc getDstDoc(int deliveryNumber,String address) throws Exception{
    return getDelivery(deliveryNumber).getDstDocByNumber(address);
}
    /// <summary>
    /// the method changes the status to "approved" to a specific delivery
    /// </summary>
    /// <param name="deliveryNumber"> the number of the delivery</param>
    /// <returns>throws exception if fails</returns>
public void approveDelivery(int deliveryNumber) throws Exception
{
    getDelivery(deliveryNumber).approveDelivery();
}

public void validEstimatedTime(int deliveryNumber, String driverID,int truckNumber,LocalDateTime estimatedTime) throws Exception
{
      if (!df.estimatedArrivalwithinShift(driverID,estimatedTime))
          throw new Exception("Estimated arrival is not within the driver shift bounderies");

      if (!getDelivery(deliveryNumber).depTimeTOEstimatedTime(estimatedTime))
          throw new Exception("Estimated arrival is not within the driver shift bounderies");

      if (!tf.isAvailableByNewDstDoc(deliveryNumber,truckNumber,estimatedTime))
          throw new Exception("The new estimated time collides with a different delivery of the truck : " + truckNumber);

      if(!df.isAvailableByNewDstDoc(deliveryNumber,driverID,estimatedTime))
          throw new Exception("The new estimated time collides with a different delivery of the driver : " + driverID);

}
}
