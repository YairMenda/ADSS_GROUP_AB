package dev.BusinessLayer;
import java.util.HashMap;
import java.util.LinkedList;

public class SubCategory {

    private HashMap<Integer,Product> products;
    private HashMap<String,Integer> namesToId;
    private String subCategoryName;
    private String category;

    public SubCategory(String categoryName, String subCategoryname)
    {
        this.products = new HashMap<>();
        this.category = categoryName;
        this.subCategoryName = subCategoryname;
    }
   

    //delete product from products dictionary. return true if deleted, false otherwise
    public boolean deleteProduct(int id)
    {
        return this.products.remove(id) != null;
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

    //Fixed
    //return list of damaged items
    public LinkedList<Item> getDamagedItems(boolean drop)
    {
        LinkedList<Item> dmgItems = new LinkedList<>();
        for(Product p : products.values()){
            dmgItems.addAll(p.getDamagedItems(drop));
        }
        return dmgItems;
    }

    //Fixed
    //return list of expired items
    public LinkedList<Item> getExpiredItems(boolean drop)
    {
        LinkedList<Item> expItems = new LinkedList<>();
        for(Product p : products.values()){
            expItems.addAll(p.getExpiredItems(drop));
        }

        return expItems;
    }

    public boolean addProduct( String productName,String supplierName, double size, double price, double supplierPrice)
    {
        if(!this.namesToId.containsKey(productName))
            return false;
        Product p = new Product(productName, supplierName, productName, supplierName, size, price, supplierPrice);
        this.products.put(p.getId(),p);
        return true;
    }

    
    //get Product by specific name
    public Product getProduct(int id)
    {
        for(Product p : products.values()){
            if(p.getId() == id)
                return p;
        }
        return null;
    }

}
