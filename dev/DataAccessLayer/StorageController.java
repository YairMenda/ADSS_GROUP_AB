package dev.DataAccessLayer;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StorageController 
{
    private String connectionString;
    private String tableName;

    public StorageController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.tableName = "Storages";
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

    public List<StorageDTO> getAllStorages()
    {
        List<StorageDTO> result = new LinkedList<>();  
        String query = "SELECT storageName FROM ?";    
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.tableName); // replacing ? into parameters
            ResultSet rs = pstmt.executeQuery(); // execute query
            List<String> storages = new LinkedList<>();

            // Process the result set
            while (rs.next()) {
                String storageName = rs.getString("storageName");
                storages.add(storageName);
            }

            // Create StorageDTO objects
            for (String storage : storages) {
                result.add(new StorageDTO(storage));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


}
