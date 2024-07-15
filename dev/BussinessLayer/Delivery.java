package BussinessLayer;
import DataAccessLayer.DeliveryDTO;
import DataAccessLayer.DeliveryToWeightDTO;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Delivery {
    static final long gapTime = 15;
    private int deliveryNumber;
    private LocalDateTime date;
    private LocalDateTime departureTime;
    private int truckNumber;
    private double truckWeight;
    private List<Double> truckWeightHistory;
    private String driverID;
    private Site origin;
    private status deliveryStatus;
    private List<DstDoc> destinationDocs;

    private LocalDateTime endTime;
    private DeliveryDTO deliveryDTO;

    public static enum status {waiting,approved,inProgress,complete};

    public Delivery(int deliveryNumber,LocalDateTime date, LocalDateTime depTime , int truckNumber
    , String driverID,Site origin) throws Exception
    {
        this.deliveryNumber=deliveryNumber;
        this.date=date;
        if (depTime.isAfter(LocalDateTime.now()))
            this.departureTime=depTime;
        else { throw new Exception("DEPT TIME IS BEFORE THE CURRENT DATE, YOU CAN ONLY ADD FUTURE DELIVERIES");}
        this.truckNumber=truckNumber;
        this.truckWeight = -1;
        this.truckWeightHistory = new LinkedList<Double>();;
        this.driverID=driverID;
        this.origin=origin;
        deliveryStatus = status.waiting;
        this.destinationDocs = new LinkedList<DstDoc>();
        this.endTime = depTime;
        this.deliveryDTO = new DeliveryDTO(this.deliveryNumber,this.date,this.departureTime,this.truckNumber,this.truckWeight
                ,this.driverID,this.deliveryStatus.toString(),this.endTime,this.origin.getAddress());
        this.deliveryDTO.add();
    }

    public Delivery(DeliveryDTO deliveryDTO,Site site,List<DstDoc> destinationDocs)
    {
        this.deliveryNumber=deliveryDTO.getDeliveryNumber();
        this.date=deliveryDTO.getDate();
        this.departureTime=deliveryDTO.getDepartureTime();
        this.truckNumber=deliveryDTO.getTruckNumber();
        this.truckWeight=deliveryDTO.getTruckWeight();
        this.driverID=deliveryDTO.getDriverID();
        this.endTime=deliveryDTO.getEndTime();
        this.deliveryStatus=status.valueOf(deliveryDTO.getDeliveryStatus());
        this.origin=site;
        this.destinationDocs=destinationDocs;
        this.truckWeightHistory = new LinkedList<>();
        for (DeliveryToWeightDTO dw : deliveryDTO.getWeightHistory())
            this.truckWeightHistory.add(dw.getTruckWeight());
        this.deliveryDTO=deliveryDTO;
    }
     public LocalDateTime getEndTime()
     {
         return this.endTime;
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

    public void setTruckWeight(double truckWeight) throws Exception{
        if (this.destinationDocs.size()==0)
            throw new Exception("You cant weight a truck without destination docs");
        this.truckWeightHistory.add(truckWeight);
        this.truckWeight = truckWeight;
        this.deliveryDTO.setTruckWeight(truckWeight);
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
        List<Site> destinations = new LinkedList<>();
        for(DstDoc d : destinationDocs)
            destinations.add(d.getDestination());
        return destinations;
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
        if (this.destinationDocs.size()==0)
            throw new Exception("You cant approve a delivery without destination docs");
        if (this.truckWeight==-1)
            throw new Exception("you have to weight the truck before approval");
        if (this.deliveryStatus != status.waiting)
            throw new Exception("this delivery is already approved, inProress, or complete");
        deliveryStatus = status.approved;
        this.deliveryDTO.setDeliveryStatus(deliveryStatus.toString());
    }

    public void inProgressDelivery() throws Exception
    {
        if (this.deliveryStatus!=status.approved)
            throw new Exception("you have to approve the delivery before");
        deliveryStatus = status.inProgress;
        this.deliveryDTO.setDeliveryStatus(deliveryStatus.toString());
    }


    public void completeDelivery() throws Exception
    {
        if (this.deliveryStatus!=status.inProgress)
            throw new Exception("you can't compete an order which is not in progress");   
        deliveryStatus = status.complete;
        this.deliveryDTO.setDeliveryStatus(deliveryStatus.toString());
    }

    public void disapproveDelivery() throws Exception
    {
        if(this.deliveryStatus==status.complete){
            throw new Exception("can't disapprove completed delivery");
        }
        deliveryStatus = status.waiting; 
        setTruckWeight(-1);
        this.deliveryDTO.setDeliveryStatus(deliveryStatus.toString());
    }

    public boolean addDstDoc(DstDoc dd) throws Exception
    {
        Site s = dd.getDestination();

        if (!openToChanges()){
            throw new Exception("This delivery is already approved, if you want to change it, please disapprove");
        }


        for (Site s1 : getDestinations()){
            if (s1.getAddress().equals(dd.getDestination()))
                throw new Exception("Already exist a document for this site");
        
            if (!s1.getShippingArea().equals(dd.getDestination().getShippingArea()))
                throw new Exception("Each delivery only support destinations with the same shipping area, this is the valid area - " + s1.getShippingArea());
            }

        if (dd.getEstimatedArrivalTime().isAfter(endTime))
            this.endTime = dd.getEstimatedArrivalTime().plusMinutes(gapTime);

        dd.getDstDocDTO().add();
        destinationDocs.add(dd);
        this.deliveryDTO.addDstDoc(dd.getDstDocDTO());
        return true;
        
    }

    public boolean removeDstDoc(int docNumber){
        if (openToChanges()){
            for (DstDoc dd : destinationDocs) {
                if (dd.getDocNumber()==docNumber){
                    destinationDocs.remove(dd);
                    this.deliveryDTO.removeDstDoc(dd.getDstDocDTO());
                    if (endTime.equals(dd.getEstimatedArrivalTime().plusMinutes(gapTime)))
                        updateEndTimeAfterDstDocRemove();
                    return true;

                }
            }
        }
        return false;
   
    }

    public void updateEndTimeAfterDstDocRemove()
    {
        LocalDateTime newEndTime = departureTime;
        for (DstDoc d: destinationDocs)
        {
            if (newEndTime.isBefore(d.getEstimatedArrivalTime()))
                newEndTime = d.getEstimatedArrivalTime();
        }

        setEndTime(newEndTime.plusMinutes(gapTime));
    }

    public void setEndTime(LocalDateTime endTime)
    {
        this.endTime=endTime;
        this.deliveryDTO.setEndTime(endTime);
    }
    public boolean remove(Site site) throws Exception{
        if (openToChanges()){
             for(DstDoc dd: destinationDocs){
                 if (dd.getDestination() == site){
                   destinationDocs.remove(dd);
                   this.deliveryDTO.removeDstDoc(dd.getDstDocDTO());
                   updateEndTimeAfterDstDocRemove();
                }
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
    public void replaceTruck(int newTruckNumber) throws Exception{
        setTruckNumber(newTruckNumber);
        setTruckWeight(-1);
        disapproveDelivery();
    }

    public boolean depTimeTOEstimatedTime(LocalDateTime estimatedTime)
    {
        if ((Duration.between(departureTime,estimatedTime).toSeconds()< (EmployeeShift.shiftDuration*3600)) && (Duration.between(departureTime,estimatedTime).toSeconds()>=0))
            return true;

        return false;
    }
}


