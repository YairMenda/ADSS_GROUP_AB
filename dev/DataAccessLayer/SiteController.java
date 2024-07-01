package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SiteController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "Sites";

    private EmployeeToSiteController empSiteCon;


    private EmployeeShiftController employeeShiftController;

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

    public List<SiteDTO> select()throws Exception{
        List<SiteDTO> sites = new ArrayList<>();
        Connection connection = this.connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+ tableName);
        while(rs.next()) {
            int deliveryID = rs.getInt("deliveryID");
            String address = rs.getString("address");
            String phoneNumber = rs.getString("phoneNumber");
            String contactName = rs.getString("contactName");
            String shippingArea = rs.getString("shippingArea");
            List<EmployeeShiftDTO> siteShifts = new ArrayList<>();
            List<EmployeeToSiteDTO> ests = this.empSiteCon.select(address);
            for (EmployeeToSiteDTO etsDTO: ests ){
                List<EmployeeShiftDTO> eShifts = this.employeeShiftController.select(etsDTO.getEmployeeID());
                for (EmployeeShiftDTO s : eShifts){
                    siteShifts.add(s);
                }
            }
            sites.add(new SiteDTO(address, phoneNumber, contactName, shippingArea, siteShifts));
        }
        return sites;
    }

}
