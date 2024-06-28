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


    public DeliveryDTO(int deliveryNumber, LocalDateTime date, LocalDateTime departureTime, int truckNumber, int truckWeight, String driverID, String deliveryStatus, LocalDateTime endTime){
        this.deliveryNumber= deliveryNumber;
        this.date=date;
        this.departureTime= departureTime;
        this.truckNumber = truckNumber;
        this.truckWeight = truckWeight;
        this.driverID = driverID;
        this.deliveryStatus = deliveryStatus;
        destinationDocs = new LinkedList<>();
        this.endTime = endTime;
    }


    public void setTruckNumber(int truckNumber) {
        this.truckNumber = truckNumber;
        //save in DB
    }

    public void  setTruckWeight(int truckWeight){
        this.truckWeight = truckWeight;
        //save in DB
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        //save in DB
    }

    public void setEntTime(LocalDateTime endTime) {
        this.endTime = endTime;
        //save on DB
    }

    public void addDstDoc(DstDocDTO ddd){
        destinationDocs.add(ddd);
        //save on DB????
    }
}
