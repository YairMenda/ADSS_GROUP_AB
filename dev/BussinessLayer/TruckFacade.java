package BussinessLayer;
import java.time.LocalDateTime;
import java.util.*;

public class TruckFacade {

private List<Truck> trucks;

public TruckFacade(List<Truck > list){
    this.trucks=new LinkedList<Truck>(list);
}


public List<Truck> getTrucks(){
    return trucks;
}

public Truck getTruck(int licenseNumber) throws Exception{
    for(Truck t:trucks){
        if (t.getLicenseNumber()==licenseNumber)
            return t;
    }
    throw new Exception("Truck doesn't exist");
    
}

public List<Truck> getAvialibleTrucks(LocalDateTime date) throws Exception{
    List<Truck> l = new LinkedList<>();
    for (Truck t:trucks){
        if (t.isAvailable(date)){
            l.add(t);
        }
    }
    if (l.size()==0)
        throw new Exception("no available trucks in this date");
    return l;
}


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


public double getMaxWeight(int licenseNumber) throws Exception{
    return getTruck(licenseNumber).getMaxWeight();
}



public boolean addDelivery(int licenseNumber, LocalDateTime d) throws Exception{
    Truck t = getTruck(licenseNumber);
    if(t.isAvailable(d)){
        t.addDelivery(d);
        return true;
    }
    else {
        throw new Exception("Truck " + licenseNumber + "isn't availible on that date");
    }
    
}

public void removeDelivery(int licenseNumber, LocalDateTime d) throws Exception{
    Truck t = getTruck(licenseNumber);
    t.removeDelivery(d);
    
}

public void deliveryAcomplishment(int licenseNumber, LocalDateTime d) throws Exception{
         Truck t = getTruck(licenseNumber);
         t.deliveryAcomplishment(d);
    }




public String getLicenseCat(int licenseNumber) throws Exception{
    return getTruck(licenseNumber).getLicenseCategory();
}    



public boolean isAvailable(int truckNumber,LocalDateTime date) throws Exception
{
        return getTruck(truckNumber).isAvailable(date);
    }


}
