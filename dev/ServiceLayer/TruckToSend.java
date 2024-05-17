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




public int getLicenseNumber() {
    return licenseNumber;
}




public void setLicenseNumber(int licenseNumber) {
    this.licenseNumber = licenseNumber;
}




public String getModel() {
    return model;
}




public void setModel(String model) {
    this.model = model;
}




public double getWeightWithoutCargo() {
    return weightWithoutCargo;
}




public void setWeightWithoutCargo(double weightWithoutCargo) {
    this.weightWithoutCargo = weightWithoutCargo;
}




public double getMaxWeight() {
    return maxWeight;
}




public void setMaxWeight(double maxWeight) {
    this.maxWeight = maxWeight;
}




public char getLicenseCategory() {
    return licenseCategory;
}




public void setLicenseCategory(char licenseCategory) {
    this.licenseCategory = licenseCategory;
}




public boolean isAvailability() {
    return availability;
}




public void setAvailability(boolean availability) {
    this.availability = availability;
}

}
