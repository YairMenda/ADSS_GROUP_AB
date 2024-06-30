package dev.DataAccessLayer;


import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.sql.Types;


public class ProductController {

    private String connectionString;
    private String productsTable;
    private String productsDataTable;
    private String priceToProductTable;

    public ProductController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
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

    public void insert(int productId, String storageName, String category, String subCategory, String productName,
                       String supplierName, double size, double price, double supplierPrice, int minimumRequired)
    {
        String sql1 = "INSERT INTO " + this.productsTable + " (StorageName, Category, SubCategory, ProductId) VALUES(?, ?, ?, ?)";
        String sql2 = "INSERT INTO " + this.productsDataTable + " (ProductId, ProductName, SupplierName, Size, MinimumRequired) VALUES(?, ?, ?, ?, ?)";
        String sql3 = "INSERT INTO " + this.priceToProductTable + " (ProductId, StoragePrice, SupplierPrice, Days, Discount, StartDate) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setString(1, storageName);
            pstmt.setString(2, category);
            pstmt.setString(3, subCategory);
            pstmt.setInt(4, productId);
            pstmt.executeUpdate();

            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, productId);
            pstmt2.setString(2, productName);
            pstmt2.setString(3, supplierName);
            pstmt2.setDouble(4, size);
            pstmt2.setInt(5, minimumRequired);
            pstmt2.executeUpdate();

            PreparedStatement pstmt3 = conn.prepareStatement(sql3);
            pstmt3.setInt(1, productId);
            pstmt3.setDouble(2, price);
            pstmt3.setDouble(3, supplierPrice);
            pstmt3.setNull(4, Types.INTEGER);  // Set Days to NULL
            pstmt3.setNull(5, Types.DOUBLE);   // Set Discount to NULL
            pstmt3.setNull(6, Types.DATE);
            pstmt3.executeUpdate();


            System.out.println("Product was inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean deleteProduct(ProductDTO pDTO)
    {
        boolean result = true;
        String query = "DELETE FROM " + this.productsTable + " WHERE ProductId = ?";
        String query2 = "DELETE FROM " + this.productsDataTable + " WHERE ProductId = ?";
        String query3 = "DELETE FROM " + this.priceToProductTable + " WHERE ProductId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pDTO.getProductId());
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setInt(1, pDTO.getProductId());
            PreparedStatement pstmt3 = conn.prepareStatement(query3);
            pstmt3.setInt(1, pDTO.getProductId());
            result = pstmt.executeUpdate() == 0 ? false : true;
            result = result && pstmt2.executeUpdate() == 0 ? false : true;
            result = result && pstmt3.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        //delete items DTO
        for(ItemDTO iDTO : pDTO.getItems())
        {
            result = result && iDTO.deleteItem();
        }

        return result;
    }

    public boolean updateStoragePrice(Double newPrice, int productId)
    {
        boolean result = true;
        String query = "UPDATE " + this.priceToProductTable + " SET StoragePrice = ? WHERE ProductId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, productId);
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return result;
    }

    public boolean updateSupPrice(Double newPrice, int productId)
    {
        boolean result = true;
        String query = "UPDATE " + this.priceToProductTable + " SET SupplierPrice = ? WHERE ProductId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, newPrice);
            pstmt.setInt(2, productId);
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return result;
    }

    public boolean updateDiscount(int discount, String startDate, int days, int productId)
    {
        boolean result = true;
        String query = "UPDATE " + this.priceToProductTable + " SET Discount = ?, StartDate = ?, Days = ? WHERE ProductId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, discount);
            pstmt.setString(2, startDate);
            pstmt.setInt(3, days);
            pstmt.setInt(4, productId);
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return result;
    }

    
}
