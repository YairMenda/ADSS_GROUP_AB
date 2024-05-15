package BussinessLayer;
import java.util.List;

public class Driver {

    private String name;
    private List<Char> licenses;
    
    public Driver(String name, List<Char> licenses)
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
}