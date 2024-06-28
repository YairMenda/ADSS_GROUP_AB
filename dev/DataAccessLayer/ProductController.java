package dev.DataAccessLayer;

import java.lang.classfile.constantpool.InterfaceMethodRefEntry;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductController {

    private String connectionString;
    private String tableName1;
    private String tableName2;

    public ProductController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.tableName1 = "Products";
        this.tableName2 = "ProductsData";
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

    public List<Integer> getProductsId(String storageName, String category, String subCategory)
    {
        List<Integer> products = new LinkedList<>();
        String query = "SELECT ProductID FROM ? WHERE StorageName = ? AND Category = ? AND SubCategory = ?";    
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.tableName1); // replacing ? into parameters
            pstmt.setString(2, storageName);
            pstmt.setString(3, category);
            pstmt.setString(4, category);
            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                Integer productId = rs.getInt("ProductID");
                products.add(productId);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    public List<ProductDTO> getAllProducts(String storageName, String category, String subCategory)
    {
        List<ProductDTO> result = new LinkedList<>();
        List<Integer> productsID = this.getProductsId(storageName, category, subCategory);
        String query = "SELECT ProductName, SupplierName, Size, MinimumRequired FROM ? WHERE ProductID = ?";
        for(Integer productID : productsID)
        {    
            try 
            {
                Connection conn = this.connect(); //connect to the db
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.tableName2); // replacing ? into parameters
                pstmt.setInt(2, productID);

                ResultSet rs = pstmt.executeQuery(); // execute query

                while (rs.next()) {
                    String productName = rs.getString("ProductName");
                    String supplierName = rs.getString("SupplierName");
                    Double size = rs.getDouble("Size");
                    Integer minimumRequired = rs.getInt("MinimumRequired");
                    result.add(new ProductDTO(storageName, category, subCategory, productID, productName, supplierName, size, minimumRequired));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    
}
