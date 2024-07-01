package DataAccessLayer;

public class TruckToDeliveryDTO {

    private int truckNumber;
    private int deliveryNumber;
    private TruckToDeliveryController controller;
    public TruckToDeliveryDTO(int truckNumber,int deliveryNumber)
    {
        this.truckNumber=truckNumber;
        this.deliveryNumber=deliveryNumber;
        this.controller=new TruckToDeliveryController();
    }

    public int getTruckNumber() {
        return truckNumber;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }
    public boolean add()
    {
        return this.controller.add(this);
    }

    public boolean remove()
    {
        return this.controller.remove(this);
    }

}
