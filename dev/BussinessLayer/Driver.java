package BussinessLayer;
import DataAccessLayer.DriverDTO;
import DataAccessLayer.DriverToDeliveryDTO;
import DataAccessLayer.DriverToLicenseDTO;

import java.time.LocalDateTime;
import java.util.*;


public class Driver {

    private String id;

    private String name;
    private List<String> licenses;
    private DeliveryDates futureDeliveryDates;
    private EmployeeShift shifts;
    private DriverDTO driverDTO;
    
    public Driver(String id, String name, List<String> licenses,EmployeeShift shifts) {
        this.id = id;
        this.name = name;
        this.licenses = licenses;
        this.futureDeliveryDates = new DeliveryDates();
        this.shifts = shifts;
        this.driverDTO = new DriverDTO(this.id,this.name,this.licenses,this.shifts.getShiftsDTO(),new LinkedList<DriverToDeliveryDTO>());
    }

    public Driver(DriverDTO driverDTO,EmployeeShift es,List<Delivery> deliveries)
    {
        this.id=driverDTO.getId();
        this.name=driverDTO.getName();
        this.licenses = new LinkedList<>();
        for (DriverToLicenseDTO dl : driverDTO.getLicenses())
            this.licenses.add(dl.getLicense());
        this.shifts=es;
        this.futureDeliveryDates = new DeliveryDates(deliveries);
        this.driverDTO=driverDTO;
    }

    public String getName() {
        return name;
    }

    public List<String> getLicenses(){
        return licenses;
    }

    public DeliveryDates getFutureDeliveryDates(){
        return futureDeliveryDates;
    }

    public String getId() {
        return id;
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
        this.driverDTO.UpdateLicense(license);
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
        this.driverDTO.removeDelivery(deliveryNumber);
    }


public void addDelivery(Delivery d)throws Exception{
    futureDeliveryDates.addDelivery(d);
    this.driverDTO.addDelivery(d.getDeliveryNumber());
}

public void removeDelivery(int deliveryNumber){
    futureDeliveryDates.removeDelivery(deliveryNumber);
    this.driverDTO.removeDelivery(deliveryNumber);
}

public boolean estimatedArrivalwithinShift(LocalDateTime estimatedArrival) throws Exception
{
    return shifts.isAvailable(estimatedArrival);
}

}