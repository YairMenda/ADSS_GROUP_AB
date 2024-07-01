package BussinessLayer;
import DataAccessLayer.TruckDTO;

import java.time.LocalDateTime;
import java.util.*;

public class Truck {
    private int licenseNumber;
    private String model;
    private double weightWithoutCargo;
    private double maxWeight;
    private String licenseCategory;
    private DeliveryDates futureDeliveryDates;
    private TruckDTO tDTO;



public Truck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,String licenseCategory) throws Exception
{
   this.licenseNumber=licenseNumber;
   this.model=model;
   this.weightWithoutCargo=weightWithoutCargo;
   this.maxWeight=maxWeight;
   if (weightWithoutCargo < 0 || maxWeight < 0){
    throw new Exception("weight of truck must be a positive number");
   }
   this.licenseCategory=licenseCategory;
   this.futureDeliveryDates= new DeliveryDates();
   this.tDTO=new TruckDTO(this.licenseNumber,this.model,this.weightWithoutCargo,this.maxWeight,this.licenseCategory);
   this.tDTO.addNewTruck();

}

public Truck(TruckDTO tDTO,List<Delivery> deliveries)
{
    this.licenseNumber=tDTO.getLicenseNumber();
    this.model=tDTO.getModel();
    this.weightWithoutCargo=tDTO.getWeightWitoutCargo();
    this.maxWeight= tDTO.getMaxWeight();
    this.licenseCategory=tDTO.getLicenseCategory();
    this.tDTO = tDTO;
    this.futureDeliveryDates = new DeliveryDates(deliveries);
}
public int getLicenseNumber(){
    return licenseNumber;
}

public double getMaxWeight(){
    return maxWeight;
}

public String getLicenseCategory(){
    return licenseCategory;
}
public double getWeightWithoutCargo(){
    return weightWithoutCargo;
}
public String getModel(){
    return model;
}

public DeliveryDates getFutureDeliveryDates(){
     return futureDeliveryDates;
    }


public boolean isAvailableByEstimatedTime(int deliveryNumber , LocalDateTime estimatedTime)
{
        return futureDeliveryDates.isAvailableByEstimatedTime(deliveryNumber , estimatedTime);
}
    public boolean isAvailableByDelivery(LocalDateTime date){
        return futureDeliveryDates.isAvailableByNewDelivery(date);
    }

public void deliveryAcomplishment(int deliveryNumber){
         futureDeliveryDates.deliveryAcomplishment(deliveryNumber);
    }
    
public void addDelivery(Delivery d) throws Exception{
    futureDeliveryDates.addDelivery(d);
    this.tDTO.addNewDelivery(d.getDeliveryNumber());
}

public void removeDelivery(int deliveryNumber)
{
    futureDeliveryDates.removeDelivery(deliveryNumber);
    this.tDTO.removeDelivery(deliveryNumber);
}
 


public boolean isAvailableForReplace(Delivery d)
{
    return futureDeliveryDates.isAvailableForReplace(d);
}


    public TruckDTO gettDTO() {
        return tDTO;
    }
}