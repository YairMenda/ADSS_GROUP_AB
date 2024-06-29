package dev.DataAccessLayer;


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
    private String productsTable;
    private String productsDataTable;
    private String priceToProductTable;

    public ProductController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("ADSS_GROUP_AB").resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.productsTable = "Products";
        this.productsDataTable = "ProductsData";
        this.priceToProductTable = "PriceToProduct";
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

    public PriceToProductDTO createPricesDTO(int productId)
    {
        PriceToProductDTO result = null;
        String query = "SELECT StoragePrice, SupplierPrice, Days, Discount, StartDate FROM " + this.priceToProductTable +
                " WHERE ProductId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                double storagePrice = rs.getDouble("storagePrice");
                double supplierPrice = rs.getDouble("supplierPrice");
                int days = rs.getInt("Days");
                int discount = rs.getInt("discount");
                String startDate = rs.getString("startDate");
                result = new PriceToProductDTO(storagePrice,supplierPrice,days,discount,startDate);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Integer> getProductsId(String storageName, String category, String subCategory)
    {
        List<Integer> products = new LinkedList<>();
        String query = "SELECT ProductId FROM " + this.productsTable + " WHERE StorageName = ? AND Category =  ? AND SubCategory = ?";
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, storageName);
            pstmt.setString(2, category);
            pstmt.setString(3, subCategory);
            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                Integer productId = rs.getInt("ProductId");
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
        String query = "SELECT ProductName, SupplierName, Size, MinimumRequired FROM " + this.productsDataTable +
                " WHERE ProductID = ?";
        for(Integer productID : productsID)
        {    
            try 
            {
                Connection conn = this.connect(); //connect to the db
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, productID);
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
