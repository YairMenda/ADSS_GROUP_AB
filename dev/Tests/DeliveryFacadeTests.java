package Tests;
import BussinessLayer.*;
import ServiceLayer.EmployeeShiftToSend;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeliveryFacadeTests {


    public DeliveryFacade df;
    public Truck t1;
    public Truck t2;
    public Site s1;
    public Site s2;

    public Driver d1;
    public Driver d2;



    @BeforeAll
    public void initTests(){
        List<Truck> lt = new LinkedList<Truck>();
        try{
            t1 = new Truck(1,"A",10.0,20.0,"a");
            lt.add(t1);
            t2 = new Truck(2, "S", 10.0,20.0, "b");
            lt.add(t2);
        }catch (Exception e){}


        List<LocalDateTime> shiftsDates = new LinkedList<>();
        shiftsDates.add(LocalDateTime.of(2024,8,1,12,0));
        shiftsDates.add(LocalDateTime.of(2024,9,1,12,0));

        List<EmployeeShift> shifts = new LinkedList<>();
        EmployeeShift e1 = new EmployeeShift(1,shiftsDates);
        EmployeeShift e2 = new EmployeeShift(2,shiftsDates);
        shifts.add(e1);

        List<Site> ls = new LinkedList<Site>();
        s1= new Site("Matan","0300301212","yair","north", shifts);
        ls.add(s1);
        s2 = new Site("Ness Ziona","0355501212","omer","south",shifts);
        ls.add(s2);

        List<Driver> ld = new LinkedList<Driver>();
        List<String> licenseList = new LinkedList<String>();

        licenseList.add("a");
        licenseList.add("b");
        d1= new Driver("1","omer", new LinkedList<String>(licenseList),e1 );
        ld.add(d1);
        d2= new Driver("2","roi", new LinkedList<String>(licenseList),e2);
        ld.add(d2);

        TruckFacade tf = new TruckFacade(lt);
        SiteFacade sf = new SiteFacade(ls);
        DriverFacade d = new DriverFacade(ld);
        df = new DeliveryFacade(tf, d, sf);
    }

    @Test
    public void addDeliveryTest(){
        try{
            LocalDateTime date = LocalDateTime.of(2025, 12,12,3,0);
            df. addNewDelivery(date,t1.getLicenseNumber(),d1.getId(),s1.getAddress());

        }catch (Exception e){}
    }

    @Test//need to throw exeption
    public void twoDifferentAreasTest(){
        String s="";
        try{
            Delivery newDel = df.addNewDelivery(LocalDateTime.of(2024,8,1,12,0), 1,"1","Matan");
            List<Integer> l = new LinkedList<>();
            l.add(1);
            l.add(2);
            df.addDstDoc(newDel.getDeliveryNumber(),l,s1.getAddress(),LocalDateTime.of(2024,8,1,14,0));
            df.addDstDoc(newDel.getDeliveryNumber(),l, s2.getAddress(),LocalDateTime.of(2024,8,1,15,0));
        }catch (Exception e){
            s=e.getMessage();
        }
        Assertions.assertEquals("Each delivery only support destinations with the same shipping area, this is the valid area - north",s);
    }

    @Test
    public void approvalTest1(){
        String result = "";

        try{
            LocalDateTime date = LocalDateTime.of(2024,9,1,12,0);
            df. addNewDelivery(date,t1.getLicenseNumber(),d1.getId(),s1.getAddress());
            df.approveDelivery(1);
        }catch(Exception e){
            result = e.getMessage();
        }
        Assertions.assertEquals("you have to weight the truck before approval",result);
    }
    @Test
    public void approvalTest2(){
        String result = "";
        try{
            df.weightUpdate(1,7000000);
            df.approveDelivery(1);
        }catch(Exception e){
            result = e.getMessage();
        }
        Assertions.assertEquals("weight out of bounderies, please remove products, change truck or destination",result);
    }

    @Test
    public void removeDeliveryTest(){
        String result="";
        try{
            df.removeDelivery(1);
            df.getDelivery(1);
        }catch (Exception e){
            result = e.getMessage();
        }
        Assertions.assertEquals("Delivery with number - 1 doesnt exist",result);
    }


    @Test
    public void removeProductsTest(){
        String s="";
        boolean removed=true;
        try{
            Delivery newDel = df.addNewDelivery(LocalDateTime.of(2024,9,1,15,0), 1,"1","Matan");
            List<Integer> l = new LinkedList<>();
            l.add(1);
            l.add(2);
            l.add(3);
            DstDoc doc= df.addDstDoc(newDel.getDeliveryNumber(),l,s1.getAddress(),LocalDateTime.of(2024,9,1,16,0));
            List<Integer> toRemove = new LinkedList<>();
            toRemove.add(3);
            df.removeProducts(newDel.getDeliveryNumber(),doc.getDocNumber(),  toRemove);
            removed = doc.getItems().contains(3);
        }catch (Exception e){
            s=e.getMessage();
        }
        Assertions.assertEquals(false,removed);

    }








}
