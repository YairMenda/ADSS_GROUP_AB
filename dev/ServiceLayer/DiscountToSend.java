package dev.ServiceLayer;

import dev.BusinessLayer.Discount;

public class DiscountToSend {

    private double supplierDiscountPrice;
    private double storageDiscountPrice;
    private int daysLeft;

    // Constructor
    public DiscountToSend(Discount discount) {
        this.supplierDiscountPrice = discount.getSupplierDiscountPrice();
        this.storageDiscountPrice = discount.getStorageDiscountPrice();
        this.daysLeft = discount.getdaysLeft();
    }

    // Getters
    public double getSupplierDiscountPrice() {
        return supplierDiscountPrice;
    }

    public double getStorageDiscountPrice() {
        return storageDiscountPrice;
    }

    public int getDaysLeft()
    {
        return this.daysLeft;
    }
}

