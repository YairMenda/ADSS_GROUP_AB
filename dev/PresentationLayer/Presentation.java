package PresentationLayer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import BussinessLayer.EmployeeShift;
import ServiceLayer.*;

public class Presentation {


        InitSystem ios;
        DeliveryService deliveryService;
        DriverService ds;
        TruckService ts;
        SiteService ss;
        Scanner s;

    public Presentation() throws Exception
    {
        ios = new InitSystem();
        deliveryService =  ios.getDeliveryService();
        ds = ios.getDs();
        ts = ios.getTs();
        ss = ios.getSs();
        s = new Scanner(System.in);
    }
    public void runSystem()
    {
    
        

        boolean exit=false;
        while (!exit){
        System.out.println("WELOCME TO SUPER LEE - DELIVERY SYSTEM!");
        System.out.println();
        System.out.println("FOR TRUCK MENU - PRESS 1");
        System.out.println("FOR DRIVER MENU - PRESS 2");
        System.out.println("FOR SITE MENU - PRESS 3");
        System.out.println("FOR DELIVERY MENU - PRESS 4");
        System.out.println("EXIT - PRESS 5");
        System.out.println();
        //s.nextLine();
        String action;

        do{
            action  = s.nextLine();
        }while(action.length() == 0);

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
        System.out.println();
        System.out.println("TRUCK MENU");
        System.out.println();
        System.out.println("FOR ADDING TRUCK  - PRESS 1");
        System.out.println("FOR TRUCK INFORMATION  - PRESS 2");
        System.out.println("FOR PRINT ALL TRUCKS - PRESS 3");
        System.out.println("FOR GET AVIALIBLE TRUCKS BY DATE - PRESS 4");
        System.out.println("MAIN MENU  - PRESS 5");
        System.out.println();
        String action;
        do{
            action  = s.nextLine();
        }while(action.length() == 0);

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
        String model;

        do{
            model  = s.nextLine();
        }while(model.length() == 0);

        System.out.println();
        System.out.print("Enter weight without cargo : ");
        double weight  = s.nextDouble();
        System.out.println();
        System.out.print("Enter Max weight : ");
        double maxWeight  = s.nextDouble();
        System.out.println();
        System.out.print("Enter License Category : ");
        String category;

        do{
            category  = s.nextLine();
        }while(category.length() == 0);

        System.out.println();

        Response r = ts.addNewTruck(license, model, weight, maxWeight, category);
         if (r.ErrorOccured())
         {
            System.out.println(r.errorMessage);
         }
         else
         {
            System.out.println("Truck number - " + r .getReturnValue() + " Added successfully\n");
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
            for (DeliveryToSend d : t.getDates().getDates())
            {
                System.out.println("delivery date - " + d.getDepartureTime());
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
        System.out.println();
        System.out.println("DRIVER MENU");
        System.out.println();
            System.out.println("Driver Information  - PRESS 0");
        System.out.println("GET ALL DRIVERS  - PRESS 1");
        System.out.println("GET DRIVERS BY LICENSE  - PRESS 2");
        System.out.println("GET DRIVERS BY LICENSE AND DATE - PRESS 3");
        System.out.println("ADD NEW LICENSE TO DRIVER - PRESS 4");
        System.out.println("MAIN MENU  - PRESS 5");
        System.out.println();
        
        String action;
        do{
            action = s.nextLine();
        }while(action.length() == 0 );

        switch (action) {
            case "0":
                getDriverInfo();
                break;
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
    public void getDriverInfo()
    {
        System.out.print("Enter driver ID - ");
        String id = "";
        do{
            id = s.nextLine();
        }while(id.length() == 0 );

        System.out.println();

        Response r = ds.getDriver(id);

        if (r.ErrorOccured())
        {
            System.out.println(r.errorMessage);
        }
        else
        {
            DriverToSend  d = (DriverToSend)r.getReturnValue();
            System.out.println(d.toString());
        }
    }
    
    public void siteMenu(){
        boolean exit = false;
        while (!exit)
        {
        System.out.println();
        System.out.println("SITE MENU");
        System.out.println();
        //System.out.println("FOR ADDING NEW SITE  - PRESS 1");
        System.out.println("FOR SITE INFORMATION  - PRESS 1");
        System.out.println("FOR PRINT ALL SITES - PRESS 2");
        System.out.println("FOR UPDATING PHONE NUMBER OF A SITE - PRESS 3");
        System.out.println("FOR UPDATING CONTACT NAME OF A SITE - PRESS 4");
        System.out.println("MAIN MENU  - PRESS 5");
          
        String action;
        do{
            action = s.nextLine();
        }while(action.length() == 0 );

        switch (action) {
            case "1":
                getSite();
                break;
            case "2":
                getAllSites();
                break;
            case "3":
                updateSiteNUM();
                break;
            case "4":
                updateSiteCON();
                break;
            case "5":
                exit = true;
                break;
        }
    }}

//    public void addSite(){
//        System.out.print("Enter Address : ");
//        String address;
//        do{  address = s.nextLine();}while(address.length() == 0);
//        System.out.println();
//        System.out.print("Enter Phone Number : ");
//        String phoneNum ;
//        do{ phoneNum = s.nextLine();}while(phoneNum.length() == 0);
//        System.out.println();
//        System.out.print("Enter Contact Name : ");
//        String contactName;
//        do{  contactName = s.nextLine();}while(contactName.length() == 0);
//        System.out.println();
//        String shippingArea;
//        do{   shippingArea = s.nextLine();}while( shippingArea.length() == 0);
//        System.out.println();
//
//        //########
//        List<LocalDateTime> shiftsDates = new LinkedList<>();
//        shiftsDates.add(LocalDateTime.of(2024,8,1,12,0));
//        shiftsDates.add(LocalDateTime.of(2024,9,1,12,0));
//
//        List<EmployeeShift> shifts = new LinkedList<>();
//        EmployeeShift e1 = new EmployeeShift("1",shiftsDates,"Matan");
//        shifts.add(e1);
//
//        EmployeeShift e2 = new EmployeeShift("2",shiftsDates,"Matan");
//        shifts.add(e2);
//        //######
//        Response r =ss.addNewSite(address,phoneNum,contactName, shippingArea,shifts);
//        if (r.ErrorOccured())
//        {
//           System.out.println(r.errorMessage);
//        }
//        else{
//            System.out.println("Site "+ address+ " added succsesfully");
//        }
//    }



    public void getSite(){
        System.out.print("Enter Address : ");
        String address;
        do{
            address = s.nextLine();
        }while(address.length() == 0 );

        System.out.println();
        Response r = ss.getSite(address);
        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        
        else{
            System.out.println(r.returnValue.toString());;
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
        String address;
        do{
            address = s.nextLine();
        }while(address.length() == 0 );

        System.out.println();

        System.out.print("Enter phone Number : ");
        String phoneNumber;
        do{
           phoneNumber = s.nextLine();
        }while(phoneNumber.length() == 0 );

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
        String address;
        do{
            address = s.nextLine();
        }while(address.length() == 0 );

        System.out.println();

        System.out.print("Enter Contact Name : ");
        String contactName;
        do{
            contactName = s.nextLine();
        }while(contactName.length() == 0 );

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
        System.out.println();
        System.out.println("Delivery MENU");
        System.out.println();
        System.out.println("FOR GETTING DELIVERY INFORAMTION - PRESS 0");
        System.out.println("FOR ADDING NEW DELIVERY - PRESS 1");
        System.out.println("FOR REMOVING DELIVERY  - PRESS 2");
        System.out.println("FOR UPDATE WEIGHT- PRESS 3");
        System.out.println("FOR REPLACE TRUCK - PRESS 4");
        System.out.println("FOR REMOVE DESTINATION - PRESS 5");
        System.out.println("FOR REMOVE PRODUCT  - PRESS 6");
        System.out.println("FOR COMPLETE DELIVERY  - PRESS 7");
        System.out.println("GET DST DOCUMENT WITH DOC NUM - PRESS 8");
        System.out.println("GET DST DOCUMENT WITH ADDRESS - PRESS 9");
        System.out.println("SET DELIVERY INPROGRESS - PRESS 10");
        System.out.println("DISAPPROVE DELIVERY - PRESS 11");
        System.out.println("ADD DESTINATION DOC - PRESS 12");
        System.out.println("REMOVE DESTINATION DOC - PRESS 13");
        System.out.println("APPROVE DELIVERY - PRESS 14");        
        System.out.println("MAIN MENU  - PRESS 15");
        System.out.println();
        
        String action;
        do{
            action = s.nextLine();
        }while(action.length() == 0 );

        switch (action) {
            case "0":
                getDeliveryInfo();
                break;
            case "1":
                addNewDelivery();
                break;
            case "2":
                removeDelivery();
                break;
            case "3":
                updateWeight();
                break;
            case "4":
                replaceTruck();
                break;
            case "5":
                removeDestination();
                break;
            case "6":
                removeProduct();
                break;
            case "7":
                completeDelivery();
                break;
            case "8":
                getDestinationDocumentbyDocNumber();
                break;
            case "9":
                getDestinationDocumentbyAddress();
                break;
            case "10":
                inProgressDelivery();
                break;
            case "11":
                disapproveDelivery();
                break;
            case "12":
                addDstDoc();
                break;
            case "13":
                removeDstDoc();
                break;
            case "14":
                approveDelivery();
                break;        
            case "15":
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
        String license;
        do{  license = s.nextLine();}while(license.length() == 0);
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
        String license;
        do{  license = s.nextLine();}while(license.length() == 0);
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
        String id;
        do{  id = s.nextLine();}while(id.length() == 0);
        System.out.println();
        System.out.print("Enter license  - ");
        String license;
        do{  license = s.nextLine();}while(license.length() == 0);
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

//Delivery Functions

    public void getDeliveryInfo()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        Response r = deliveryService.getDeliveryInfo(dNum);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println( r.getReturnValue());
        }
    }
    public void addNewDelivery()
    {   
        //System.out.print("\033[A\033[2K");
        System.out.print("Enter driver id  - ");
        String driverID;
        do{  driverID = s.nextLine();}while(driverID.length() == 0);
        System.out.println();

        System.out.print("Enter origin address  - ");
        String address;
        do{  address = s.nextLine();}while(address.length() == 0);
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

        
        System.out.print("Enter license Number  - ");
        int licencseNumber = s.nextInt();
        System.out.println();

        Response r = deliveryService.addNewDelivery(LocalDateTime.of(LocalDate.of(year,month,day),LocalTime.of(hour,min)),
         licencseNumber, driverID, address);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " added successfully, notice that you must weight the truck before delivery starts");
        }
    }


    public void removeDelivery()
    {
        System.out.print("Enter delivery number to be deleted - ");
        int dNum = s.nextInt();
        Response r = deliveryService.removeDelivery(dNum);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " deleted successfully");
        }
    }

    public void updateWeight()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        System.out.print("Enter new truck weight - ");
        double newWeight = s.nextDouble();
        System.out.println();

        Response r = deliveryService.updateWeight(dNum,newWeight);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " weighted successfully with weight - " + newWeight);
        }
    }

    public void replaceTruck()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        System.out.print("Enter truck number - ");
        int truckNum = s.nextInt();
        System.out.println();

        Response r = deliveryService.replaceTruck(dNum,truckNum);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " update truck to - " + truckNum);
        }

    }

    public void removeDestination()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        System.out.print("Enter destination to be removed - ");
        String address;
        do{
            address = s.nextLine();
        }while(address.length() == 0);
        
        System.out.println();

        Response r = deliveryService.removeDestination(dNum,address);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " removed destination - " + address);
        }
    }

    public void removeProduct()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        System.out.print("Enter the relevant document number - ");
        Integer docNumber = s.nextInt();
        System.out.println();

        System.out.print("Enter product number to be removed - ");
        int productNum = s.nextInt();
        System.out.println();

        List<Integer> lp = new LinkedList<>();
        lp.add(productNum);

        Response r = deliveryService.removeProduct(dNum,docNumber,lp);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " removed product - " 
            + productNum + " from Document number"+ docNumber);
        }
    }

    public void completeDelivery()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        Response r = deliveryService.completeDelivery(dNum);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " status set to - complete");
        }
    }

    public void getDestinationDocumentbyAddress()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        System.out.print("Enter address of the document number - ");
        String address;
        do{
            address = s.nextLine();
        }while(address.length() == 0);
        
        System.out.println();

        Response r = deliveryService.getDestinationDocument(dNum,address);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Document number of this address is - " + r.getReturnValue());
        }
    }

    public void getDestinationDocumentbyDocNumber()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        System.out.print("Enter document number - ");
        int docNumber = s.nextInt();
        System.out.println();

        Response r = deliveryService.getDestinationDocument(dNum,docNumber);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Document number - " + r.getReturnValue());
        }
    }

    public void inProgressDelivery()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        Response r = deliveryService.inProgressDelivery(dNum);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue()+ " status set to in Progress");
        }

    }

    public void disapproveDelivery()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();

        Response r = deliveryService.disapproveDelivery(dNum);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue()+ " status set to disApprove");
        }
    }

    public void addDstDoc()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();
        System.out.print("Enter Address - ");
        String address;


        do{
            address = s.nextLine();
        }while(address.length() == 0);
        
        System.out.println();
        System.out.println("Estimated Arrival Time : ");
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

        int itemNumber = 0;
        String input;
        List<Integer> items = new LinkedList<Integer>();
        
        do
        {
            System.out.print("Enter Item number - ");
            itemNumber = s.nextInt();
            items.add(itemNumber);

            System.out.print("You want to enter another item ? yes / no - ");
            do{
                input = s.nextLine();
            }while(input.length() == 0);

        }
        while (!input.equals("no"));

        Response r = deliveryService.addDestinationDoc(dNum,items,address,LocalDateTime.of(year,month,day,hour,min));

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Document number - " + r.getReturnValue() + " Added successfully");
        }
    }

    public void removeDstDoc()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        System.out.println();
        System.out.print("Enter Document number - ");
        int docNumber = s.nextInt();
        System.out.println();

        Response r = deliveryService.removeDestinationDoc(dNum,docNumber);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Document number - " + r.getReturnValue() + " deleted successfully");
        }
    }

    public void approveDelivery()
    {
        System.out.print("Enter delivery number - ");
        int dNum = s.nextInt();
        Response r = deliveryService.approveDelivery(dNum);

        if (r.ErrorOccured())
        {
           System.out.println(r.errorMessage);
        }
        else
        {
            System.out.println("Delivery number - " + r.getReturnValue() + " approved successfully");
        }
    }
    //Exit the program
    public void exit()
    {
        System.out.println("Thank you");
        s.close();
    }
    }
