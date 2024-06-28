package DataAccessLayer;

public class DriverToLicenseDTO {

    private String id;
    private String license;
    private DriverToLicenseController controller= new DriverToLicenseController();



    public DriverToLicenseDTO(String id, String license){
        this.id = id;
        this.license = license;
    }


    public boolean addLicense(){
        return controller.addLicense(this);
    }
}
