/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ServiceLayer;
import BussinessLayer.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 *
 * @author Omera
 */
public class DeliveryDatesToSend {
   private List<DeliveryToSend> dates;


public DeliveryDatesToSend(DeliveryDates other){
    dates = new LinkedList<>();
    for (Delivery d : other.getDates().values())
        dates.add(new DeliveryToSend(d));
}

public List<DeliveryToSend> getDates(){
    return dates;
}

}
