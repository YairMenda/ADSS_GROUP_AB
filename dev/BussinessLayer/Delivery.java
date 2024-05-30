package BussinessLayer;
import java.time.LocalDateTime;
import java.util.*;

public class Delivery {
    private int deliveryNumber;
    private LocalDateTime date;
    private LocalDateTime departureTime;
    private int truckNumber;
    private double truckWeight;
    private List<Double> truckWeightHistory;
    private String driverID;
    private Site origin;
    private List<Site> destinations;
    private status deliveryStatus;
    private List<DstDoc> destinationDocs;

    public static enum status {waiting,approved,inProgress,complete};

    public Delivery(int deliveryNumber,LocalDateTime date, LocalDateTime depTime , int truckNumber
    , String driverID,Site origin)
    {
        this.deliveryNumber=deliveryNumber;
        this.date=date;
        this.departureTime=depTime;
        this.truckNumber=truckNumber;
        this.truckWeight = -1;
        this.truckWeightHistory = new LinkedList<Double>();;
        this.driverID=driverID;
        this.origin=origin;
        this.destinations=new LinkedList<>();
        deliveryStatus = status.waiting;
        this.destinationDocs = new LinkedList<DstDoc>();
        
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public LocalDateTime getDate() {
        return this.date;
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
        this.truckWeightHistory.add(truckWeight);
        this.truckWeight = truckWeight;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverName) {
        this.driverID = driverID;
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

    public List<Double> getTruckWeightHistory() {
        return truckWeightHistory;
    }

    public void approveDelivery() throws Exception
    {
        if (this.truckWeight==-1)
            throw new Exception("you have to weight the truck before approval");
        deliveryStatus = status.approved; 
    }

    public void inProgressDelivery() throws Exception
    {
        if (this.deliveryStatus!=status.approved)
            throw new Exception("you have to approve the delivery before");
        deliveryStatus = status.inProgress; 
    }


    public void completeDelivery() throws Exception
    {
        if (this.deliveryStatus!=status.inProgress)
            throw new Exception("you can't compete an order which is not in progress");   
        deliveryStatus = status.complete; 
    }

    public void disapproveDelivery() throws Exception
    {
        if(this.deliveryStatus==status.complete){
            throw new Exception("can't disapprove completed delivery");
        }
        deliveryStatus = status.waiting; 
        truckWeight=-1;
    }

    public boolean addDstDoc(DstDoc dd) throws Exception
    {
        Site s = dd.getDestination();
        if (!openToChanges()){
            throw new Exception("This delivery is already approved, if you want to change it, please disapprove");
        }
        for (Site s1 : destinations){
            if (s1.getAddress().equals(dd.getDestination()))
                throw new Exception("Already exist a document for this site");
        
            if (s1.getShippingArea().equals(dd.getDestination().getShippingArea()))
                throw new Exception("Each delivery only support destinations with the same shipping area, this is the valid area - " + s1.getShippingArea());
            }
        
        destinationDocs.add(dd);
        destinations.add(s);
        return true;
        
    }

    public boolean removeDstDoc(int docNumber){
        if (openToChanges()){
            for (DstDoc dd : destinationDocs) {
                if (dd.getDocNumber()==docNumber){
                    destinationDocs.remove(dd);
                    destinations.remove(dd.getDestination());
                    return true;
                } 
            }
            
        }
        return false;
   
    }



    public boolean remove(Site site) throws Exception{
        if (openToChanges()){
             for(DstDoc dd: destinationDocs){
                 if (dd.getDestination() == site){
                   destinationDocs.remove(dd);
                }
            destinations.remove(site);
             return true;
             }
          }
          throw new Exception("please disapprove before changes");
    }

    public boolean openToChanges(){
        return deliveryStatus.equals(status.waiting);
    }


    public void removeProductsByDocNumber(int dstDocNumber,List<Integer> deletedProducts) throws Exception
    {
        if (openToChanges()){
            getDstDocByNumber(dstDocNumber).removeProducts(deletedProducts);
        }
        else{
            throw new Exception("please disapprove before changes");
        }
        
        
    }

    public DstDoc getDstDocByNumber(int dstDocNumber) throws Exception
    {
        for (DstDoc d: destinationDocs)
        {
            if (d.getDocNumber()==dstDocNumber)
                return d;
        }

        throw new Exception("destination document "+dstDocNumber+" doesn't exist"); 
    }

    public DstDoc getDstDocByNumber(String address) throws Exception
    {
        for (DstDoc d: destinationDocs)
        {
            if (d.getDestination().getAddress()==address)
                return d;
        }
        throw new Exception("destination document of "+address+" doesn't exist"); 
    }

    //replaces the truck and  cancels the last weight
    public void replaceTruck(int newTruckNumber){
        this.truckNumber= newTruckNumber;
        this.truckWeight=-1;
        this.deliveryStatus= status.waiting;
    }

    }


