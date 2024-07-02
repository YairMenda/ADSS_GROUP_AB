package ServiceLayer;
import BussinessLayer.*;
import java.util.List;

public class DriverToSend {

    private String id;
    private String name;
    private List<String> licenses;
    private DeliveryDatesToSend dates;

    public DriverToSend(Driver d)
    {

        this.id  = d.getId();
        this.name = d.getName();
        this.licenses = d.getLicenses();
        this.dates= new DeliveryDatesToSend(d.getFutureDeliveryDates());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLicenses() {
        return this.licenses;
    }

    public void setLicenses(List<String> licenses) {
        this.licenses = licenses;
    }

    public String toString()
    {
        String deliveries = "";
        for (DeliveryToSend d : dates.getDates())
        {
            deliveries += "   delivery date - " + d.getDepartureTime();
        }
        return "Driver id - " + this.id + " Name - " + this.name + " License " + licenses + "\n" +
                "Future Deliveries - " + deliveries;
    }
}
