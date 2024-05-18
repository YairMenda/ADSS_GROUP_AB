/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ServiceLayer;
import BussinessLayer.*;
import java.util.*;

/**
 *
 * @author Omera
 */
public class DeliveryDatesToSend {
   private List<Date> dates;


public DeliveryDatesToSend(DeliveryDates other){
    this.dates=other.getDates();
}

public List<Date> getDates(){
    return dates;
}

}
