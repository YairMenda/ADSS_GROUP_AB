package DataAccessLayer;
import BussinessLayer.Delivery;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeliveryController {

    private String DB_Path= "need to put the data base address";

    private String tableName = "the name of the table";

    private Connection connection = null;



    public boolean add(DeliveryDTO deliveryDTO){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_Path);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO tableName (deliveryNumber, date, departureTime, truckNumber,truckWeight,driverID, deliveryStatus,endTime) VALUES (?,?,?,?,?,?,?,?)");
            statement.setInt(1, deliveryDTO.getDeliveryNumber());
            statement.setTimestamp(2, deliveryDTO.getSQLDate());
            statement.setTimestamp(3, deliveryDTO.getSQLDepartureTime());
            statement.setInt(4, deliveryDTO.getTruckNumber());
            statement.setDouble(5, deliveryDTO.getTruckWeight());
            statement.setString(6,deliveryDTO.getDriverID());
            statement.setString(7,deliveryDTO.getDeliveryStatus());
            statement.setTimestamp(8,deliveryDTO.getSQLEndTime());
            statement.executeUpdate();

        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean remove(DeliveryDTO deliveryDTO){
      try {
          //Class.forName("com.mysql.cj.jdbc.Driver");???????
          connection = DriverManager.getConnection(DB_Path);
          PreparedStatement stmt = connection.prepareStatement("DELETE FROM tableName WHERE deliveryID = ?");
          stmt.setInt(1, deliveryDTO.getDeliveryNumber());
          stmt.executeUpdate();
      }catch(Exception e){
          return false;}
      return true;
    }
    public boolean update(DeliveryDTO deliveryDTO){
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");????????
            connection = DriverManager.getConnection(DB_Path);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE tableName SET date = ?, departureTime = ?,truckNumber = ?, truckWeight = ?, driverID = ?, deliveryStatus = ?, endTime = ?  WHERE deliveryNumber = ?");
            statement.setTimestamp(1, deliveryDTO.getSQLDate());
            statement.setTimestamp(2, deliveryDTO.getSQLDepartureTime());
            statement.setInt(3, deliveryDTO.getTruckNumber());
            statement.setDouble(4, deliveryDTO.getTruckWeight());
            statement.setString(5,deliveryDTO.getDriverID());
            statement.setString(6,deliveryDTO.getDeliveryStatus());
            statement.setTimestamp(7,deliveryDTO.getSQLEndTime());
            statement.setInt(8,deliveryDTO.getDeliveryNumber());
            statement.executeUpdate();
        }catch(Exception e){
            return false;}
        return true;
    }

    public List<DeliveryDTO> select()throws Exception{
        List<DeliveryDTO> deliveries = new ArrayList<>();
        connection = DriverManager.getConnection(DB_Path);
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tableName");
        while(rs.next()){
            int deliveryID = rs.getInt("deliveryID");
            int truckNumber = rs.getInt("truckNumber");
            double truckWeight = rs.getDouble("truckWeight");
            String driverID = rs.getString("driverID");
            String Status = rs.getString("deliveryStatus");
            LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
            LocalDateTime departureTime = rs.getTimestamp("departureTime").toLocalDateTime();
            LocalDateTime endTime = rs.getTimestamp("endTime").toLocalDateTime();
            DeliveryDTO d = new DeliveryDTO(deliveryID,date,departureTime,truckNumber,truckWeight,driverID,Status,endTime);
            deliveries.add(d);
        }
        return deliveries;
    }

    }

