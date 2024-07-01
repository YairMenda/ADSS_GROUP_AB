package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckToDeliveryController {

    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "TrucksToDeliveries";

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

    public boolean add(TruckToDeliveryDTO truckToDeliveryDTO){

        //Employees to sites
        String query = "INSERT INTO "+ tableName + " (truckNumber, deliveryNumber)" +
                " VALUES (?,?)";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,truckToDeliveryDTO.getTruckNumber());
            statement.setInt(2,truckToDeliveryDTO.getDeliveryNumber());
            statement.executeUpdate();
            //ResultSet rs = statement.excuteQuery();
            //while(rs.next)
            //{rs.getDeliveryId RESULT = NEW PRICETOPRODUCT}

        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean remove(TruckToDeliveryDTO truckToDeliveryDTO){

        //Employees to sites
        String query = "DELETE FROM "+ tableName + " WHERE truckNumber=? AND deliveryNumber=?";
        try{
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1,truckToDeliveryDTO.getTruckNumber());
            statement.setInt(2,truckToDeliveryDTO.getDeliveryNumber());
            statement.executeUpdate();
            //ResultSet rs = statement.excuteQuery();
            //while(rs.next)
            //{rs.getDeliveryId RESULT = NEW PRICETOPRODUCT}

        }catch(Exception e){
            return false;
        }
        return true;
    }

    public List<TruckToDeliveryDTO> select(int truckNumber) throws Exception{
        List<TruckToDeliveryDTO> truckDeliveries = new ArrayList<>();
        Connection conn = this.connect();
        PreparedStatement stmt = conn.prepareStatement("SELECT deliveryNumber FROM "+ tableName + " WHERE truckNumber = ?");
        stmt.setInt(1,truckNumber);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            truckDeliveries.add(new TruckToDeliveryDTO(truckNumber,rs.getInt("deliveryNumber")));
        }
        return truckDeliveries;
    }
}
