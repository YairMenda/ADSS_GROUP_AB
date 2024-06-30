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
    private String categoriesTable;

    public CategoryController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.categoriesTable = "Categories";
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
        List<String> categories = new LinkedList<>();
        String query = "SELECT StorageName, Category FROM " + this.categoriesTable + " WHERE StorageName = ?";
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, storageName);
            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                String category = rs.getString("Category");
                categories.add(category);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for(String category : categories)
        {
            result.add(new CategoryDTO(storageName,category));
        }
        
        return result;
    }

    public void insert(String storageName, String categoryName)
    {
        String sql = "INSERT INTO " + this.categoriesTable + " (StorageName, Category) VALUES(?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, storageName);
            pstmt.setString(2, categoryName);
            pstmt.executeUpdate();
            System.out.println("Category inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteCategory(CategoryDTO cDTO)
    {
        boolean result = true;
        String query = "DELETE FROM " + this.categoriesTable + " WHERE StorageName = ? AND Category = ?";
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, cDTO.getStorageName());
            pstmt.setString(2, cDTO.getCategoryName());
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        for(SubCategoryDTO scDTO : cDTO.getSubCategories())
        {
            result = result && scDTO.deleteSubCategory();
        }
        return result;
    }
}
