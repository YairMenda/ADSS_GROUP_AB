package BussinessLayer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;

public class DeliveryDates {
    List<LocalDateTime> dates;
    
    public DeliveryDates()
    {
        this.dates = new LinkedList<LocalDateTime>();
    }

    public List<LocalDateTime> getDates(){
        return dates;
    }

    public boolean isAvailable(LocalDateTime date)
    {
        boolean available = true;
        for (LocalDateTime d : dates)
            if (Duration.between(d,date).toHours() < 4)
                available = false;
        return available;
    }

    public void deliveryAcomplishment(LocalDateTime d)
    {  
        for (LocalDateTime date : dates)
        {
            if (date.compareTo(d) == 0)
            {
                dates.remove(date);
            }

        }

    }

    //assumes 
    public boolean addDelivery(LocalDateTime date){
        if (isAvailable(date))
            {dates.add(date);return true;}
        return false;
    }

    public void removeDelivery(LocalDateTime d){
        dates.remove(d);
    }
}
