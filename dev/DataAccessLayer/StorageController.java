package DataAccessLayer;

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
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();
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

    public boolean deleteStorage(StorageDTO storageDTO)
    {
        boolean result = true;
        String query = "DELETE FROM " + this.storagesTable + " WHERE StorageName = ?";
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, storageDTO.getStorageName());
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        for(CategoryDTO cDTO : storageDTO.getCategories())
        {
            result = result && cDTO.deleteCategory();
        }
        return result;
    }

    public void insert(String storageName)
    {
        String sql = "INSERT INTO " + this.storagesTable + " (StorageName) VALUES(?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, storageName);
            pstmt.executeUpdate();
            System.out.println("Storage inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
