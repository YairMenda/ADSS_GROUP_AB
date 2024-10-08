package ServiceLayer;
import BussinessLayer.*;

import java.time.LocalDateTime;
import java.util.*;


public class DeliveryToSend {

    private int deliveryNumber;
    private LocalDateTime date;
    private LocalDateTime departureTime;
    private int truckNumber;
    private double truckWeight;
    private String driverID;
    private SiteToSend origin;
    private Delivery.status deliveryStatus;
    private List<DstDocToSend> destinationDocs;
    private List<Double> truckWeightHistory;

    private LocalDateTime endTime;

    public DeliveryToSend(Delivery other)
    {
        this.deliveryNumber=other.getDeliveryNumber();
        this.date=other.getDate();
        this.departureTime=other.getDepartureTime();
        this.truckNumber=other.getTruckNumber();
        this.driverID=other.getDriverID();
        this.origin=new SiteToSend(other.getOrigin());
        this.truckWeight = other.getTruckWeight();
        this.truckWeightHistory = new LinkedList<Double>(other.getTruckWeightHistory());
        destinationDocs= new LinkedList<>();
        List<DstDoc> docs = other.geDstDocs();
        for(DstDoc dd:docs){
            destinationDocs.add(new DstDocToSend(dd));
        }
        this.deliveryStatus=other.getDeliveryStatus();
        this.endTime = other.getEndTime();

    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getTruckNumber() {
        return truckNumber;
    }

    public void setTruckNumber(int truckNumber) {
        this.truckNumber = truckNumber;
    }

    public double getTruckWeight() {
        return truckWeight;
    }

    public void setTruckWeight(double truckWeight) {
        this.truckWeight = truckWeight;
    }

    public String getDriverName() {
        return driverID;
    }

    public void setDriverName(String driverID) {
        this.driverID = driverID;
    }

    public SiteToSend getOrigin() {
        return origin;
    }

    public void setOrigin(SiteToSend origin) {
        this.origin = origin;
    }

    public Delivery.status getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Delivery.status deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "Delivery Information :  [ DeliveryNumber = " + deliveryNumber + ", date = " + date + ", departureTime = "
                + departureTime + ", estimated time of arrival = " + endTime +", truckNumber = " + truckNumber + ", truckWeight = " + truckWeight + ", driverID = "
                + driverID + ", origin = " + origin +  ", deliveryStatus = " + deliveryStatus + ",\n"
                + "Truck weight history -  " +truckWeightHistory + ",\n " 
                + "Destination Documents - "+ destinationDocs + "]";
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
