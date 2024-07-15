package DataAccessLayer;

import BussinessLayer.Driver;

import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DriverController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "Drivers";
    private DriverToLicenseController licenseController = new DriverToLicenseController();
    private DriverToDeliveryController deliveryController =new DriverToDeliveryController();
    private EmployeeShiftController shiftController = new EmployeeShiftController();


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
    public List<DriverDTO> select()throws Exception{
        List<DriverDTO> drivers = new ArrayList<>();
        Connection conn = this.connect();
        Statement stmt =  conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
        while(rs.next()){
            String driverID = rs.getString("driverID");
            String name = rs.getString("name");
            DriverDTO d = new DriverDTO(driverID,name,licenseController.select(driverID),shiftController.select(driverID),deliveryController.select(driverID));
            drivers.add(d);
        }
        return drivers;
    }


    public boolean add(DriverDTO driverDTO){

        String query = "INSERT INTO "+ tableName + " (driverID, name)" +
                " VALUES (?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, driverDTO.getId());
            statement.setString(2,driverDTO.getName());
            statement.executeUpdate();

        }catch(Exception e){
            return false;
        }
        return true;
    }

}
