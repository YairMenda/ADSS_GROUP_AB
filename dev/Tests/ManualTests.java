package Tests;

import ServiceLayer.*;

import java.time.LocalDateTime;

public class ManualTests {
    DeliveryService deliveryService;
    DriverService driverService;
    TruckService truckService;
    SiteService siteService;
    public  ManualTests(){
        InitSystem init = new InitSystem();
        deliveryService = init.getDeliveryService();
        driverService = init.getDs();
        truckService = init.getTs();
        siteService = init.getSs();

    }



    private void addDeliveryTest(){
        Response r = deliveryService.addNewDelivery(LocalDateTime.of(2022,12,12,12,12),1,"1","Matan");
        if(r.ErrorOccured())
            System.out.println("addDeliveryTest -failed  error:"+ r.errorMessage);
        else
            System.out.println("addDeliveryTest -Succeded");
    }

    private void removeDeliveryTest(){
        Response r = deliveryService.removeDelivery(1);
        if(r.ErrorOccured())
            System.out.println("removeDeliveryTest -failed  error:"+ r.errorMessage);
        else
            System.out.println("removeDeliveryTest -Succeded");
    }



    private void updateWeightTest(){
        deliveryService.addNewDelivery(LocalDateTime.of(2022,12,12,12,12),1,"1","Matan");
        Response r = deliveryService.updateWeight(2,300);
        if(r.ErrorOccured())
            System.out.println("updateWeightTest -Succeded");

        else
            System.out.println("updateWeightTest -failed  error should occur");
    }







    public void runManualTests(){
        addDeliveryTest();
        removeDeliveryTest();
        updateWeightTest();
    }











}
