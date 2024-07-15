package DataAccessLayer;

public class DriverToLicenseDTO {

    private String id;
    private String license;
    private DriverToLicenseController controller;



    public DriverToLicenseDTO(String id, String license){
        this.id = id;
        this.license = license;
        this.controller= new DriverToLicenseController();
    }


    public boolean addLicense(){
        return controller.addLicense(this);
    }


    public String getId() {
        return id;
    }

    public String getLicense(){
        return license;
    }
}
