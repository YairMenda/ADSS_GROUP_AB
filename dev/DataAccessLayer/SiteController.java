package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SiteController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "Sites";

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

    public boolean add(SiteDTO siteDTO){

        //Employees to sites
        String query = "INSERT INTO "+ tableName + " (address, phoneNumber, contactName, shippingArea)" +
                " VALUES (?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1,siteDTO.getAddress());
            statement.setString(2,siteDTO.getPhoneNumber());
            statement.setString(3,siteDTO.getContactName());
            statement.setString(4,siteDTO.getShippingArea());
            statement.executeUpdate();
            //ResultSet rs = statement.excuteQuery();
            //while(rs.next)
            //{rs.getDeliveryId RESULT = NEW PRICETOPRODUCT}

        }catch(Exception e){
            return false;
        }
        return true;
    }
    public boolean update(SiteDTO siteDTO){
        String query =  "UPDATE "+ tableName +" SET phoneNumber = ?,ContactName = ?, shippingArea = ?  WHERE address = ?";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");????????
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, siteDTO.getPhoneNumber());
            statement.setString(2,siteDTO.getContactName());
            statement.setString(3,siteDTO.getShippingArea());
            statement.setString(4, siteDTO.getAddress());
            statement.executeUpdate();
        }catch(Exception e){
            return false;}
        return true;
    }
//
//    public List<DeliveryDTO> select()throws Exception{
//        List<DeliveryDTO> deliveries = new ArrayList<>();
//        connection = DriverManager.getConnection(DB_Path);
//        Statement stmt = connection.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM tableName");
//        while(rs.next()){
//            int deliveryID = rs.getInt("deliveryID");
//            int truckNumber = rs.getInt("truckNumber");
//            double truckWeight = rs.getDouble("truckWeight");
//            String driverID = rs.getString("driverID");
//            String Status = rs.getString("deliveryStatus");
//            LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
//            LocalDateTime departureTime = rs.getTimestamp("departureTime").toLocalDateTime();
//            LocalDateTime endTime = rs.getTimestamp("endTime").toLocalDateTime();
//            DeliveryDTO d = new DeliveryDTO(deliveryID,date,departureTime,truckNumber,truckWeight,driverID,Status,endTime);
//            deliveries.add(d);
//        }
//        return deliveries;
//    }

}
