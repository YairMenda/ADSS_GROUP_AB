package dev;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SubCategory {

    private HashMap<String,Product> products;
    private String subCategoryName;
    private String category;

    public SubCategory(String categoryName, String subCategoryname)
    {
        this.products = new HashMap<>();
        this.category = categoryName;
        this.subCategoryName = subCategoryname;
    }
   
    //adding product to products dictionary. return false if already exist, true otherwise
    public boolean addProduct(String productName, String supplierName, double size)
    {
        return this.products.put(productName, new Product(productName, this.category, this.subCategoryName, supplierName,size)) == null;
    }

    //delete product from products dictionary. return true if deleted, false otherwise
    public boolean deleteProduct(String productName)
    {
        return this.products.remove(productName) != null;
    }

    //adding new item for the product
    public void addItem(String productName, LocalDate expDate, double boughtPrice)
    {   
        this.products.get(productName).addItem(expDate,boughtPrice);
    }
     
    //return products list of current subcategory
    public LinkedList<Product> getProducts()
    {
        return new LinkedList<>(products.values());
    }

    //get all products by specific size
    public LinkedList<Product> getProductsBySize(int size)
    {
        LinkedList<Product> result = new LinkedList<>();
        for (Product p : this.products.values()) 
        {
            if(p.getSize() == size)
            {
                result.add(p);
            }
        }
        return result;
    }
    //return list of damaged items
    public LinkedList<Item> getDamagedItems()
    {
        LinkedList<Item> dmgItems = new LinkedList<>();
        for(Product p : products.values()){
            dmgItems.addAll(p.getDamagedProducts());
        }
        return dmgItems;
    }

    //return list of expired items
    public LinkedList<Item> getExpiredItems()
    {
        LinkedList<Item> expItems = new LinkedList<>();
        for(Product p : products.values()){
            expItems.addAll(p.getExpiredItems());
        }

        return expItems;
    }

    public void addItem(String productName, double boughtPrice)
    {
        this.products.get(productName).addItem(LocalDate.now(), boughtPrice);
    }

    public boolean deleteItem(String productName, int id)
    {
        return this.products.get(productName).deleteItem(id);
    }

    //handles the sale of an item
    public void sellItem(String productName, int itemId, double price)
    {
        this.products.get(productName).sellItem(itemId, price);
    }
    
    //get Product by specific name
    public Product getProduct(String pName)
    {
        for(Product p : products.values()){
            if(p.getProductName() == pName)
                return p;
        }
        return null;
    }

}
