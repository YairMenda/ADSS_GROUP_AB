package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DstDocController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "DstDocs";
    private ItemToDstDocController controller= new ItemToDstDocController();
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
            statement.setString(3,dstDocDTO.getDestination());
            statement.setString(4,dstDocDTO.getEstimatedArrivalTime().toString());

            statement.executeUpdate();
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

    public List<DstDocDTO> select()throws Exception{
        List<DstDocDTO> dstDocs = new ArrayList<>();
        Connection connection = this.connect();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+ tableName);
        while(rs.next()) {
            int docNumber = rs.getInt("docNumber");
            int deliveryNumber = rs.getInt("deliveryNumber");
            String address = rs.getString("siteAddress");
            LocalDateTime estimatedArrivalTime = LocalDateTime.parse(rs.getString("estimatedArrivalTime"));
            List<ItemToDstDocDTO> items = controller.select(docNumber);
            dstDocs.add(new DstDocDTO(docNumber,deliveryNumber, items ,address, estimatedArrivalTime));
        }
        return dstDocs;
    }
}
