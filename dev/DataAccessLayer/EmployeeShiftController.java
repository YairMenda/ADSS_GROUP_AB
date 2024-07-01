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
            LocalDateTime shift = rs.getTimestamp("shift").toLocalDateTime();
            EmployeeShiftDTO d = new EmployeeShiftDTO(employeeID,shift);
            shiftDTOS.add(d);
        }
        return shiftDTOS;

    }

}
