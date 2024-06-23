package BussinessLayer;
import java.time.LocalDateTime;
import java.util.*;

public class Truck {
    private int licenseNumber;
    private String model;
    private double weightWithoutCargo;
    private double maxWeight;
    private String licenseCategory;
    private DeliveryDates futureDeliveryDates;




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
}

public void removeDelivery(int deliveryNumber)
{
    futureDeliveryDates.removeDelivery(deliveryNumber);
}
 


public boolean isAvailableForReplace(Delivery d)
{
    return futureDeliveryDates.isAvailableForReplace(d);
}


}