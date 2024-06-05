package PresentationLayer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import BussinessLayer.Truck;
import ServiceLayer.DeliveryService;
import ServiceLayer.DriverService;
import ServiceLayer.InitSystem;
import ServiceLayer.SiteService;
import ServiceLayer.TruckService;
import Tests.ManualTests;

public class Main {


    public static void main(String [] args)
    {

        //manual tests
        ManualTests t = new ManualTests();
        t.runManualTests();





        Presentation p = new Presentation();
        p.runSystem();

    }

}
