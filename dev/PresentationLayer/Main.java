package PresentationLayer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import BussinessLayer.Truck;
import ServiceLayer.*;
import Tests.ManualTests;

public class Main {


    public static void main(String [] args)
    {

        //manual tests
//        try{
//        ManualTests t = new ManualTests();
//        t.runManualTests();}
//        catch (Exception e){}


        Scanner s = new Scanner(System.in);
        boolean exit=false;
        while (!exit){
            System.out.println("WELOCME TO SUPER LI!");
            System.out.println();
            System.out.println("FOR Deliveries Module MENU - PRESS 1");
            System.out.println("FOR Storage Module MENU - PRESS 2");
            System.out.println("EXIT - PRESS 3");
            System.out.println();
            //s.nextLine();
            String action;

            do{
                action  = s.nextLine();
            }while(action.length() == 0);

            switch (action) {
                case "1":
                    try {
                        Presentation p = new Presentation();
                        p.runSystem();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    try {
                        StorageHandler sh = new StorageHandler();
                        StorageInit si = new StorageInit();
                        si.init();
                        sh.StorageLoop();
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    System.out.println("Thank you");
                    exit=true;
                    s.close();
                    break;
            }}
    }
}

