package ServiceLayer;
import BussinessLayer.*;


public class TruckToSend {

    private int licenseNumber;
    private String model;
    private double weightWithoutCargo;
    private double maxWeight;
    private String licenseCategory;
    private DeliveryDatesToSend dates;




public TruckToSend(Truck other)
{
   this.licenseNumber=other.getLicenseNumber();
   this.model=other.getModel();
   this.weightWithoutCargo=other.getWeightWithoutCargo();
   this.maxWeight=other.getMaxWeight();
   this.licenseCategory=other.getLicenseCategory();
   this.dates= new DeliveryDatesToSend(other.getFutureDeliveryDates());
   
}


public int getLicenseNumber() {
    return licenseNumber;
}

public String getModel() {
    return model;
}



public double getWeightWithoutCargo() {
    return weightWithoutCargo;
}


public double getMaxWeight() {
    return maxWeight;
}


public String getLicenseCategory() {
    return licenseCategory;
}
public DeliveryDatesToSend getDates(){
    return dates;
}

}
