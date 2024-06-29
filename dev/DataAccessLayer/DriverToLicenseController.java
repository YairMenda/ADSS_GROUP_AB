package DataAccessLayer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverToLicenseController {

    private String DB_Path= "need to put the data base address";

    private String tableName = "the name of the table";

    private Connection connection = null;


    public boolean addLicense(DriverToLicenseDTO d){
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");????????
            connection = DriverManager.getConnection(DB_Path);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tableName (driverID, license) VALUES (?, ?)");
            statement.setString(1, d.getId());
            statement.setString(2, d.getLicense());
            statement.executeUpdate();

        }catch(Exception e){
            return false;
        }
        return true;
    }

    public List<String> select(String driverID) throws Exception{
            List<String> licenses = new ArrayList<>();
            //Class.forName("com.mysql.cj.jdbc.Driver");???????
            connection = DriverManager.getConnection(DB_Path);
            PreparedStatement stmt = connection.prepareStatement("SELECT license FROM tableName WHERE driverID=?");
            stmt.setString(1,driverID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                licenses.add(rs.getString("license"));
            }
            return licenses;

    }
}
