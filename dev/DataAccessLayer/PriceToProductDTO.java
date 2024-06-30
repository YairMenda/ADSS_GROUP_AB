package dev.DataAccessLayer;

public class PriceToProductDTO 
{
    private double storagePrice;
    private double supplierPrice;
    private int discount;
    private String startDate;
    private int days;


    public PriceToProductDTO(double storagePrice, double supplierPrice,int days, int discount, String startDate)
    {
        this.storagePrice = storagePrice;
        this.supplierPrice = supplierPrice;
        this.discount = discount;
        this.startDate = startDate;
        this.days = days;
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

    public int getDays()
    {
        return this.days;
    }

    public void setStoragePrice(Double newPrice)
    {
        this.storagePrice = newPrice;
    }

    public void setSupplierPrice(Double newSupPrice)
    {
        this.supplierPrice = newSupPrice;
    }

    public void setDiscount(int discount, String startDate, int days)
    {
        this.discount = discount;
        this.startDate = startDate;
        this.days = days;
    }

}
