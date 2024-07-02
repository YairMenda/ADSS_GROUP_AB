package DataAccessLayer;

public class DeliveryToWeightDTO {

    private int deliveryNumber;
    private double truckWeight;
    private DeliveryToWeightController controller;
    public DeliveryToWeightDTO(int deliveryNumber,double truckWeight)
    {
        this.deliveryNumber=deliveryNumber;
        this.truckWeight=truckWeight;
        this.controller=new DeliveryToWeightController();
    }

    public boolean add()
    {
        return this.controller.add(this);
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public double getTruckWeight() {
        return truckWeight;
    }
}
