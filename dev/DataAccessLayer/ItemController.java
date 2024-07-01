package dev.DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ItemController 
{

    private String connectionString;
    private String itemsTable;
    private String itemDataTable;

    public ItemController()
    {
        String path = (Paths.get("").toAbsolutePath()).resolve("Super-li.db").toString();
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
                    Boolean damaged = rs.getInt("Damaged") == 0 ? false : true;
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

    public boolean deleteItem(ItemDTO iDTO)
    {
        boolean result = true;
        String query = "DELETE FROM " + this.itemsTable + " WHERE ItemId = ?";
        String query2 = "DELETE FROM " + this.itemDataTable + " WHERE ItemId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, iDTO.getItemId());
            PreparedStatement pstmt2 = conn.prepareStatement(query2);
            pstmt2.setInt(1, iDTO.getItemId());
            result = pstmt.executeUpdate() == 0 ? false : true;
            result = result && pstmt2.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return result;
    }

    public boolean insert(ItemDTO iDTO){
        String sql1 = "INSERT INTO " + this.itemDataTable + " (ItemId, ExpDate, Location, Damaged, BoughtPrice, SoldPrice, SellDate) VALUES(?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql1)) {
            pstmt.setInt(1, iDTO.getItemId());
            pstmt.setString(2, iDTO.getExpDate());
            pstmt.setString(3, iDTO.getLocation());
            pstmt.setInt(4, iDTO.isDamaged() ? 1 : 0);
            pstmt.setDouble(5, iDTO.getBoughtPrice());
            pstmt.setNull(6, Types.DOUBLE);
            pstmt.setNull(7, Types.DATE);
            pstmt.executeUpdate();



            System.out.println("Item was inserted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateSale(int itemId, double soldPrice, String sellDate)
    {
        boolean result = true;
        String query = "UPDATE " + this.itemDataTable + " SET SoldPrice = ?, sellDate = ? WHERE ItemId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, soldPrice);
            pstmt.setString(2, sellDate);
            pstmt.setInt(3, itemId);
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return result;
    }

    public boolean setDamaged(int itemId)
    {
        boolean result = true;
        String query = "UPDATE " + this.itemDataTable + " SET Damaged = ? WHERE ItemId = ?";
        try
        {
            Connection conn = this.connect(); //connect to the db
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setBoolean(1, true);
            pstmt.setInt(2, itemId);
            result = pstmt.executeUpdate() == 0 ? false : true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return result;
    }
}
