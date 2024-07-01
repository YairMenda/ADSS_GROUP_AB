package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DstDocController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "DstDocs";

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

    public boolean add(DstDocDTO dstDocDTO){

        //Employees to sites
        String query = "INSERT INTO "+ tableName + " (docNumber,deliveryNumber,siteAddress,estimatedArrivalTime)" +
                " VALUES (?,?,?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,dstDocDTO.getDocNumber());
            statement.setInt(2,dstDocDTO.getDeliveryNumber());
            statement.setString(3,dstDocDTO.getDestination().getAddress());

            //statement.setDate(4,dstDocDTO.getEstimatedArrivalTime());

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
    public boolean remove(DstDocDTO dstDocDTO){
        String query =  "DELETE  FROM "+ tableName +" WHERE docNumber = ?";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");????????
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,dstDocDTO.getDocNumber());
            statement.executeUpdate();
        }catch(Exception e){
            return false;}
        return true;
    }
}
