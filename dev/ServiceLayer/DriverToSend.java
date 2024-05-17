package ServiceLayer;
import java.util.List;

public class DriverToSend {

    private String name;
    private List<String> licenses;
    
    public DriverToSend(String name, List<String> licenses)
    {

        this.name = name;
        this.licenses = licenses;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLicenses() {
        return this.licenses;
    }

    public void setLicenses(List<String> licenses) {
        this.licenses = licenses;
    }

}
