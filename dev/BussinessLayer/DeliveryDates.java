package BussinessLayer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DeliveryDates {
    List<Date> dates;
    
    public DeliveryDates()
    {
        this.dates = new LinkedList<Date>();
    }

    public List<Date> getDates(){
        return dates;
    }

    public boolean isAvailable(Date d)
    {
        boolean available = true;

        for (Date date : dates)
        {
            if (date.compareTo(d) == 0)
            {
                available = false;
            }

        }

        return available;
    }

    public void deliveryAcomplishment(Date d)
    {  
        for (Date date : dates)
        {
            if (date.compareTo(d) == 0)
            {
                dates.remove(date);
            }

        }

    }

    public void addDelivery(Date d){
        dates.add(d);
    }

    public void removeDelivery(Date d){
        dates.remove(d);
    }
}
