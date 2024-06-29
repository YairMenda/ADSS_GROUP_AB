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
    private String itemsTable;
    private String itemDataTable;

    public ItemController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("ADSS_GROUP_AB").resolve("Super-li.db").toString();
        this.connectionString = "jdbc:sqlite:" + path; // need to connect the path 
        this.itemsTable = "Items";
        this.itemDataTable = "ItemsData";
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
        String query = "SELECT ItemId FROM " + this.itemsTable + " WHERE ProductId = ?";
        try 
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, productId);
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
        for(Integer id : itemsId)
        {
            String query = "SELECT ExpDate, Location, Damaged, BoughtPrice, SoldPrice, SellDate FROM " + this.itemDataTable +
                    " WHERE ItemId = ?";
            try 
            {
                Connection conn = this.connect(); //connect to the db
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery(); // execute query

                while (rs.next()) {
                    String expDate = rs.getString("ExpDate");
                    String location = rs.getString("Location");
                    Boolean damaged = rs.getInt("Damaged") == 0 ? true : false;
                    Double boughtPrice = rs.getDouble("BoughtPrice");
                    Double soldPrice = rs.getDouble("SoldPrice");
                    String sellDate = rs.getString("SellDate");
                    result.add(new ItemDTO(id,expDate,location,damaged,boughtPrice,soldPrice,sellDate));
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
