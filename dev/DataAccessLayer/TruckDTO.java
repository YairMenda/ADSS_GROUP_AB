package DataAccessLayer;

import java.util.LinkedList;
import java.util.List;

public class TruckDTO {
    private int licenseNumber;
    private String model;
    private double weightWitoutCargo;
    private double maxWeight;
    private String licenseCategory;
    private TruckController controller;

    private List<TruckToDeliveryDTO> truckTODelivery;
    public TruckDTO(int licenseNumber,String model,double weightWitoutCargo,double maxWeight,String licenseCategory)
    {
        this.licenseNumber=licenseNumber;
        this.model=model;
        this.weightWitoutCargo=weightWitoutCargo;
        this.maxWeight=maxWeight;
        this.licenseCategory=licenseCategory;
        this.controller=new TruckController();
        this.truckTODelivery=new LinkedList<TruckToDeliveryDTO>();
    }

    public TruckDTO(int licenseNumber,String model,double weightWitoutCargo,double maxWeight,String licenseCategory,List<TruckToDeliveryDTO> td)
    {
        this.licenseNumber=licenseNumber;
        this.model=model;
        this.weightWitoutCargo=weightWitoutCargo;
        this.maxWeight=maxWeight;
        this.licenseCategory=licenseCategory;
        this.controller=new TruckController();
        this.truckTODelivery=td;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public String getModel() {
        return model;
    }

    public double getWeightWitoutCargo() {
        return weightWitoutCargo;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public boolean addNewTruck()
    {
        return controller.add(this);
    }

    public List<TruckToDeliveryDTO> getTruckTODelivery() {
        return truckTODelivery;
    }

    public boolean addNewDelivery(int deliveryNumber)
    {
         TruckToDeliveryDTO td = new TruckToDeliveryDTO(this.getLicenseNumber(),deliveryNumber);
         this.truckTODelivery.add(td);
         return td.add();
    }

    public boolean removeDelivery(int deliveryNumber)
    {
        for (TruckToDeliveryDTO td : this.getTruckTODelivery())
            if (td.getDeliveryNumber() == deliveryNumber) {
                this.truckTODelivery.remove(td);
                return td.remove();
            }

            return false;
    }
}
