package dev.BusinessLayer;
import dev.DataAccessLayer.ItemDTO;

import java.time.LocalDate; // import the LocalDate class
import java.time.format.DateTimeFormatter;

public class Item {
    private static int idNum = 1;
    private int id;
    private String productName;
    private LocalDate expData; // Assuming Time is a valid type for expData
    private String location;
    private boolean damaged;
    private double boughtPrice;
    private Double soldPrice;
    private LocalDate sellDate;

    // Constructor for items
    public Item(LocalDate expData, double boughtPrice, String productName) {
        this.id = idNum++;
        this.productName = productName;
        this.expData = expData;
        this.location = "Warehouse";
        this.damaged = false;
        this.boughtPrice = boughtPrice;
        this.soldPrice = null;
        this.sellDate = null;
    }

    public Item(ItemDTO iDTO, String productName)
    {
        this.id = iDTO.getItemId();
        this.productName = productName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.expData = LocalDate.parse(iDTO.getExpDate(),formatter);
        this.location = iDTO.getLocation();
        this.damaged = iDTO.isDamaged();
        this.boughtPrice = iDTO.getBoughtPrice();
        this.soldPrice = iDTO.getSoldPrice();
        this.sellDate = iDTO.getSellDate() == null ? null : LocalDate.parse(iDTO.getSellDate(),formatter);
    }
    
    public String toString()
    {
        String s =  "Item name: " + this.productName + "-item \n"
                +"Item id: " + this.id + "\n"
                + "Experation date: " + this.expData + "\n"
                + "Location: " + this.location + "\n"
                + "Damaged: " + this.damaged + "\n"
                + "Bought price: " + this.boughtPrice + "\n"
                + "Sold price: " + (this.soldPrice == -1 ? "none" : this.soldPrice) + "\n"
                + "Sell date: " + sellDateString() + "\n";
        return s;
    }

    public String sellDateString()
    {
        return this.sellDate == null ? "none" : this.sellDate.toString();
    }
    // Getters
    public int getId() {
        return id;
    }

    public String getProductName()
    {
        return this.productName;
    }

    public LocalDate getExpData() {
        return expData;
    }
    public String getLocation() {
        return location;
    }
    public boolean isDamaged() {
        return damaged;
    }
    public double getboughtPrice() {
        return boughtPrice;
    }
    public LocalDate getSellDate() {
        return sellDate;
    }
    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setExpData(LocalDate expData) {
        this.expData = expData;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDamaged() {
        this.damaged = !this.damaged;
    }
    public void setboughtPrice(double boughtPrice) {
        this.boughtPrice = boughtPrice;
    }
    public void setsoldPrice(double price){
        this.soldPrice = price;
    }
    public void setSellDate(LocalDate date){
        this.sellDate = date;
    }

    public boolean isExpired(){
        return getExpData().compareTo(LocalDate.now()) < 0;
                
    }
    public double getSoldPrice()
    {
        return this.soldPrice;
    }
    
}
