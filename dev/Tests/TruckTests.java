package Tests;
import BussinessLayer.Delivery;
import BussinessLayer.Site;
import BussinessLayer.Truck;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TruckTests {
    private Truck t1;

    @BeforeAll
    public void initTest(){
        try{
            t1 = new Truck(1,"Toyota", 2000,4000,"b");
        }
        catch(Exception e){}
    }

    @Test
    public void aviableTest1(){
        LocalDateTime date = LocalDateTime.of(2024, 12,12,3,30);
        try{
            Delivery newDel = new Delivery(1,date,date, 1,"1",new Site("","","",",",new ArrayList<>()));
            t1.addDelivery(newDel);}
        catch(Exception c){}
        Assertions.assertEquals(t1.isAvailableByDelivery(date),false);
    }

    @Test
    public void aviableTest2(){
        LocalDateTime date = LocalDateTime.of(2024, 12,12,3,30);
        try{
            t1.removeDelivery(1);
        }
        catch(Exception c){}
        Assertions.assertEquals(t1.isAvailableByDelivery(date),true);
    }

    @Test
    public void deliveryAccomplish(){
        LocalDateTime date = LocalDateTime.of(2024, 12,12,3,30);
        try{
            Delivery newDel = new Delivery(1,date,date, 1,"1",new Site("","","",",",new ArrayList<>()));
            t1.addDelivery(newDel);
            t1.deliveryAcomplishment(1);
        }
        catch(Exception c){}
        Assertions.assertEquals(t1.isAvailableByDelivery(date),true);
    }



}
