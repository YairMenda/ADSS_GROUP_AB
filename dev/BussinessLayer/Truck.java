package BussinessLayer;
import java.util.*;
public class Truck {
    private int licenseNumber;
    private String model;
    private double weightWithoutCargo;
    private double maxWeight;
    private char licenseCategory;
    private boolean availability;




public Truck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,char licenseCategory)
{
   this.licenseNumber=licenseNumber;
   this.model=model;
   this.weightWithoutCargo=weightWithoutCargo;
   this.maxWeight=maxWeight;
   this.licenseCategory=licenseCategory;
   this.availability=false;
}




}
