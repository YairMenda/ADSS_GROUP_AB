package dev.BusinessLayer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Discount {

    private double supplierDiscountPrice;
    private double storageDiscountPrice;
    private LocalDate startDate;
    private int days;

    public Discount(double supplierDiscountPrice, double storageDiscountPrice)
    {
        this.supplierDiscountPrice = supplierDiscountPrice;
        this.storageDiscountPrice = storageDiscountPrice;
        this.startDate = null;
        this.days = 0;
    }

    public double getSupplierDiscountPrice()
    {
        return this.supplierDiscountPrice;
    }

    public double getStorageDiscountPrice()
    {
        return this.storageDiscountPrice;
    }

    
    public int getdaysLeft()
    {
        return (int)ChronoUnit.DAYS.between(LocalDate.now(), this.startDate) - this.days;
    }

    public void setSupplierDiscount(double supplierDiscountPrice)
    {
        this.supplierDiscountPrice = supplierDiscountPrice;
    }

    public void setStorageDiscount(double storageDiscountPrice, int days)
    {
        this.storageDiscountPrice = storageDiscountPrice;
        this.startDate = LocalDate.now();
        this.days = days;
    }

    //update days left and return true if days left, false otherwise
    public void updateDiscount(double price)
    {
        this.days = (int)ChronoUnit.DAYS.between(LocalDate.now(), this.startDate);
        if(this.days < 0)
        {
            this.storageDiscountPrice = price;
            this.days = 0;
            this.startDate = null;
        }
    }
}
