package Tests;
import BussinessLayer.Truck;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;
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
    public void aviableTest(){
        LocalDateTime date = LocalDateTime.of(2024, 12,12,3,30);
        try{t1.addDelivery(date);}
        catch(Exception c){}
        Assertions.assertEquals(t1.isAvailable(date),false);
    }













}
