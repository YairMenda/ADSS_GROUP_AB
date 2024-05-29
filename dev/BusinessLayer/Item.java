package dev.BusinessLayer;
import java.time.LocalDate; // import the LocalDate class

public class Item {
    private static int idNum = 1;
    private int id;
    private LocalDate expData; // Assuming Time is a valid type for expData
    private String location;
    private boolean damaged;
    private double boughtPrice;
    private double soldPrice;
    private LocalDate sellDate;

    // Constructor for items
    public Item(LocalDate expData, double boughtPrice) {
        this.id = idNum++;
        this.expData = expData;
        this.location = "Warehouse";
        this.damaged = false;
        this.boughtPrice = boughtPrice;
        this.soldPrice = -1;
    }
    
    public String toString()
    {
        return "Item id: " + this.id + "\n"
                + "Experation date: " + this.expData + "\n "
                + "Location: " + this.location + "\n"
                + "Damaged: " + this.damaged + "\n"
                + "Bought price: " + this.boughtPrice + "\n"
                + "Sold price: " + this.soldPrice + "\n"
                + "Sell date: " + this.sellDate.toString() + "\n";
    }
    // Getters
    public int getId() {
        return id;
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
    public void setDamage(boolean damage) {
        this.damaged = damage;
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
        return getExpData().compareTo(LocalDate.now()) >= 0;
                
    }
    public double getSoldPrice()
    {
        return this.soldPrice;
    }
    
}
