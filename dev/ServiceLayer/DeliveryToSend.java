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
    private List<SiteToSend> destinations;
    private Delivery.status deliveryStatus;
    private List<DstDocToSend> destinationDocs;

    public DeliveryToSend(Delivery other)
    {
        this.deliveryNumber=other.getDeliveryNumber();
        this.date=other.getDate();
        this.departureTime=other.getDepartureTime();
        this.truckNumber=other.getTruckNumber();
        this.driverID=other.getDriverID();
        this.origin=new SiteToSend(other.getOrigin());
        destinations= new LinkedList<>();
        List<Site> l = other.getDestinations();
        for(Site s:l){
            destinations.add(new SiteToSend(s));
        }
        destinationDocs= new LinkedList<>();
        List<DstDoc> docs = other.geDstDocs();
        for(DstDoc dd:docs){
            destinationDocs.add(new DstDocToSend(dd));
        }
        this.deliveryStatus=other.getDeliveryStatus();

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

    public List<SiteToSend> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<SiteToSend> destinations) {
        this.destinations = destinations;
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
                + departureTime + ", truckNumber = " + truckNumber + ", truckWeight = " + truckWeight + ", driverID = "
                + driverID + ", origin = " + origin +  ", deliveryStatus = " + deliveryStatus + " ]";
    }
}
