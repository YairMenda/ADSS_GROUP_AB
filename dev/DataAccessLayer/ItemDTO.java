package dev.DataAccessLayer;

import java.time.LocalDate;
import java.util.List;

public class ItemDTO {

    private int itemId;
    private String expDate;
    private String location;
    private boolean damaged;
    private double boughtPrice;
    private double soldPrice;
    private String sellDate;
    private ItemController itemController;

    public ItemDTO(int itemId, String expDate, String location, boolean damaged, double boughtPrice, double soldPrice, String sellDate)
    {
        this.itemId = itemId;
        this.expDate = expDate;
        this.location = location;
        this.damaged = damaged;
        this.boughtPrice = boughtPrice;
        this.soldPrice = soldPrice;
        this.sellDate = sellDate;
        this.itemController = new ItemController();
    }
    

    public int getItemId() {
        return itemId;
    }
    
    
    public String getExpDate() {
        return expDate;
    }
    
    public String getLocation() {
        return location;
    }
    
    public boolean isDamaged() {
        return damaged;
    }
    
    public double getBoughtPrice() {
        return boughtPrice;
    }
    
    public double getSoldPrice() {
        return soldPrice;
    }
    
    public String getSellDate() {
        return sellDate;
    }

    public ItemController getItemController()
    {
        return this.itemController;
    }
}
