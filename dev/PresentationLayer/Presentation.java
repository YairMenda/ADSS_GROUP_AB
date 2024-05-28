package PresentationLayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import ServiceLayer.DeliveryDatesToSend;
import ServiceLayer.DeliveryService;
import ServiceLayer.DriverService;
import ServiceLayer.InitSystem;
import ServiceLayer.Response;
import ServiceLayer.SiteService;
import ServiceLayer.TruckService;
import ServiceLayer.TruckToSend;

public class Presentation {
    InitSystem ios = new InitSystem();
        DeliveryService deliveryService =  ios.getDeliveryService();
        DriverService ds = ios.getDs();
        TruckService ts = ios.getTs();
        SiteService ss = ios.getSs();
        Scanner s = new Scanner(System.in);

    public void runSystem()
    {
    
        

        boolean exit=false;
        while (!exit){
        System.out.println("WELOCME TO SUPER LEE - DELIVERY SYSTEM!");
        System.out.println("FOR TRUCK MENU - PRESS 1");
        System.out.println("FOR DRIVER MENU - PRESS 2");
        System.out.println("FOR SITE MENU - PRESS 3");
        System.out.println("FOR DELIVERY MENU - PRESS 4");
        System.out.println("EXIT - PRESS 5");
        String action = s.nextLine();

        switch (action) {
            case "1":
                truckMenu();
                break;
            
            case "2":
                driverMenu();
                break;

            case "3":
                siteMenu();
                break;
            case "4":
                deliveryMenu();
                break;
            case "5":
                System.out.println("Thank you");
                exit=true;
                s.close();
                break;
        }}
    }
    public void truckMenu()
    {
        boolean exit = false;
        while (!exit)
        {
        System.out.println("TRUCK MENU");
        System.out.println("FOR ADDING TRUCK  - PRESS 1");
        System.out.println("FOR TRUCK INFORMATION  - PRESS 2");
        System.out.println("FOR PRINT ALL TRUCKS - PRESS 3");
        System.out.println("FOR GET AVIALIBLE TRUCKS BY DATE - PRESS 4");
        System.out.println("MAIN MENU  - PRESS 5");

        String action = s.nextLine();

        switch (action) {
            case "1":
                addTruck();
                break;
            case "2":
                getTruck();
                break;
            case "3":
                getAllTrucks();
                break;
            case "4":
                getAvialibleTrucks();
                break;
            case "5":
                exit = true;
                break;
                
        }
    }
    }

    public void addTruck()
    {   
        System.out.print("Enter License : ");
        int license  = s.nextInt();
        System.out.println();
        System.out.print("Enter model : ");
        String model  = s.nextLine();
        System.out.println();
        System.out.print("Enter weight without cargo : ");
        double weight  = s.nextDouble();
        System.out.println();
        System.out.print("Enter Max weight : ");
        double maxWeight  = s.nextDouble();
        System.out.println();
        System.out.print("Enter License Category : ");
        String category  = s.nextLine();
        System.out.println();
        System.out.println();

        Response r = ts.addNewTruck(license, model, weight, maxWeight, category);
         if (r.ErrorOccured())
         {
            System.out.println(r.errorMessage);
         }
         else
         {
            System.out.println("Truck number - " + r .getReturnValue() + " Added successfully");
         }

    }

    public void getTruck()
    {
        System.out.print("Enter license number - ");
        int license = s.nextInt();
        System.out.println();

        Response r = ts.getTruck(license);

         if (r.ErrorOccured())
         {
            System.out.println(r.errorMessage);
         }
         else
         {
            TruckToSend t = (TruckToSend)r.getReturnValue();

            System.out.println("Truck number - " + t.getLicenseNumber());
            System.out.println("Model - " + t.getModel());
            System.out.println("weight witout cargo - " + t.getWeightWithoutCargo());
            System.out.println("Max weight - " + t.getMaxWeight());
            System.out.println("license category - " + t.getLicenseCategory());
            System.out.println("Future delivery dates:");
            for (LocalDateTime d : t.getDates().getDates())
            {
                System.out.println("delivery date - " + d);
            }
        
        }

    }

