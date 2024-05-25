/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ServiceLayer;
import BussinessLayer.*;

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


    public String getAllDrivers(){
        try{
            List<Driver> drivers = df.getDrivers();
            List<String> l = new LinkedList<>();
            for (Driver d  : drivers){
                l.add(d.getId()+" "+d.getName());
            }
            return JsonSerializer.Serialize(new Response(l,null));}
            catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
        
    }

    public String getDriversByLicense(String license){
        try{
            List<Driver> drivers = df.getDriversByLicense(license);
            if (drivers.size()==0)
                return JsonSerializer.Serialize(new Response(null, "no drivers with that license"));
            List<String> l = new LinkedList<>();
            for (Driver d  : drivers){
                l.add(d.getId()+" "+d.getName());
            }
            return JsonSerializer.Serialize(new Response(l,null));}
            catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
        
    }

    public String getDriversByLicenseAndDate(String license, Date date){
        try{
            List<Driver> drivers = df.getDriversByLicenseAndDate(license,date);
            if (drivers.size()==0)
                return JsonSerializer.Serialize(new Response(null, "no drivers with that license that are availiable on that date"));
            List<String> l = new LinkedList<>();
            for (Driver d  : drivers){
                l.add(d.getId()+" "+d.getName());
            }
            return JsonSerializer.Serialize(new Response(l,null));}
            catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }



    public String addNewLicenseToDriver(String driverID, String license){
        try{
            df.updateLicense(driverID,license);
            return JsonSerializer.Serialize(new Response(driverID,null));}
            catch(Exception e)
        {
            return JsonSerializer.Serialize(new Response(null, e.getMessage()));
        }
    }






    
}

