package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DriverToDeliveryController {
    private String DB_Path= "need to put the data base address";

    private String tableName = "the name of the table";

    private Connection connection = null;




    public boolean add(DriverToDeliveryDTO dtd){
        try{
            //Class.forName("com.mysql.cj.jdbc.Driver");????????
            connection = DriverManager.getConnection(DB_Path);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tableName (driverID, delivetyNumber) VALUES (?, ?)");
            statement.setString(1, dtd.getDriverID());
            statement.setInt(2, dtd.getDeliveryNumber());
            statement.executeUpdate();
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean delete(DriverToDeliveryDTO dtd) {
        try {
            connection = DriverManager.getConnection(DB_Path);
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM tableName WHERE driverID = ? AND deliveryNumber = ?");
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
        //Class.forName("com.mysql.cj.jdbc.Driver");???????
        connection = DriverManager.getConnection(DB_Path);
        PreparedStatement stmt = connection.prepareStatement("SELECT deliveryNumber FROM tableName WHERE driverID=?");
        stmt.setString(1,DriverID);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            deliveries.add(rs.getInt("deliveryNumber"));
        }
        return deliveries;
    }
}
