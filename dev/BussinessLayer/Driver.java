package BussinessLayer;
import java.util.*;


public class Driver {

    private String name;
    private List<String> licenses;
    private DeliveryDates futureDeliveryDates;
    
    public Driver(String name, List<String> licenses)
    {

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



    public boolean isAvailable(Date date){
        return futureDeliveryDates.isAvailable(date);
    }

    public void deliveryAcomplishment(Date date){
        futureDeliveryDates.deliveryAcomplishment(date);
    }


//assumes isAvailable(date)==true
public void addDelivery(Date date){
    futureDeliveryDates.addDelivery(date);
}

public void removeDelivery(Date date){
    futureDeliveryDates.removeDelivery(date);
}


}