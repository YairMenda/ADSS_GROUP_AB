package DataAccessLayer;

import BussinessLayer.Delivery;
import BussinessLayer.EmployeeShift;

import java.util.LinkedList;
import java.util.List;

public class DriverDTO {

    private String name;
    private List<DriverToLicenseDTO> licenses;
    private String id;
    private List<EmployeeShiftDTO> shifts;
    private List<DriverToDeliveryDTO> deliveries;
    private DriverController controller = new DriverController();

    public DriverDTO(String id,String name, List<String> licenses,List<EmployeeShiftDTO> shifts,List<DriverToDeliveryDTO> deliveries ){
        this.id = id;
        this.name = name;
        this.shifts = shifts;
        this.licenses = new LinkedList<>();
        for (String s: licenses){
            this.licenses.add(new DriverToLicenseDTO(id,s));
        }
        this.deliveries=deliveries;
    }

    public DriverDTO(String driverID,String name) {
        this.id= driverID;
        this.name=name;
        this.shifts= new LinkedList<>();
        this.deliveries=new LinkedList<>();
    }

    public boolean UpdateLicense(String license) {
        DriverToLicenseDTO newLicense  = new DriverToLicenseDTO(id,license);
        licenses. add(newLicense);
        return newLicense.addLicense();
    }

    public String getName() {
        return name;
    }
    public String getId()
    {
        return id;
    }
    public List<DriverToLicenseDTO> getLicenses()
    {
        return licenses;
    }
    public boolean addDelivery(int deliveryNumber)
    {
        DriverToDeliveryDTO dd = new DriverToDeliveryDTO(this.id,deliveryNumber);
        this.deliveries.add(dd);
        return dd.add();

    }

    public boolean removeDelivery(int deliveryNumber)
    {
        for (DriverToDeliveryDTO dd : this.deliveries){
            if (dd.getDeliveryNumber() == deliveryNumber)
            {
                dd.remove();
                return this.deliveries.remove(dd);
            }
        }
        return false;
    }

}
