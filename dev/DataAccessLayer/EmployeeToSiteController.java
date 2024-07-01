package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeToSiteController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "EmployeeToSite";

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

    public List<EmployeeToSiteDTO> select(String address) throws Exception
    {
        String query = "SELECT * FROM "+tableName + " WHERE address = ?";
        List<EmployeeToSiteDTO> shiftDTOS = new ArrayList<>();
        Connection conn = this.connect();
        PreparedStatement stmt =  conn.prepareStatement(query);
        stmt.setString(1,address);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String id = rs.getString("employeeID");
            EmployeeToSiteDTO d = new EmployeeToSiteDTO(address,id);
            shiftDTOS.add(d);
        }
        return shiftDTOS;

    }

}
