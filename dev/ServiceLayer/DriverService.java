/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ServiceLayer;
import BussinessLayer.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Omera
 */
public class DriverService {
    private DriverFacade df;



    public DriverService(DriverFacade df){
        this.df=df;
    }


    //returns response with list of driversID, or error message if error occurs
    public Response getAllDrivers(){
        try{
            List<Driver> drivers = df.getDrivers();
            List<String> l = new LinkedList<>();
            for (Driver d  : drivers){
                l.add(d.getId()+" "+d.getName());
            }
            return new Response(l,null);}
            catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
        
    }
    public Response getDriver(String id) {
        try {
            Driver driver = df.getDriverByID(id);
            return new Response(new DriverToSend(driver), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

        //returns response with list of driversID, or error message if error occurs
    public Response getDriversByLicense(String license){
        try{
            List<Driver> drivers = df.getDriversByLicense(license);
            if (drivers.size()==0)
                return new Response(null, "no drivers with that license");
            List<String> l = new LinkedList<>();
            for (Driver d  : drivers){
                l.add(d.getId()+" "+d.getName());
            }
            return new Response(l,null);}
            catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
        
    }

    //returns response with list of driversID, or error message if error occurs
    public Response getDriversByLicenseAndDate(String license, LocalDateTime date){
        try{
            List<Driver> drivers = df.getDriversByLicenseAndDate(license,date);
            if (drivers.size()==0)
                return new Response(null, "no drivers with that license that are availiable on that date");
            List<String> l = new LinkedList<>();
            for (Driver d  : drivers){
                l.add(d.getId()+" "+d.getName());
            }
            return new Response(l,null);}
            catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }


    //returns response with driverID, or error message if error occurs
    public Response addNewLicenseToDriver(String driverID, String license){
        try{
            df.updateLicense(driverID,license);
            return new Response(driverID,null);}
            catch(Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }






    
}

