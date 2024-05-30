package BussinessLayer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

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
        for (LocalDateTime d : dates){
            Period p = getPeriod(d,date);  
            if (p.getDays() < 2)
                if (Duration.between(date, d).toHours() < 4){
                 available = false;
                }
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

    private static Period getPeriod(LocalDateTime dob, LocalDateTime now) {
        return Period.between(dob.toLocalDate(), now.toLocalDate());
    }
}
