package BussinessLayer;
import java.time.LocalDateTime;
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

    public List<Driver> getDriversByLicenseAndDate(String license,LocalDateTime date)
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
            if (d.getId().equals(id))
            {
                d.addLicense(license);
            }
        }
    }

    public Driver getDriverByID(String driverID) throws Exception
    {
        if (drivers.containsKey(driverID))
            return drivers.get(driverID);
    
        throw new Exception("driver " + driverID+ "doesn't exist");
    }

    public boolean addDelivery(String driverID,LocalDateTime date) throws Exception
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

    public void removeDelivery(String driverID,LocalDateTime date) throws Exception
    {
        getDriverByID(driverID).removeDelivery(date);
    }

    public void deliveryAcomplishment(String driverID,LocalDateTime date) throws Exception
    {
        getDriverByID(driverID).deliveryAcomplishment(date);
    }


    public boolean isAvailable(String driverID,LocalDateTime date)throws Exception{
        return getDriverByID(driverID).isAvailable(date);
    }

    public boolean hasLicense(String driverID,String license) throws Exception{
        Driver d = getDriverByID(driverID);
        return d.hasLicense(license);
    }


}
