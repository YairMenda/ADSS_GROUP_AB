package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverToDeliveryController {
    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "DriversToDeliveries";

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


    public boolean add(DriverToDeliveryDTO dtd){
        String query = "INSERT INTO " + tableName +" (driverID, deliveryNumber) VALUES (?, ?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, dtd.getDriverID());
            statement.setInt(2, dtd.getDeliveryNumber());
            statement.executeUpdate();
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean remove(DriverToDeliveryDTO dtd) {

        String query = "DELETE FROM " +tableName+ " WHERE driverID = ? AND deliveryNumber = ?";
        try {
            Connection connection = this.connect();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, dtd.getDriverID());
            stmt.setInt(2, dtd.getDeliveryNumber());
            stmt.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public List<Integer> select(String DriverID) throws Exception{
        List<Integer> deliveries = new ArrayList<>();
        Connection conn = this.connect();
        PreparedStatement stmt = conn.prepareStatement("SELECT deliveryNumber FROM "+tableName +" WHERE driverID=?");
        stmt.setString(1,DriverID);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            deliveries.add(rs.getInt("deliveryNumber"));
        }
        return deliveries;
    }
}
