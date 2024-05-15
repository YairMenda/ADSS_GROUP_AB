package ServiceLayer;
import java.util.*;
public class DeliveryToSend {


public class Delivery {
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
}
