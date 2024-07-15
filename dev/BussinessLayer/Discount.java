package BussinessLayer;

import DataAccessLayer.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class Discount {

    private double supplierDiscountPrice;
    private double storageDiscountPrice;
    private LocalDate startDate;
    private int days;
    private int discountPre;

    public Discount(double supplierDiscountPrice, double storageDiscountPrice)
    {
        this.supplierDiscountPrice = supplierDiscountPrice;
        this.storageDiscountPrice = storageDiscountPrice;
        this.startDate = null;
        this.days = 0;
        this.discountPre = 0;
    }

    public Discount(PriceToProductDTO ptpDTO)
    {
        this.supplierDiscountPrice = ptpDTO.getSupplierPrice();
        this.storageDiscountPrice = ptpDTO.getStoragePrice();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.startDate = ptpDTO.getStartDate() == null ? null : LocalDate.parse(ptpDTO.getStartDate(),formatter);
        this.days = ptpDTO.getDays();
        this.discountPre = ptpDTO.getDiscount();
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
        if(startDate == null)
            return 0;
        return this.days - (int)Duration.between(LocalDate.now(), this.startDate).toDays();
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
        if(this.startDate != null)
        {
            this.days = (int)Duration.between(LocalDate.now(),this.startDate).toDays();
            if(this.days < 0)
            {
                this.storageDiscountPrice = price;
                this.days = 0;
                this.startDate = null;
            }
        }
    }

    public void setDiscountPre(int discountPre)
    {
        this.discountPre = discountPre;
    }

    public int getDiscountPre()
    {
        return this.discountPre;
    }
}
