package DataAccessLayer;

import BussinessLayer.EmployeeShift;

import java.util.LinkedList;
import java.util.List;

public class DriverDTO {

    private String name;
    private List<DriverToLicenseDTO> licenses;
    private String id;
    private EmployeeShiftDTO shifts;
    private DriverController controller = new DriverController();

    public DriverDTO(String id,String name, List<String> licenses,EmployeeShiftDTO shifts ){
        this.id = id;
        this.name = name;
        this.shifts = shifts;
        this.licenses = new LinkedList<>();
        for (String s: licenses){
            this.licenses.add(new DriverToLicenseDTO(id,s));
        }
    }

    public boolean UpdateLicense(String license) {
        DriverToLicenseDTO newLicense  = new DriverToLicenseDTO(id,license);
        licenses. add(newLicense);
        return newLicense.addLicense();
    }
}
