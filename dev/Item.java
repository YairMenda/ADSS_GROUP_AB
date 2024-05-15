package dev;
import java.time.LocalDate; // import the LocalDate class

public class Item {
    private static int id = 1;
    private LocalDate expData; // Assuming Time is a valid type for expData
    private String location;
    private boolean damaged;
    private double supPrice;
    private double soldPrice;
    private LocalDate sellDate;

    // Constructor for items
    public Item(LocalDate expData, double supPrice) {
        this.id = id++;
        this.expData = expData;
        this.location = "Warehouse";
        this.damaged = false;
        this.supPrice = supPrice;
        this.soldPrice = -1;
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
    public double getSupPrice() {
        return supPrice;
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
    public void setSupPrice(double supPrice) {
        this.supPrice = supPrice;
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
    
}
