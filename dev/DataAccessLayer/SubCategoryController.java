package dev.DataAccessLayer;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SubCategoryController {

    private String connectionString;
    private String tableName;

    public SubCategoryController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.tableName = "Sub_Categories";
    }

    private Connection connect() 
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<SubCategoryDTO> getSubCategories(String storageName, String category)
    {
        List<SubCategoryDTO> result = new LinkedList<>();
        String query = "SELECT SubCategory FROM ? WHERE StorageName = ? AND Category = ?";    
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.tableName); // replacing ? into parameters
            pstmt.setString(2, storageName);
            pstmt.setString(3, category);
            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                String subCategory = rs.getString("SubCategory");
                result.add(new SubCategoryDTO(storageName,category,subCategory));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    
}
