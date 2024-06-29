package DataAccessLayer;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DeliveryDTO {
    private int deliveryNumber;
    private LocalDateTime date;
    private LocalDateTime departureTime;
    private int truckNumber;
    private double truckWeight;
    private String driverID;
    private String deliveryStatus;
    private List<DstDocDTO> destinationDocs;

    private LocalDateTime endTime;

    private DeliveryController controller;


    //site origin??????
    public DeliveryDTO(int deliveryNumber, LocalDateTime date, LocalDateTime departureTime, int truckNumber, double truckWeight, String driverID, String deliveryStatus, LocalDateTime endTime){
        this.deliveryNumber= deliveryNumber;
        this.date=date;
        this.departureTime= departureTime;
        this.truckNumber = truckNumber;
        this.truckWeight = truckWeight;
        this.driverID = driverID;
        this.deliveryStatus = deliveryStatus;
        destinationDocs = new LinkedList<>();
        this.endTime = endTime;
        this.controller = new DeliveryController();
    }


    public boolean setTruckNumber(int truckNumber) {
        this.truckNumber = truckNumber;
        return update();
    }

    public boolean setTruckWeight(int truckWeight){
        this.truckWeight = truckWeight;
        return update();
    }

    public boolean setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return update();
    }

    public boolean setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return update();
    }

    //need to check if to the function recives DstDoc or DstDocDTO
    public boolean addDstDoc(DstDocDTO ddd){
        destinationDocs.add(ddd);
        return true;//only for now
        //return ddd.add();

    }

    public boolean update(){
        return controller.update(this);
    }

    public boolean remove(){
        return controller.remove(this);
    }

    public boolean add(){
        return controller.add(this);
    }

    public int getDeliveryNumber(){
        return deliveryNumber;
    }

    public java.sql.Timestamp getSQLDate(){
        return java.sql.Timestamp.valueOf(date);
    }
    public java.sql.Timestamp getSQLDepartureTime(){
        return java.sql.Timestamp.valueOf(departureTime);
    }

    public LocalDateTime getDate(){
        return date;
    }
    public LocalDateTime getDepartureTime(){
        return departureTime;
    }

    public int getTruckNumber(){
        return truckNumber;
    }


    public double getTruckWeight() {
        return truckWeight;
    }

    public String getDriverID() {
        return driverID;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public java.sql.Timestamp getSQLEndTime(){
        return java.sql.Timestamp.valueOf(endTime);
    }
}
