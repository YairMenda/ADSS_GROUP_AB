package ServiceLayer;
import java.util.*;


public class DeliveryToSend {

    private int deliveryNumber;
    private Date date;
    private Date departureTime;
    private int truckNumber;
    private double truckWeight;
    private String driverName;
    private String origin;
    private List<String> destinations;
    private String deliveryStatus;

    public DeliveryToSend(int deliveryNumber,Date date, Date depTime , int truckNumber,double truckWeight, String driverName,String origin, List<String> destinations,String deliveryStatus)
    {
        this.deliveryNumber=deliveryNumber;
        this.date=date;
        this.departureTime=depTime;
        this.truckNumber=truckNumber;
        this.driverName=driverName;
        this.origin=origin;
        this.destinations=destinations;
        this.deliveryStatus=deliveryStatus;

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

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
