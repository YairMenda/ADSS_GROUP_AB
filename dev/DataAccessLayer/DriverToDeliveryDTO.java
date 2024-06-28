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

    public boolean delete(){
        return controller.delete(this);
    }
}
