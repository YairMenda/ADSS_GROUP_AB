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
    private String storagesTable;

    public StorageController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.storagesTable = "Storages";
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
        List<String> storages = new LinkedList<>();
        String query = "SELECT StorageName FROM " + this.storagesTable;
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery(); // execute query

            // Process the result set
            while (rs.next()) {
                String storageName = rs.getString("StorageName");
                storages.add(storageName);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for (String storage : storages) {
            result.add(new StorageDTO(storage));
        }
        return result;
    }


}
