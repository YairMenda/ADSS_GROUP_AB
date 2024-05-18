package BussinessLayer;
import java.util.*;

public class TruckFacade {

private List<Truck> trucks;

public TruckFacade(){
    this.trucks=new LinkedList<Truck>();
}


public List<Truck> getTrucks(){
    return trucks;
}

public Truck getTruck(int licenseNumber){
    for(Truck t:trucks){
        if (t.getLicenseNumber()==licenseNumber)
            return t;
    }
    return null;
}

public List<Truck> getAvialibleTrucks(Date date){
    List<Truck> l = new LinkedList<>();
    for (Truck t:trucks){
        if (t.isAvailable(date)){
            l.add(t);
        }
    }
    return l;
}


public void addTruck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,String licenseCategory){

    boolean exist=false;
    for (Truck truck:trucks){
        if (licenseNumber==truck.getLicenseNumber()){
            exist=true;
        }
    }
    if(!exist)
        trucks.add(new Truck(licenseNumber, model,  weightWithoutCargo, maxWeight, licenseCategory));
}


public double getMaxWeight(int licenseNumber){
    return getTruck(licenseNumber).getMaxWeight();
}



public boolean addDelivery(int licenseNumber, Date d){
    Truck t = getTruck(licenseNumber);
    if(t.isAvailable(d)){
        t.addDelivery(d);
        return true;
    }
    return false;
}

public void removeDelivery(int licenseNumber, Date d){
    Truck t = getTruck(licenseNumber);
    t.removeDelivery(d);
    
}

public void deliveryAcomplishment(int licenseNumber, Date d){
         Truck t = getTruck(licenseNumber);
         t.deliveryAcomplishment(d);
    }




public String getLicenseCat(int licenseNumber){
    return getTruck(licenseNumber).getLicenseCategory();
}    

public boolean isValidTruck(int truckNumber,Date date)
{
    List<Truck> avalTrucks = getAvialibleTrucks(date);

    for (Truck t : avalTrucks)
    {
        if (t.getLicenseNumber() == truckNumber)
            return true;
    }

    return false;
}


}
