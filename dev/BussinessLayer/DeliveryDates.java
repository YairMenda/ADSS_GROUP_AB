package BussinessLayer;
import java.time.LocalDateTime;
import java.time.temporal.*;
import java.util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class DeliveryDates {
    private Map<Integer, Delivery> dates;

    public DeliveryDates() {
        this.dates = new HashMap<>();
    }
    public DeliveryDates(List<Delivery> deliveries)
    {
        dates = new HashMap<>();
        for (Delivery d : deliveries)
            dates.put(d.getDeliveryNumber(),d);
    }

    public Map<Integer, Delivery> getDates() {
        return dates;
    }

    public boolean isAvailableByEstimatedTime(int deliveryNumber, LocalDateTime dateToCheck) {

        LocalDateTime depDateToCheck = dates.get(deliveryNumber).getDepartureTime();
        for (Delivery d : dates.values()) {
            if (d.getDeliveryNumber() != deliveryNumber) {
                if ((depDateToCheck.isBefore(d.getDepartureTime()) && dateToCheck.isAfter(d.getDepartureTime())) ||
                        (depDateToCheck.isBefore(d.getEndTime()) && dateToCheck.isAfter(d.getEndTime())))
                    return false;
            }
        }
        return true;
    }

    public boolean isAvailableByNewDelivery(LocalDateTime dateToCheck) {
        for (Delivery d : dates.values()) {
            if ((dateToCheck.isAfter(d.getDepartureTime()) && dateToCheck.isBefore(d.getEndTime())))
                return false;
        }
        return true;
    }

    public void deliveryAcomplishment(int deliveryNumber) {
        dates.remove(deliveryNumber);

    }

    //assumes 
    public void addDelivery(Delivery d) {
        dates.put(d.getDeliveryNumber(), d);
    }

    public void removeDelivery(int deliveryNumber) {
        dates.remove(deliveryNumber);
    }

    public boolean isAvailableForReplace(Delivery newDelivery) {
        for (Delivery d : dates.values()) {
            if ((newDelivery.getDepartureTime().isAfter(d.getDepartureTime()) && newDelivery.getDepartureTime().isBefore(d.getEndTime()))
                    || (newDelivery.getEndTime().isAfter(d.getDepartureTime()) && newDelivery.getEndTime().isBefore(d.getEndTime())))
                return false;
        }
        return true;
    }

}
