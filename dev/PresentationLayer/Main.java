package PresentationLayer;

import java.util.Scanner;

import BussinessLayer.Truck;
import ServiceLayer.DeliveryService;
import ServiceLayer.DriverService;
import ServiceLayer.InitSystem;
import ServiceLayer.SiteService;
import ServiceLayer.TruckService;

public class Main {


    public static void main(String [] args)
    {
        Presentation p = new Presentation();
        p.runSystem();
    }

}
