package dev.DataAccessLayer;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryController {

    private String connectionString;
    private String tableName;

    public CategoryController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.tableName = "Categories";
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.connectionString);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<CategoryDTO> getStorageCategories(String storageName)
    {
        List<CategoryDTO> result = new LinkedList<>();
        String query = "SELECT Category FROM ? WHERE StorageName = ?";    
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.tableName); // replacing ? into parameters
            pstmt.setString(2, storageName);
            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                String category = rs.getString("Category");
                result.add(new CategoryDTO(storageName,category));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return result;
    }
    
}
