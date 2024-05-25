package BussinessLayer;
import java.util.*;

public class DriverFacade {

    private Map<String , Driver> drivers;

    public DriverFacade(List<Driver> drivers)
    {
        this.drivers = new HashMap<>();
        for (Driver d : drivers){
            this.drivers.put(d.getId(),d);
        }
    }

    public List<Driver> getDrivers() {
        return new LinkedList<Driver>(drivers.values());
    }

    public boolean driverExist(String driverID) throws Exception
    {
        if (getDriverByID(driverID) == null)
            return false;
        return true;    
    }

    public List<Driver> getDriversByLicense(String license)
    {
        List<Driver> result = new LinkedList<Driver>();
        for (Driver d : this.drivers.values()){
            if (d.hasLicense(license))
                {
                    result.add(d);   
                }
        }

        return result;   
    }

    public List<Driver> getDriversByLicenseAndDate(String license,Date date)
    {
        List<Driver> result = new LinkedList<Driver>();
        for (Driver d : this.drivers.values()){
            if (d.hasLicense(license) && d.isAvailable(date))
                {
                    result.add(d);   
                }
        }

        return result;   
    }
    public void updateLicense(String id,String license) throws Exception
    {

        for (Driver d : this.drivers.values())
        {
            if (d.getId() == id)
            {
                d.addLicense(license);
            }
        }
    }

    public Driver getDriverByID(String driverID) throws Exception
    {
        if (drivers.containsKey(driverID))
            return drivers.get(driverID);
    
        throw new Exception("Site with this address already exists");
    }

    public boolean addDelivery(String driverID,Date date) throws Exception
    {
        Driver currDriver = getDriverByID(driverID);
        if (currDriver.isAvailable(date))
        {
            currDriver.addDelivery(date);
            return true;
        }

        else
            return false;

    }

    public void removeDelivery(String driverID,Date date) throws Exception
    {
        getDriverByID(driverID).removeDelivery(date);
    }

    public void deliveryAcomplishment(String driverID,Date date) throws Exception
    {
        getDriverByID(driverID).deliveryAcomplishment(date);
    }


}