    public void getAllTrucks()
    {
        Response r = ts.getAllTrucks();

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
           
           for (Integer i : (List<Integer>)r.getReturnValue())
           {
               System.out.println("Truck number - " + i);
           }
       

        }
    }

    public void getAvialibleTrucks()
    {
        System.out.print("Enter specific year - ");
        int year = s.nextInt();
        System.out.println();


        System.out.print("Enter specific month - ");
        int month = s.nextInt();
        System.out.println();

        System.out.print("Enter specific day(1-31) - ");
        int day = s.nextInt();
        System.out.println();

        System.out.print("Enter specific hour (0-23)- ");
        int hour = s.nextInt();
        System.out.println();

        System.out.print("Enter specific minutes (0-59)- ");
        int min = s.nextInt();
        System.out.println();

        Response r = ts.getAvialibleTrucks(LocalDateTime.of(LocalDate.of(year,month,day),LocalTime.of(hour,min)));

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
           
           for (Integer i : (List<Integer>)r.getReturnValue())
           {
               System.out.println("Truck number - " + i);
           }
       

        }

    }


    public void driverMenu(){
        boolean exit = false;
        while (!exit)
        {
        System.out.println("DRIVER MENU");
        System.out.println("GET ALL DRIVERS  - PRESS 1");
        System.out.println("GET DRIVERS BY LICENSE  - PRESS 2");
        System.out.println("GET DRIVERS BY LICENSE AND DATE - PRESS 3");
        System.out.println("ADD NEW LICENSE TO DRIVER - PRESS 4");
        System.out.println("MAIN MENU  - PRESS 5");

        String action = s.nextLine();

        switch (action) {
            case "1":
                getAllDrivers();
                break;
            case "2":
                getDriversByLicense();
                break;
            case "3":
                getDriversByLicenseAndDate();
                break;
            case "4":
                addNewLicenseToDriver();
                break;
            case "5":
                exit = true;
                break;
        }
    }
    }
    
    public void siteMenu(){
        boolean exit = false;
        while (!exit)
        {
        System.out.println("SITE MENU");
        System.out.println("FOR ADDING NEW SITE  - PRESS 1");
        System.out.println("FOR SITE INFORMATION  - PRESS 2");
        System.out.println("FOR PRINT ALL SITES - PRESS 3");
        System.out.println("FOR UPDATING PHONE NUMBER OF A SITE - PRESS 4");
        System.out.println("FOR UPDATING CONTACT NAME OF A SITE - PRESS 5");
        System.out.println("MAIN MENU  - PRESS 6");

        String action = s.nextLine();

        switch (action) {
            case "1":
                addSite();
                break;
            case "2":
                getSite();
                break;
            case "3":
                getAllSites();
                break;
            case "4":
                updateSiteNUM();
                break;
            case "5":
                updateSiteCON();
                break;
            case "6":
                exit = true;
                break;
        }
    }}

    public void addSite(){
        System.out.print("Enter Address : ");
        String address  = s.nextLine();
        System.out.println();
        System.out.print("Enter Phone Number : ");
        String phoneNum  = s.nextLine();
        System.out.println();
        System.out.print("Enter Contact Name : ");
        String contactName  = s.nextLine();
        System.out.println();
        Response r =ss.addNewSite(address,phoneNum,contactName);
        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else{
            System.out.println("Site "+ address+ " added succsesfully");
        }
    }



    public void getSite(){
        System.out.print("Enter Address : ");
        String address  = s.nextLine();
        System.out.println();
        Response r1 =ss.getPhoneNumber(address);
        Response r2 = ss.getContactName(address);
        if (r1.ErrorOccured() || r2.ErrorOccured())
        {
           System.out.println(r1.errorMessage);
        }
        
        else{
            System.out.println("Phone number- "+r1.getReturnValue()+" Contact Name-"+r2.getReturnValue());
        }
    }
    public void getAllSites(){

        Response r = ss.getAllSites();
        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else{
            List<String>  l = (List<String>)(r.getReturnValue()); 
            for (String s : l)
           {
                System.out.println("Site address : " + s);
            }
        }

    }
    public void updateSiteNUM(){
        System.out.print("Enter address Name : ");
        String address  = s.nextLine();
        System.out.println();

        System.out.print("Enter phone Number : ");
        String phoneNumber  = s.nextLine();
        System.out.println();
        Response r =ss.editPhoneNumber(address,phoneNumber);
        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else{
            System.out.println("edit Site "+ address + " phone number to - "+ phoneNumber 
            + " was done succsesfully");
        }
    }
    
    
    
    
    
    public void updateSiteCON(){
        
        System.out.print("Enter address Name : ");
        String address  = s.nextLine();
        System.out.println();

        System.out.print("Enter Contact Name : ");
        String contactName  = s.nextLine();
        System.out.println();
        Response r =ss.editContactName(address,contactName);
        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else{
            System.out.println("edit Site "+ address + " contact name to - "+ contactName 
            + " was done succsesfully");
        }
        
    }

    public void deliveryMenu(){
        boolean exit = false;
        while (!exit)
        {
        System.out.println("TRUCK MENU");
        System.out.println("FOR ADDING TRUCK  - PRESS 1");
        System.out.println("FOR TRUCK STATS  - PRESS 2");
        System.out.println("FOR PRINT ALL TRUCKS - PRESS 3");
        System.out.println("FOR GET AVIALIBLE TRUCKS BY DATE - PRESS 4");
        System.out.println("MAIN MENU  - PRESS 5");

        String action = s.nextLine();

        switch (action) {
            case "1":
                addTruck();
                break;
            case "2":
                getTruck();
                break;
            case "3":
                getAllTrucks();
                break;
            case "4":
                getAvialibleTrucks();
                break;
            case "5":
                exit = true;
                break;
        }
    }
    }


