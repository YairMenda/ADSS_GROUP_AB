package ServiceLayer;
import BussinessLayer.*;
import java.util.*;


public class DeliveryToSend {

    private int deliveryNumber;
    private Date date;
    private Date departureTime;
    private int truckNumber;
    private double truckWeight;
    private String driverName;
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
        this.driverName=other.getDriverName();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
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
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
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
}
