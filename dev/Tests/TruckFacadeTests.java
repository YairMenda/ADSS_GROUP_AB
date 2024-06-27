package Tests;
import BussinessLayer.Driver;
import BussinessLayer.Truck;
import BussinessLayer.TruckFacade;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class TruckFacadeTests {
//
//    public TruckFacade tf;
//    public Truck t1;
//    public Truck t2;
//
//
//    @BeforeAll
//    public void initTests(){
//        List<Truck> lt = new LinkedList<Truck>();
//        try{
//            t1 = new Truck(1,"A",10.0,20.0,"a");
//            lt.add(t1);
//            t2 = new Truck(2, "S", 10.0,20.0, "b");
//            lt.add(t2);
//            tf = new TruckFacade(lt);
//        }catch (Exception e){}
//    }
//
//    @Test
//    public void getAvialibleTrucksTest1()
//        {
//            LocalDateTime date = LocalDateTime.of(2024, 12,12,3,30);
//            List<Truck> l;
//            try{
//                l=tf.getAvialibleTrucks(date);
//                Assertions.assertEquals(l.contains(t1), true);
//            }catch (Exception e){}
//        }
//
//    @Test
//    public void getAvialibleTrucksTest2()
//    {
//        LocalDateTime date = LocalDateTime.of(2024, 12,12,3,0);
//        List<Truck> l;
//        try{
//            tf.addDelivery(1,LocalDateTime.of(2024, 12,12,1,0));
//            l=tf.getAvialibleTrucks(date);
//            Assertions.assertEquals(false, l.contains(t1) );
//        }catch (Exception e){}
//    }
//
//
//    @Test
//    public void getAvialibleTrucksTest3()
//    {
//        LocalDateTime date = LocalDateTime.of(2024, 12,12,11,0);
//        List<Truck> l;
//        try{
//            tf.addDelivery(1,LocalDateTime.of(2024, 12,12,7,0));
//            l=tf.getAvialibleTrucks(date);
//            Assertions.assertEquals(true, l.contains(t1) );
//        }catch (Exception e){}
//    }
//
//    @Test
//    public void getAvialibleTrucksTest4()
//    {
//        LocalDateTime date = LocalDateTime.of(2025, 12,12,3,0);
//        List<Truck> l;
//        try{
//            tf.addDelivery(2,LocalDateTime.of(2024, 12,12,3,0));
//            l=tf.getAvialibleTrucks(date);
//            Assertions.assertEquals(true,l.contains(t2) );
//        }catch (Exception e){}
//    }
//
//
//    @Test
//    public void addTruckTest(){
//        List<Truck> l;
//        try{
//            tf.addTruck(3,"Mazda", 3000, 4000, "d");
//            l = tf.getTrucks();
//            Assertions.assertEquals(l.size(),3 );
//        }catch (Exception e){}
//    }
//
//
//
//
//
//
//}
