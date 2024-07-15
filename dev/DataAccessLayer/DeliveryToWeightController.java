package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryToWeightController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "DeliveryToWeight";

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


    public boolean add(DeliveryToWeightDTO dw){
        String query = "INSERT INTO " + tableName +" (deliveryNumber, weight) VALUES (?, ?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, dw.getDeliveryNumber());
            statement.setDouble(2, dw.getTruckWeight());
            statement.executeUpdate();
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public List<DeliveryToWeightDTO> select(int deliveryNumber) throws Exception{
        List<DeliveryToWeightDTO> weightHistory = new ArrayList<>();
        Connection conn = this.connect();
        PreparedStatement stmt = conn.prepareStatement("SELECT weight FROM "+tableName +" WHERE deliveryNumber=?");
        stmt.setInt(1,deliveryNumber);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            weightHistory.add(new DeliveryToWeightDTO(deliveryNumber,rs.getDouble("weight")));
        }
        return weightHistory;
    }
}
