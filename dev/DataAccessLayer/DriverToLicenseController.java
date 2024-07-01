package DataAccessLayer;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverToLicenseController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "DriversToLicenses";

    private Connection connect()
    {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_Path);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;

    }
    public boolean addLicense(DriverToLicenseDTO d){
        String query = "INSERT INTO "+tableName +" (driverID, license) VALUES (?, ?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
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
            Connection conn = this.connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT license FROM " +tableName+" WHERE driverID=?");
            stmt.setString(1,driverID);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                licenses.add(rs.getString("license "));
            }
            return licenses;

    }
}
