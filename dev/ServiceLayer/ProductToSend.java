package ServiceLayer;
import BussinessLayer.*;

public class ProductToSend {

    private int productId;
    private String productName;
    private String category;
    private String subCategory;
    private String supplierName;
    private double price;
    private double supplierPrice;
    private double size;
    private DiscountToSend discount;
    private String description; 

    // Constructor
    public ProductToSend(Product p) {

        this.productId = p.getId();
        this.productName = p.getProductName();
        this.category = p.getCategory();;
        this.subCategory = p.getSubCategory();
        this.supplierName = p.getSupplierName();
        this.price = p.getStoragePrice();
        this.supplierPrice = p.getSupplierPrice();
        this.size = p.getSize();
        this.discount = new DiscountToSend(p.geDiscount());
        this.description = p.toString();
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public double getPrice() {
        return price;
    }

    public double getSupplierPrice() {
        return supplierPrice;
    }

    public double getSize() {
        return size;
    }

    public DiscountToSend getDiscount() {
        return discount;
    }

    public String getDescription()
    {
        return this.description;
    }
}
