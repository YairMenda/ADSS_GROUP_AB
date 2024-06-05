package BussinessLayer;
import java.time.LocalDateTime;
import java.time.temporal.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class DeliveryDates {
    List<LocalDateTime> dates;
    final double durationBetweenDel=4;
    
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
        for (LocalDateTime d : dates){
            Duration duration = Duration.between(d, date);
            if (Math.abs(duration.toHours()) < durationBetweenDel)
                 available = false;

        }
        return available;
    }

    public void deliveryAcomplishment(LocalDateTime d)
    {  
        for (LocalDateTime date : dates)
        {
            if (date.equals(d))
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

