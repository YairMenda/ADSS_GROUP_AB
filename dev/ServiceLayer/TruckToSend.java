package ServiceLayer;

public class TruckToSend {

    private int licenseNumber;
    private String model;
    private double weightWithoutCargo;
    private double maxWeight;
    private char licenseCategory;
    private boolean availability;




public TruckToSend(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,char licenseCategory)
{
   this.licenseNumber=licenseNumber;
   this.model=model;
   this.weightWithoutCargo=weightWithoutCargo;
   this.maxWeight=maxWeight;
   this.licenseCategory=licenseCategory;
   this.availability=false;
}

}
