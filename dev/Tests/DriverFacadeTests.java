package Tests;

import BussinessLayer.Driver;
import BussinessLayer.DriverFacade;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class DriverFacadeTests {
//
//    public DriverFacade df;
//    public Driver omer;
//    public Driver roi;
//
//
//    @BeforeAll
//    public void InitTest(){
//        List<Driver> ld = new LinkedList<Driver>();
//        List<String> licenseList = new LinkedList<String>();
//        licenseList.add("a");
//        licenseList.add("b");
//        omer = new Driver("1","omer", new LinkedList<String>(licenseList));
//        roi = new Driver("2","roi", new LinkedList<String>(licenseList));
//        ld.add(omer);
//        ld.add(roi);
//        df = new DriverFacade(ld);
//    }
//
//    @Test
//    public void hasLicenseTest1(){
//       try{ Assertions.assertEquals(df.hasLicense("1","a"),true );
//    }
//    catch (Exception e){}
//    }
//
//    @Test
//    public void hasLicenseTest2(){
//        try{Assertions.assertEquals(df.hasLicense("2","c"),false );}
//        catch (Exception e){}}
//
//    @Test
//    public void DriversByLicenseTest1(){
//        List<Driver> l;
//        try{
//            l=df.getDriversByLicense("a");
//            Assertions.assertEquals(l.contains(omer), true);
//        }catch (Exception e){}
//
//
//    }
//
//    @Test
//    public void DriversByLicenseTest2(){
//        List<Driver> l;
//        try{
//            l=df.getDriversByLicense("c");
//            Assertions.assertEquals(l.contains(omer), false);
//        }catch (Exception e){}
//
//
//    }
//
//
//    @Test
//    public void DeliveryAccomplishTest1(){
//        LocalDateTime date = LocalDateTime.of(2024, 12,12,3,30);
//        try{
//            df.addDelivery("1", date);
//            Assertions.assertEquals(df.isAvailable("1", date),false );
//        }catch (Exception e){}
//    }
//    @Test
//    public void DeliveryAccomplishTest2(){
//        LocalDateTime date = LocalDateTime.of(2024, 12,12,3,30);
//        try{
//            df.deliveryAcomplishment("1", date);
//            Assertions.assertEquals(df.isAvailable("1", date),true );
//        }catch (Exception e){}
//    }




//}
