package BussinessLayer;
import java.util.*;

public class DriverFacade {

    private List<Driver> drivers;

    public DriverFacade(List<Driver> drivers)
    {
        this.drivers = drivers;
    }

    public void addDriver(Driver d)
    {
        this.drivers.add(d);
    }

    public void removeDriver(Driver d)
    {
        this.drivers.remove(d);
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Driver> getDriversByLicense(String license)
    {
        List<Driver> result = new LinkedList<Driver>();
        for (Driver d : this.drivers){
            if (d.hasLicense(license))
                {
                    result.add(d);   
                }
        }

        return result;   
    }

    public void updateLicense(String name,String license)
    {
        for (Driver d : this.drivers)
        {
            if (d.getName() == name)
            {
                d.addLicense(license);
            }
        }
    }

    public Driver getDriverByName(String driverName) 
    {
        for(Driver d : drivers){
            if(d.getName()==driverName){
                return d;
            } 
        }
        return null;
    }

    public boolean addDeliveryDate(String driverName,Date date)
    {
        Driver currDriver = getDriverByName(driverName);
        if (currDriver.isAvailable(date))
        {
            currDriver.addDelivery(date);
            return true;
        }

        else
            return false;

    }

    public void removeDelivery(String driverName,Date date)
    {
        getDriverByName(driverName).removeDelivery(date);
    }

    public void deliveryAcomplishment(String driverName,Date date)
    {
        getDriverByName(driverName).deliveryAcomplishment(date);
    }


}
