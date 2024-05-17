package dev;
import java.util.List;
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Product {
    private String productName;
    private String category;
    private String subCategory;
    private String supplierName; // Changed to String assuming 'supplierName' should be textual
    private List<Item> items;
    private List<Item> soldItems;
    private double size;
    private double discount; // -1 equals no discount

    // Constructor
    public Product(String productName, String category, String subCategory, String supplierName,
                    double size) {
        this.productName = productName;
        this.category = category;
        this.subCategory = subCategory;
        this.supplierName = supplierName;
        this.items = new LinkedList<Item>();
        this.size = size;
        this.discount = -1;
    }

    //Calculates the total decent items left
    public int itemsLeft(){
        int availableItems = 0;
        for (Item  i : items) {
            if(!i.isDamaged() && i.getExpData().compareTo(LocalDate.now()) >= 0)
                availableItems++;
        }
        return availableItems;
    }

    //handles the sale of an item
    public void sellItem(int itemId, double price){
        for (Item i : items) {
            if(i.getId() == itemId){
                i.setsoldPrice(price);
                i.setSellDate(LocalDate.now());
                items.remove(i);
                soldItems.add(i);
            }
        }
    }

    //Avg items sold per month
    public int avgSoldInMonth(){
        int totalSold = 0;
        LocalDate minimumSellDate = LocalDate.now();
        for(Item i: soldItems){
            totalSold++;
            if(i.getSellDate().compareTo(minimumSellDate) < 0)
                minimumSellDate = i.getSellDate();

        }
        int daysFromFirstSell = (int)ChronoUnit.DAYS.between(LocalDate.now(), minimumSellDate);

        return (totalSold / daysFromFirstSell) * 30;
    }

    public LinkedList<Item> getExpiredItems(){
        LinkedList<Item> exProducts = new LinkedList<>();
        for(Item i : items){
            if(i.isExpired())
                exProducts.add(i);
        }
        return exProducts;
    }

    // Return true iff the number of good items left is lower than the avarage items selt per month
    public boolean needsAlert(){
        return itemsLeft() <= avgSoldInMonth(); 
    }


    // return list of damaged items
    public LinkedList<Item> getDamagedProducts()
    {
        LinkedList<Item> damagedList = new LinkedList<>();
        for (Item it : this.items)
        {
            if(it.isDamaged())
            {
                damagedList.add(it);
            }
        }
        return damagedList;
    }

    public String toString()
    {
        return "Product name: " + this.productName + "\n "
                + "Category: " + this.category + "\n "
                + "Sub category: " + this.subCategory + "\n "
                + "Supplier name: " + this.supplierName + "\n " 
                + "Product size: " + this.size + "\n "
                + "Quantity left: " + this.itemsLeft() + "\n "
                + "Sold count: " + this.soldItems.size() + "\n ";
    }

    // Getters
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

    public List<Item> getItems() {
        return items;
    }

    public double getSize() {
        return size;
    }

    public double getDiscount() {
        return discount;
    }

    // Setters
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void addItem(LocalDate expDate, double boughtPrice){
        items.add(new Item(expDate,boughtPrice));
    }

    
}
