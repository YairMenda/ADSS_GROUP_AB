package BussinessLayer;
import java.util.*;

public class Delivery {
    private int deliveryNumber;
    private Date date;
    private Date departureTime;
    private int truckNumber;
    private double truckWeight;
    private String driverName;
    private Site origin;
    private List<Site> destinations;
    private status deliveryStatus;
    private List<DstDoc> destinationDocs;

    public static enum status {waiting,approved,complete};

    public Delivery(int deliveryNumber,Date date, Date depTime , int truckNumber
    , String driverName,Site origin, List<Site> destinations)
    {
        this.deliveryNumber=deliveryNumber;
        this.date=date;
        this.departureTime=depTime;
        this.truckNumber=truckNumber;
        this.truckWeight=-1;
        this.driverName=driverName;
        this.origin=origin;
        this.destinations=destinations;
        deliveryStatus = status.waiting;
        this.destinationDocs = new LinkedList<DstDoc>();
        
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

    public Site getOrigin() {
        return origin;
    }

    public void setOrigin(Site origin) {
        this.origin = origin;
    }

    public List<Site> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Site> destinations) {
        this.destinations = destinations;
    }

    public List<DstDoc> geDstDocs()
    {
        return this.destinationDocs;
    }

    public status getDeliveryStatus() {
        return deliveryStatus;
    }

    public void approveDelivery()
    {
        deliveryStatus = status.approved; 
    }

    public void completeDelivery()
    {
        deliveryStatus = status.complete; 
    }

    public boolean addDstDoc(DstDoc dd)
    {
        if (openToChanges()){
            destinationDocs.add(dd);
            return true;
        }
        return false;
   
    }

    public boolean removeDstDoc(int docNumber){
        if (openToChanges()){
            for (DstDoc dd : destinationDocs) {
                if (dd.getDocNumber()==docNumber){
                    destinationDocs.remove(dd);
                    return true;
                }
                
            }
            
        }
        return false;
   
    }



    public boolean remove(Site site){
        if (openToChanges()){
             for(DstDoc dd: destinationDocs){
                 if (dd.getDestination() == site){
                   destinationDocs.remove(dd);
                }
            destinations.remove(site);
             return true;
             }
          }
         return false;
    }

    public boolean openToChanges(){
        return deliveryStatus==status.approved;
    }


    public void removeProductsByDocNumber(int dstDocNumber,List<Integer> deletedProducts)
    {
        geDstDocByNumber(dstDocNumber).removeProducts(deletedProducts);
    }

    public DstDoc geDstDocByNumber(int dstDocNumber)
    {
        for (DstDoc d: destinationDocs)
        {
            if (d.getDocNumber()==dstDocNumber)
                return d;
        }

        return null; 
    }

    //replaces the truck and  cancels the last weight
    public void replaceTruck(int newTruckNumber){
        this.truckNumber= newTruckNumber;
        this.truckWeight=-1;
    }

    }


