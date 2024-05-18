package ServiceLayer;
import BussinessLayer.TruckFacade;
import java.util.Date;


public class TruckService {

    private TruckFacade tf;

    public TruckService(TruckFacade tf){
        this.tf=tf;
    }


    public String addNewTruck(int licenseNumber, String model, double weightWithoutCargo,double maxWeight,String licenseCategory){
        throw new UnsupportedOperationException();
    }

    public String getTruck(int licenseNumber){
        throw new UnsupportedOperationException();
    }

    public String getAllTrucks(){
        throw new UnsupportedOperationException();
    }

    public String getAvialibleTrucks(Date date){
        throw new UnsupportedOperationException();}



}
