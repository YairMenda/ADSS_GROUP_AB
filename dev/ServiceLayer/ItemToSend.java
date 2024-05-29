package dev.ServiceLayer;
import java.time.LocalDate;

import dev.BusinessLayer.Item;

public class ItemToSend {

    private int id;
    private LocalDate expData; 
    private String location;
    private boolean damaged;
    private double boughtPrice;
    private double soldPrice;

    // Constructor for items
    public ItemToSend(Item item) {
        this.id = item.getId();
        this.expData = item.getExpData();
        this.location = item.getLocation();
        this.damaged = item.isDamaged();
        this.boughtPrice = item.getboughtPrice();
        this.soldPrice = item.getSoldPrice();
    }

    // Getters and Setters
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

    public double getBoughtPrice() {
        return boughtPrice;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

}
