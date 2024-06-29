package dev.DataAccessLayer;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ItemController 
{

    private String connectionString;
    private String tableName1;
    private String tableName2;

    public ItemController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.tableName1 = "Items";
        this.tableName2 = "ItemsData";
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

    public List<Integer> getItemsId(int productId)
    {
        List<Integer> items = new LinkedList<>();
        String query = "SELECT ItemId FROM ? WHERE ProductId = ?";    
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, this.tableName1); // replacing ? into parameters
            pstmt.setInt(2, productId);

            ResultSet rs = pstmt.executeQuery(); // execute query

            while (rs.next()) {
                Integer itemId = rs.getInt("ItemId");
                items.add(itemId);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
    }

    public List<ItemDTO> getItems(int productId)
    {
        List<ItemDTO> result = new LinkedList<>();
        List<Integer> itemsId = getItemsId(productId);
        String query = "SELECT ItemId, ExpDate, Location, Damaged, BoughtPrice, SoldPrice, SellDate FROM ? WHERE ProductID = ?";
        for(Integer id : itemsId)
        {    
            try 
            {
                Connection conn = this.connect(); //connect to the db
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, this.tableName2); // replacing ? into parameters
                pstmt.setInt(2, id);

                ResultSet rs = pstmt.executeQuery(); // execute query

                while (rs.next()) {
                    Integer itemId = rs.getInt("ItemId");
                    String expDate = rs.getString("ExpDate");
                    String location = rs.getString("Location");
                    Boolean damaged = rs.getBoolean("Damaged");
                    Double boughtPrice = rs.getDouble("BoughtPrice");
                    Double soldPrice = rs.getDouble("SoldPrice");
                    String sellDate = rs.getString("SellDate");
                    result.add(new ItemDTO(itemId,expDate,location,damaged,boughtPrice,soldPrice,sellDate));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
