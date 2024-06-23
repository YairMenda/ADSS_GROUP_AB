package BussinessLayer;
import java.time.LocalDateTime;
import java.util.*;

public class TruckFacade {

private List<Truck> trucks;

public TruckFacade(List<Truck > list){
    this.trucks=new LinkedList<Truck>(list);
}


    /// <summary>
    /// the method gets all the trucks on the system
    /// </summary>
    /// <returns>al list of trucks</returns>
public List<Truck> getTrucks(){
    return trucks;
}

    /// <summary>
    /// the method gets a specific truck
    /// </summary>
    /// <param name="licenseNumber"> the license number of the truck we want</param>
    /// <returns>a truck,throws exception if fails</returns>
public Truck getTruck(int licenseNumber) throws Exception{
    for(Truck t:trucks){
        if (t.getLicenseNumber()==licenseNumber)
            return t;
    }
    throw new Exception("Truck doesn't exist");
    
}

    /// <summary>
    /// the method gets trucks by availability
    /// </summary>
    /// <param name="date"> the date we need a truck</param>
    /// <returns>list of trucks, throws exception if fails</returns>
public List<Truck> getAvialibleTrucks(LocalDateTime date) throws Exception{
    List<Truck> l = new LinkedList<>();
    for (Truck t:trucks){
        if (t.isAvailableByDelivery(date)){
            l.add(t);
        }
    }
    if (l.size()==0)
        throw new Exception("no available trucks in this date");
    return l;
}

    /// <summary>
    /// the method adds a new truck to the system
    /// </summary>
    /// <param name="licenseNumber"> the license number of the new truck</param>
    /// <param name="model"> the model of the new truck</param>
    /// <param name="weightWithoutCargo">the weight of the truck without cargo</param>
    /// <param name="maxWeight"> the maximum weight that the truck can carry</param>
    /// <param name="licenseCategory"> the license category of the new truck</param>
    /// <returns>the new truck, throws exception if fails</returns>
public Truck addTruck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,String licenseCategory) throws Exception{

    boolean exist=false;
    for (Truck truck:trucks){
        if (licenseNumber==truck.getLicenseNumber()){
            exist=true;
        }
    }
    if(!exist){
        Truck t = new Truck(licenseNumber, model,  weightWithoutCargo, maxWeight, licenseCategory);
        trucks.add(t);
        return t;
    }
    else{
        throw new Exception("Truck with the same license number already exist");
    }    
}

    /// <summary>
    /// the method gets the maximum carry of a specific truck
    /// </summary>
    /// <param name="licenseNumber"> the license number of the truck we want to get the weight of</param>
    /// <returns>the maximum weight of the truck, throws exception if fails</returns>
public double getMaxWeight(int licenseNumber) throws Exception{
    return getTruck(licenseNumber).getMaxWeight();
}


    /// <summary>
    /// the method updates the availability of a truck after he was scheduled to a new delivery
    /// </summary>
    /// <param name="licenseNumber"> the license number of the truck we want to update</param>
    /// <param name="d"> the date of the delivery</param>
    /// <returns>true, throws exception if fails</returns>
public void addDelivery(int licenseNumber, Delivery d) throws Exception{
    getTruck(licenseNumber).addDelivery(d);
}

    /// <summary>
    /// the method updates the availability of a truck after he was removed from a delivery
    /// </summary>
    /// <param name="licenseNumber"> the license number of the truck we want to update</param>
    /// <param name="d"> the date of the delivery</param>
    /// <returns>throws exception if fails</returns>
public void removeDelivery(int licenseNumber, int deliveryNumber) throws Exception{
    getTruck(licenseNumber).removeDelivery(deliveryNumber);
}
    /// <summary>
    /// the method updates the availability of a truck after the delivery was completed
    /// </summary>
    /// <param name="licenseNumber"> the license number of the truck we want to update</param>
    /// <param name="d"> the date of the delivery</param>
    /// <returns>throws exception if fails</returns>
public void deliveryAcomplishment(int licenseNumber, int deliveryNumber) throws Exception{
         getTruck(licenseNumber).deliveryAcomplishment(deliveryNumber);
    }
public boolean availableForReplace(int licencseNumber, Delivery d) throws Exception
{
    return getTruck(licencseNumber).isAvailableForReplace(d);
}


    /// <summary>
    /// the method the license category of a specific truck
    /// </summary>
    /// <param name="licenseNumber"> the license number of the truck we want to get</param>
    /// <returns>the truck's category, throws exception if fails</returns>
public String getLicenseCat(int licenseNumber) throws Exception{
    return getTruck(licenseNumber).getLicenseCategory();
}


    /// <summary>
    /// the method checks if a truck is available on a specific date
    /// </summary>
    /// <param name="licenseNumber"> the license number of the truck we want to check</param>
    /// <param name="date"> the date we want to check</param>
    /// <returns>true or false, throws exception if fails</returns>
public boolean isAvailableByNewDstDoc(int deliveryNumber,int truckNumber,LocalDateTime newEstimatedTime) throws Exception
{
        return getTruck(truckNumber).isAvailableByEstimatedTime(deliveryNumber,newEstimatedTime);
}

public boolean availableDeliveryDate(int licenseNumber,LocalDateTime date) throws Exception
{
    return getTruck(licenseNumber).isAvailableByDelivery(date);
}


}
