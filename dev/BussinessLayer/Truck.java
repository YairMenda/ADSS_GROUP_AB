package BussinessLayer;
import java.util.*;

public class Truck {
    private int licenseNumber;
    private String model;
    private double weightWithoutCargo;
    private double maxWeight;
    private String licenseCategory;
    private DeliveryDates futureDeliveryDates;




public Truck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,String licenseCategory)
{
   this.licenseNumber=licenseNumber;
   this.model=model;
   this.weightWithoutCargo=weightWithoutCargo;
   this.maxWeight=maxWeight;
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


public boolean isAvailable(Date date){
        return futureDeliveryDates.isAvailable(date);
    }

public void deliveryAcomplishment(Date date){
         futureDeliveryDates.deliveryAcomplishment(date);
    }

//assumes isAvailable(date)==true
public void addDelivery(Date date){
    futureDeliveryDates.addDelivery(date);
}

public void removeDelivery(Date date)
{
    futureDeliveryDates.removeDelivery(date);
}
 
}
