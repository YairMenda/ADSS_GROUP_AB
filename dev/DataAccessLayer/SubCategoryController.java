package DataAccessLayer;

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
    private String subCategoryTable;

    public SubCategoryController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.subCategoryTable = "SubCategories";
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
        List<String> subCategories = new LinkedList<>();
        String query = "SELECT SubCategory FROM " + this.subCategoryTable + " WHERE StorageName = ? AND Category = ?";
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, storageName);
            pstmt.setString(2, category);
            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                String subCategory = rs.getString("SubCategory");
                subCategories.add(subCategory);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        for(String subCat : subCategories)
        {
            result.add(new SubCategoryDTO(storageName,category,subCat));
        }
        
        return result;
    }

    public void insert(String storageName, String categoryName, String subCategoryName)
    {
        String sql = "INSERT INTO " + this.subCategoryTable + " (StorageName, Category, SubCategory) VALUES(?, ?, ?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, storageName);
            pstmt.setString(2, categoryName);
            pstmt.setString(3, subCategoryName);
            pstmt.executeUpdate();
            System.out.println("Sub-Category inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteSubCategory(SubCategoryDTO scDTO)
    {
        boolean result = true;
        String query = "DELETE FROM " + this.subCategoryTable + " WHERE StorageName = ? AND Category = ? AND SubCategory = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, scDTO.getStorageName());
            pstmt.setString(2, scDTO.getCategoryName());
            pstmt.setString(3, scDTO.getSubCategoryName());
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        for(ProductDTO pDTO : scDTO.getProducts())
        {
            result = result && pDTO.deleteProduct();
        }
        return result;
    }
    
}
