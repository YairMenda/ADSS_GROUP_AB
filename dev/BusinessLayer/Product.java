package dev.BusinessLayer;
import dev.DataAccessLayer.ItemDTO;
import dev.DataAccessLayer.ProductDTO;

import java.util.LinkedList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class Product {
    private static int productIdNum = 0;
    private int productId;
    private String productName;
    private String category;
    private String subCategory;
    private String storageName;
    private String supplierName; // Changed to String assuming 'supplierName' should be textual
    private double price;
    private double supplierPrice;
    private LinkedList<Item> items;
    private LinkedList<Item> soldItems;
    private double size;
    private int minimumRequired;
    private Discount discount; // -1 equals no discount
    private ProductDTO pDTO;

    // Constructor
    public Product(String storageName,String productName, String category, String subCategory, String supplierName,
                    double size , double price, double supplierPrice, int minimumRequired) {
        this.storageName = storageName;
        this.productId = productIdNum++;
        this.productName = productName;
        this.category = category;
        this.subCategory = subCategory;
        this.supplierName = supplierName;
        this.price = price;
        this.supplierPrice = supplierPrice;
        this.items = new LinkedList<Item>();
        this.soldItems = new LinkedList<>();
        this.size = size;
        this.minimumRequired = minimumRequired;
        this.discount = new Discount(supplierPrice,this.price);
        this.pDTO = new ProductDTO(storageName,category,subCategory,productId,productName,supplierName,size,minimumRequired);
    }

    public Product(ProductDTO pDTO)
    {
        this.storageName = pDTO.getStorageName();
        this.productId = pDTO.getProductId();
        this.productName = pDTO.getProductName();
        this.category = pDTO.getCategory();
        this.subCategory = pDTO.getSubCategory();
        this.supplierName = pDTO.getSupplierName();
        this.discount = new Discount(pDTO.getPriceToProductDTO());
        this.items = fillAvailableItems(pDTO.getItems());
        this.soldItems = fillSoldItems(pDTO.getItems());
        this.size = pDTO.getSize();
        this.minimumRequired = pDTO.getMinimumRequired();
        this.pDTO = pDTO;
    }

    public ProductDTO getProductDTO()
    {
        return this.pDTO;
    }

    public LinkedList<Item> fillAvailableItems(List<ItemDTO> items)
    {
        LinkedList<Item> result = new LinkedList<>();
        for(ItemDTO iDTO : items)
        {
            if(iDTO.getSellDate() == null)
                result.add(new Item(iDTO,this.productName));
        }
        return result;
    }

    public LinkedList<Item> fillSoldItems(List<ItemDTO> items)
    {
        LinkedList<Item> result = new LinkedList<>();
        for(ItemDTO iDTO : items)
        {
            if(iDTO.getSellDate() != null)
                result.add(new Item(iDTO,this.productName));
        }
        return result;
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
    public void sellItem(int itemId){
        LinkedList<Item> newItems = new LinkedList<>();
        for (Item i : items) {
            if(i.getId() == itemId){
                i.setsoldPrice(getStoragePrice());
                i.setSellDate(LocalDate.now());
                soldItems.add(i);
            }
            else
                newItems.add(i);
        }
        setItems(newItems);
        if(belowMin())
            System.out.println("Product " + productName + " is reaching its end!\nOnly " + itemsLeft() + " left\nThe average month selling rate of this item is " + avgSoldInMonth());

    }

    //Avg items sold per month
    public double avgSoldInMonth(){
        double totalSold = 0;
        LocalDate minimumSellDate = LocalDate.now();
        for(Item i: soldItems){
            totalSold++;
            if(i.getSellDate().compareTo(minimumSellDate) < 0)
                minimumSellDate = i.getSellDate();

        }
        double daysFromFirstSale = (double)ChronoUnit.DAYS.between(LocalDate.now(), minimumSellDate)+1;
        return (totalSold / daysFromFirstSale) * 30;
    }

    public LinkedList<Item> getExpiredItems(boolean drop){
        LinkedList<Item> exProducts = new LinkedList<>();
        LinkedList<Item> newList = new LinkedList<>();
        for(Item i : items)
        {
            if(i.isExpired())
                exProducts.add(i);
            else
                newList.add(i);
        }
        setItems(newList);
        if(belowMin())
            System.out.println("Product " + productName + " is reaching its end!\nOnly " + itemsLeft() + " left\nThe average month selling rate of this item is " + avgSoldInMonth());
        return exProducts;
    }

    // Return true iff the number of good items left is lower than the avarage items selt per month
    public boolean belowMin(){
        return itemsLeft() < this.minimumRequired; 
    }

    
    // return list of damaged items
    public LinkedList<Item> getDamagedItems(boolean drop)
    {
        LinkedList<Item> damagedList = new LinkedList<>();
        LinkedList<Item> newList = new LinkedList<>();
        for (Item it : this.items)
        {
            if(it.isDamaged())
                damagedList.add(it);
            else
                newList.add(it);
        }
        setItems(newList);
        return damagedList;
    }

    public void addItem(LocalDate expDate)
    {
        items.add(new Item(expDate,getSupplierPrice(),this.productName));
    }

    public String toString()
    {
        String s = "Product name: " + this.productName + "\n "
                + "Category: " + this.category + "\n "
                + "Sub category: " + this.subCategory + "\n "
                + "Supplier name: " + this.supplierName + "\n " 
                + "Product size: " + this.size + "\n "
                + "Product price: " + this.getStoragePrice() + "\n "
                + "Discount days left: " + this.discount.getdaysLeft() + "\n "
                + "Quantity left: " + this.itemsLeft() + "\n "
                + "Sold count: " + this.soldItems.size() + "\n "; 
        return s;
    }

    //set new product price by specific discount
    public void setStorageDiscount(int storageDiscount, int days)
    {
        this.discount.setStorageDiscount(this.price*(1-((double)storageDiscount/100)), days);
        this.discount.setDiscountPre(storageDiscount);
        this.pDTO.updateDiscount(storageDiscount,LocalDate.now().toString(),days);
    }

    //set new supplier price by specific discount
    public void setSupplierDiscount(int supplierDiscount)
    {
        this.discount.setSupplierDiscount(this.supplierPrice*(1-(supplierDiscount/100)));
        this.pDTO.updateSupPrice(this.discount.getSupplierDiscountPrice());
    }

    public Discount geDiscount(){return discount;}

    //update discount by days left
    public void updateDiscount(int discount)
    {
        this.discount.updateDiscount(this.price);  
    }

    public double getStoragePrice()
    {
        return this.discount.getStorageDiscountPrice();
    }

    public double getSupplierPrice()
    {
        return this.discount.getSupplierDiscountPrice();
    }

    // Getters
    public String getProductName() {
        return productName;
    }
    public int getId(){
        return this.productId;
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

    public LinkedList<Item> getItems() {
        return items;
    }

    public double getSize() {
        return size;
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

    public void setProductPrice(double price)
    {
        this.price = price;
        this.setStorageDiscount(this.discount.getDiscountPre(), discount.getdaysLeft());
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean deleteItem(int id)
    {
        for (Item item : items) 
        {
            if(item.getId() == id)
            {
                item.getItemDTO().deleteItem();
                this.items.remove(item);
                return true;
            }
        }
        return false;
    }

    public void setDamagedItem(int id)
    {
        for (Item item : items) 
        {
            if(item.getId() == id)
                item.setDamaged();
        }
    }

    
}
