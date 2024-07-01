package DataAccessLayer;

public class DriverToDeliveryDTO {

    private String driverID;
    private int deliveryNumber;
    private DriverToDeliveryController controller;

    public DriverToDeliveryDTO(String driverID, int deliveryNumber){
        this.driverID = driverID;
        this.deliveryNumber = deliveryNumber;
    }


    public boolean add(){
        return controller.add(this);
    }
    public boolean remove(){
        return controller.remove(this);
    }
    public String getDriverID() {
        return driverID;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }
}