//Driver functions
    public void getAllDrivers(){
        Response r = ds.getAllDrivers();

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
           
           for (String s : (List<String>)r.getReturnValue())
           {
               System.out.println("Driver details - " + s);
           }
       

        }
    }
    public void getDriversByLicense(){
        
        System.out.print("Enter license  - ");
        String license = s.nextLine();
        System.out.println();
        
        Response r = ds.getDriversByLicense(license);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
           
           for (String s : (List<String>)r.getReturnValue())
           {
               System.out.println("Driver details - " + s);
           }
       

        }
    }
    public void getDriversByLicenseAndDate(){

        System.out.print("Enter license  - ");
        String license = s.nextLine();
        System.out.println();
        
        System.out.print("Enter specific year - ");
        int year = s.nextInt();
        System.out.println();


        System.out.print("Enter specific month - ");
        int month = s.nextInt();
        System.out.println();

        System.out.print("Enter specific day(1-31) - ");
        int day = s.nextInt();
        System.out.println();

        System.out.print("Enter specific hour (0-23)- ");
        int hour = s.nextInt();
        System.out.println();

        System.out.print("Enter specific minutes (0-59)- ");
        int min = s.nextInt();
        System.out.println();

        Response r = ds.getDriversByLicenseAndDate(license,LocalDateTime.of(LocalDate.of(year,month,day),LocalTime.of(hour,min)));

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
           
           for (String s : (List<String>)r.getReturnValue())
           {
               System.out.println("Driver details - " + s);
           }
       

        }
    }
    public void addNewLicenseToDriver(){
        
        System.out.print("Enter driver ID  - ");
        String id = s.nextLine();
        System.out.println();
        System.out.print("Enter license  - ");
        String license = s.nextLine();
        System.out.println();
        
        Response r = ds.addNewLicenseToDriver(id, license);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
               System.out.println("Driver that was updated : driver id  - " + r.getReturnValue() 
               + " with license - " + license);
        }
        
    }


//Exit the program
    public void exit()
    {
        System.out.println("Thank you");
        s.close();
    }
    }
