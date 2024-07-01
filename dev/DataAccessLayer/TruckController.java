package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TruckController {
    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "Trucks";

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

    public boolean add(TruckDTO truckDTO){

        //Employees to sites
        String query = "INSERT INTO "+ tableName + " (licenseNumber, model, weightWithoutCargo, maxWeight,licenseCategory)" +
                " VALUES (?,?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,truckDTO.getLicenseNumber());
            statement.setString(2,truckDTO.getModel());
            statement.setDouble(3,truckDTO.getWeightWitoutCargo());
            statement.setDouble(4,truckDTO.getMaxWeight());
            statement.setString(5,truckDTO.getLicenseCategory());
            statement.executeUpdate();
            //ResultSet rs = statement.excuteQuery();
            //while(rs.next)
            //{rs.getDeliveryId RESULT = NEW PRICETOPRODUCT}

        }catch(Exception e){
            return false;
        }
        return true;
    }
//    public boolean update(SiteDTO siteDTO){
//        String query =  "UPDATE "+ tableName +" SET phoneNumber = ?,ContactName = ?, shippingArea = ?  WHERE adress = ?";
//        try {
//            //Class.forName("com.mysql.cj.jdbc.Driver");????????
//            Connection conn = this.connect();
//            PreparedStatement statement = conn.prepareStatement(query);
//            statement.setString(1, siteDTO.getPhoneNumber());
//            statement.setString(2,siteDTO.getContactName());
//            statement.setString(3,siteDTO.getShippingArea());
//            statement.setString(4, siteDTO.getAddress());
//            statement.executeUpdate();
//        }catch(Exception e){
//            return false;}
//        return true;
//    }
}
