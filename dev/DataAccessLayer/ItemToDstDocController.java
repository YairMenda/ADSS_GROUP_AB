package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemToDstDocController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "ItemsToDstDocs";

    private Connection connect()
    {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(DB_Path);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;

    }

    public boolean add(ItemToDstDocDTO itemToDstDocDTO){

        //Employees to sites
        String query = "INSERT INTO "+ tableName + " (docNumber,itemNumber)" +
                " VALUES (?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,itemToDstDocDTO.getDocNumber());
            statement.setInt(2,itemToDstDocDTO.getItem());
            statement.executeUpdate();
            //ResultSet rs = statement.excuteQuery();
            //while(rs.next)
            //{rs.getDeliveryId RESULT = NEW PRICETOPRODUCT}

            //for (itemsToDstDoc item : dstDocDTO.getItems())
            //item.add();

        }catch(Exception e){
            return false;
        }
        return true;
    }
    public boolean remove(ItemToDstDocDTO itemToDstDocDTO){
        String query =  "DELETE  FROM "+ tableName +" WHERE docNumber = ? AND itemNumber = ?";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");????????
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,itemToDstDocDTO.getDocNumber());
            statement.setInt(2,itemToDstDocDTO.getItem());
            statement.executeUpdate();
        }catch(Exception e){
            return false;}
        return true;
    }
}
