package dev.DataAccessLayer;

public class PriceToProductDTO 
{
    private double storagePrice;
    private double supplierPrice;
    private int discount;
    private String startDate;

    public PriceToProductDTO(double storagePrice, double supplierPrice, int discount, String startDate)
    {
        this.storagePrice = storagePrice;
        this.supplierPrice = supplierPrice;
        this.discount = discount;
        this.startDate = startDate;
    }

    public double getStoragePrice() {
        return storagePrice;
    }

    public double getSupplierPrice() {
        return supplierPrice;
    }

    // Getter for discount
    public int getDiscount() {
        return discount;
    }

    public String getStartDate() {
        return startDate;
    }
}
