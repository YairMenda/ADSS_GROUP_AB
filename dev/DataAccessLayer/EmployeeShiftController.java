package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EmployeeShiftController {
    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "EmployeeShifts";

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

    public List<EmployeeShiftDTO> select(String employeeID) throws Exception
    {
        String query = "SELECT * FROM "+tableName + " WHERE employeeID = ?";
        List<EmployeeShiftDTO> shiftDTOS = new ArrayList<>();
        Connection conn = this.connect();
        PreparedStatement stmt =  conn.prepareStatement(query);
        stmt.setString(1,employeeID);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            LocalDateTime shift = LocalDateTime.parse(rs.getString("shift"));
            String site = rs.getString("site");
            EmployeeShiftDTO d = new EmployeeShiftDTO(employeeID,shift,site);
            shiftDTOS.add(d);
        }
        return shiftDTOS;

    }

    public List<EmployeeShiftDTO> selectByaddress(String address) throws Exception
    {
        String query = "SELECT * FROM "+tableName + " WHERE site = ?";
        List<EmployeeShiftDTO> shiftDTOS = new ArrayList<>();
        Connection conn = this.connect();
        PreparedStatement stmt =  conn.prepareStatement(query);
        stmt.setString(1,address);
        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            LocalDateTime shift = LocalDateTime.parse(rs.getString("shift"));
            String employeeID = rs.getString("employeeID");
            EmployeeShiftDTO d = new EmployeeShiftDTO(employeeID,shift,address);
            shiftDTOS.add(d);
        }
        return shiftDTOS;

    }
    public boolean add(EmployeeShiftDTO es){

        String query = "INSERT INTO "+ tableName + " (employeeID, shift,site)" +
                " VALUES (?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, es.getEmployeeID());
            statement.setString(2,es.getShift().toString());
            statement.setString(3, es.getSite());
            statement.executeUpdate();
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
