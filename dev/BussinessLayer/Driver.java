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
    
    public Driver(String id, String name, List<String> licenses)
    {
        this.id = id;
        this.name = name;
        this.licenses = licenses;
        this.futureDeliveryDates= new DeliveryDates();

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
            if (c==license)
                return true;
    }
        return false;     
    }



    public void addLicense(String license){
        licenses.add(license);
    }



    public boolean isAvailable(LocalDateTime date){
        return futureDeliveryDates.isAvailable(date);
    }

    public void deliveryAcomplishment(LocalDateTime date){
        futureDeliveryDates.deliveryAcomplishment(date);
    }


public void addDelivery(LocalDateTime date)throws Exception{
    if (!futureDeliveryDates.addDelivery(date))
        throw new Exception("The driver with id - " + getId() + "is not avaliable");
}

public void removeDelivery(LocalDateTime date){
    futureDeliveryDates.removeDelivery(date);
}


}