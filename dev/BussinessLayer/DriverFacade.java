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

    /// <summary>
    /// the method gets all the drivers
    /// </summary>
    /// <returns>a list of drivers, throws exception if fails</returns>
    public List<Driver> getDrivers() {
        return new LinkedList<Driver>(drivers.values());
    }


    /// <summary>
    /// the method checks of a specific driver exist
    /// </summary>
    /// <param name="driverID"> the driver's ID we want to check</param>
    /// <returns>true if exist,false if not.Throws exception if fails</returns>
    public boolean driverExist(String driverID) throws Exception
    {
        if (getDriverByID(driverID) == null)
            return false;
        return true;    
    }
    /// <summary>
    /// the method gets the drivers with a specific license
    /// </summary>
    /// <param name="license"> the specific license we want</param>
    /// <returns>list of drivers , throws exception if fails</returns>
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

    /// <summary>
    /// the method gets the drivers which are availiable in a specific date with a specific license
    /// </summary>
    /// <param name="license"> the specific license we want</param>
    /// <param name="date"> the specific date we want</param>
    /// <returns>list of drivers , throws exception if fails</returns>
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

    /// <summary>
    /// the method adds another license to a specific driver
    /// </summary>
    /// <param name="id"> the driver's id</param>
    /// <param name="license"> the specific license we want</param>
    /// <returns> throws exception if fails</returns>
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
    /// <summary>
    /// the method gets a driver
    /// </summary>
    /// <param name="driverID"> the driversID we wanr</param>
    /// <returns>driver, throws exception if fails</returns>

    public Driver getDriverByID(String driverID) throws Exception
    {
        if (drivers.containsKey(driverID))
            return drivers.get(driverID);
    
        throw new Exception("driver " + driverID+ "doesn't exist");
    }


    /// <summary>
    /// the method update the availability of a specific driver
    /// </summary>
    /// <param name="driverID"> the driver's ID</param>
    /// <param name="date"> the specific date we want</param>
    /// <returns>true or false, throws exception if fails</returns>
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

    /// <summary>
    /// the method update the availability of a specific driver
    /// </summary>
    /// <param name="driverID"> the driver's ID</param>
    /// <param name="date"> the specific date we want to remove to</param>
    /// <returns>throws exception if fails</returns>
    public void removeDelivery(String driverID,LocalDateTime date) throws Exception
    {
        getDriverByID(driverID).removeDelivery(date);
    }

    /// <summary>
    /// the method update the availability of a specific driver
    /// </summary>
    /// <param name="driverID"> the driver's ID</param>
    /// <param name="date"> the specific date we want</param>
    /// <returns>throws exception if fails</returns>
    public void deliveryAcomplishment(String driverID,LocalDateTime date) throws Exception
    {
        getDriverByID(driverID).deliveryAcomplishment(date);
    }

    /// <summary>
    /// the method checks the availability of a specific driver on a specific date
    /// </summary>
    /// <param name="driverID"> the driver's ID</param>
    /// <param name="date"> the specific date we want</param>
    /// <returns>true or false, throws exception if fails</returns>
    public boolean isAvailable(String driverID,LocalDateTime date)throws Exception{
        return getDriverByID(driverID).isAvailable(date);
    }


    /// <summary>
    /// the method checks if a driver has a specific license
    /// </summary>
    /// <param name="driverID"> the driver's ID</param>
    /// <param name="date"> the specific license we want to check if he has</param>
    /// <returns>true or false, throws exception if fails</returns>
    public boolean hasLicense(String driverID,String license) throws Exception{
        Driver d = getDriverByID(driverID);
        return d.hasLicense(license);
    }


}
