package DataAccessLayer;
import BussinessLayer.Delivery;

import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeliveryController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "Deliveries";

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

    public boolean add(DeliveryDTO deliveryDTO){

        String query = "INSERT INTO "+ tableName + " (deliveryNumber, date, departureTime, truckNumber,truckWeight,driverID, deliveryStatus,endTime,origin)" +
                " VALUES (?,?,?,?,?,?,?,?,?)";


        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, deliveryDTO.getDeliveryNumber());
            statement.setTimestamp(2, deliveryDTO.getSQLDate());
            statement.setTimestamp(3, deliveryDTO.getSQLDepartureTime());
            statement.setInt(4, deliveryDTO.getTruckNumber());
            statement.setDouble(5, deliveryDTO.getTruckWeight());
            statement.setString(6,deliveryDTO.getDriverID());
            statement.setString(7,deliveryDTO.getDeliveryStatus());
            statement.setTimestamp(8,deliveryDTO.getSQLEndTime());
            statement.setString(9,deliveryDTO.getOrigin());
            statement.executeUpdate();


            //ResultSet rs = statement.excuteQuery();
            //while(rs.next)
            //{rs.getDeliveryId RESULT = NEW PRICETOPRODUCT}
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean remove(DeliveryDTO deliveryDTO){
        String query = "DELETE FROM "+ tableName + " WHERE deliveryID = ?";
        try {
          Connection conn = this.connect();
          PreparedStatement stmt = conn.prepareStatement(query);
          stmt.setInt(1, deliveryDTO.getDeliveryNumber());
          stmt.executeUpdate();
      }catch(Exception e){
          return false;}
      return true;
    }
    public boolean update(DeliveryDTO deliveryDTO){
        String query = "UPDATE tableName SET truckNumber = ?, truckWeight = ?, deliveryStatus = ?, endTime = ? WHERE deliveryNumber = ?";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, deliveryDTO.getTruckNumber());
            statement.setDouble(2, deliveryDTO.getTruckWeight());
            statement.setString(3,deliveryDTO.getDeliveryStatus());
            statement.setTimestamp(4,deliveryDTO.getSQLEndTime());
            statement.setInt(5,deliveryDTO.getDeliveryNumber());
            statement.executeUpdate();
        }catch(Exception e){
            return false;}
        return true;
    }

    public List<DeliveryDTO> select()throws Exception{
        List<DeliveryDTO> deliveries = new ArrayList<>();
        Connection conn = this.connect();
        Statement stmt =  conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
        while(rs.next()){
            int deliveryID = rs.getInt("deliveryNumber");
            int truckNumber = rs.getInt("truckNumber");
            double truckWeight = rs.getDouble("truckWeight");
            String driverID = rs.getString("driverID");
            String Status = rs.getString("deliveryStatus");
            LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
            LocalDateTime departureTime = rs.getTimestamp("departureTime").toLocalDateTime();
            LocalDateTime endTime = rs.getTimestamp("endTime").toLocalDateTime();
            String origin = rs.getString("origin");
            DeliveryDTO d = new DeliveryDTO(deliveryID,date,departureTime,truckNumber,truckWeight,driverID,Status,endTime,origin);
            deliveries.add(d);
        }
        return deliveries;
    }

    }

