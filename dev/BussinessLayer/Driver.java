package BussinessLayer;
import java.time.LocalDateTime;
import java.util.*;


public class Driver {

    private String id;
    public String getId() {
        return id;
    }

    private String name;
    private List<String> licenses;
    private DeliveryDates futureDeliveryDates;
    private EmployeeShift shifts;
    
    public Driver(String id, String name, List<String> licenses,EmployeeShift shifts) {
        this.id = id;
        this.name = name;
        this.licenses = licenses;
        this.futureDeliveryDates = new DeliveryDates();
        this.shifts = shifts;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLicenses(){
        return licenses;
    }

    public DeliveryDates getFutureDeliveryDates(){
        return futureDeliveryDates;
    }


    public boolean hasLicense(String license){
        for (String c:licenses){
            if (c.equals(license))
                return true;
    }
        return false;     
    }



    public void addLicense(String license){
        licenses.add(license);
    }



    public boolean isAvailableByEstimatedTime(int deliveryNumber , LocalDateTime estimatedTime){
        return futureDeliveryDates.isAvailableByEstimatedTime(deliveryNumber,estimatedTime);
    }

    public boolean isAvailableByDelivery(LocalDateTime date){
        return futureDeliveryDates.isAvailableByNewDelivery(date);
    }

    public boolean isAvailableByShift(LocalDateTime date)
    {
        return shifts.isAvailable(date);
    }
    public void deliveryAcomplishment(int deliveryNumber){
        futureDeliveryDates.deliveryAcomplishment(deliveryNumber);
    }


public void addDelivery(Delivery d)throws Exception{
    futureDeliveryDates.addDelivery(d);
}

public void removeDelivery(int deliveryNumber){
    futureDeliveryDates.removeDelivery(deliveryNumber);
}

public boolean estimatedArrivalwithinShift(LocalDateTime estimatedArrival) throws Exception
{
    return shifts.isAvailable(estimatedArrival);
}

}