package DataAccessLayer;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckController {
    private String DB_Path= "jdbc:sqlite:"+(Paths.get("").toAbsolutePath()).resolve("Super-Li.db").toString();

    private String tableName = "Trucks";
    private TruckToDeliveryController TruckToDeliveryController = new TruckToDeliveryController();

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

    public boolean add(TruckDTO truckDTO) {

        //Employees to sites
        String query = "INSERT INTO " + tableName + " (licenseNumber, model, weightWithoutCargo, maxWeight,licenseCategory)" +
                " VALUES (?,?,?,?,?)";
        try {
            Connection conn = this.connect();
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, truckDTO.getLicenseNumber());
            statement.setString(2, truckDTO.getModel());
            statement.setDouble(3, truckDTO.getWeightWitoutCargo());
            statement.setDouble(4, truckDTO.getMaxWeight());
            statement.setString(5, truckDTO.getLicenseCategory());
            statement.executeUpdate();
            //ResultSet rs = statement.excuteQuery();
            //while(rs.next)
            //{rs.getDeliveryId RESULT = NEW PRICETOPRODUCT}

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<TruckDTO> select()throws Exception{
        List<TruckDTO> trucks = new ArrayList<>();
        Connection conn = this.connect();
        Statement stmt =  conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM "+tableName);
        while(rs.next()){
            int licenseNumber = rs.getInt("licenseNumber");
            String model = rs.getString("model");
            double weightWithoutCargo = rs.getDouble("weightWithoutCargo");
            double maxWeight = rs.getDouble("maxWeight");
            String licenseCategory = rs.getString("licenseCategory");
            List<TruckToDeliveryDTO> deliveries = TruckToDeliveryController.select(licenseNumber);
            trucks.add(new TruckDTO(licenseNumber,model,weightWithoutCargo,maxWeight,licenseCategory,deliveries));
        }
        return trucks;
    }
}
