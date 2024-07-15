package Tests;

import BussinessLayer.*;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriverFacadeTests {

    public DriverFacade df;
    public Driver omer;
    public Driver roi;


    @BeforeAll
    public void InitTest(){
        List<Driver> ld = new LinkedList<Driver>();
        List<String> licenseList = new LinkedList<String>();
        licenseList.add("a");
        licenseList.add("b");
        List<LocalDateTime> times = new LinkedList<>();
        times.add(LocalDateTime.of(2024,8,1,12,0));
        times.add(LocalDateTime.of(2024,9,1,12,0));
        EmployeeShift emOmer = new EmployeeShift("1",times,"Ness Ziona");
        EmployeeShift emRoi = new EmployeeShift("2",times,"Ness Ziona");
        omer = new Driver("1","omer", new LinkedList<String>(licenseList),emOmer);
        roi = new Driver("2","roi", new LinkedList<String>(licenseList),emRoi);
        ld.add(omer);
        ld.add(roi);
        df = new DriverFacade(ld);
    }

    @Test
    public void hasLicenseTest1(){
       try{ Assertions.assertEquals(df.hasLicense("1","a"),true );
    }
    catch (Exception e){}
    }

    @Test
    public void hasLicenseTest2(){
        try{Assertions.assertEquals(df.hasLicense("2","c"),false );}
        catch (Exception e){}}

    @Test
    public void DriversByLicenseTest1(){
        List<Driver> l;
        try{
            l=df.getDriversByLicense("a");
            Assertions.assertEquals(l.contains(omer), true);
        }catch (Exception e){}


    }

    @Test
    public void DriversByLicenseTest2(){
        List<Driver> l;
        try{
            l=df.getDriversByLicense("c");
            Assertions.assertEquals(l.contains(omer), false);
        }catch (Exception e){}
    }


    @Test
    public void DeliveryAccomplishTest1(){
        LocalDateTime date = LocalDateTime.of(2024, 8,1,13,30);
        try{
            Delivery newDel = new Delivery(1,date,date, 1,"1",new Site("","","",",",new ArrayList<>()));
            df.addDelivery("1", newDel);
            Assertions.assertEquals(df.getDriversByLicenseAndDate("a",date).size(),1 );
        }catch (Exception e){}
    }
    @Test
    public void DeliveryAccomplishTest2(){
        LocalDateTime date = LocalDateTime.of(2024, 8,1,13,30);
        try{
            df.deliveryAcomplishment("1", 1);
            Assertions.assertEquals(df.getDriversByLicenseAndDate("a",date).size(),2 );
        }catch (Exception e){}
    }
}
