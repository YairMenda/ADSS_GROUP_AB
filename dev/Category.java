package dev;

import java.util.HashMap;
import java.util.LinkedList;

public class Category {

    private HashMap<String,SubCategory> subCategories;
    private String categoryName;

    public Category(String name)
    {
        this.subCategories = new HashMap<>();
        this.categoryName = name;
    }
    
    

    //add new subcategory to dictionary. true if added, false otherwise
    public boolean addSubCategory(String subCategoryName)
    {
        return this.subCategories.put(subCategoryName, new SubCategory(this.categoryName, subCategoryName)) == null;
    }

    //delete subcategory from dictionary. true if removed, false otherwise
    public boolean deleteSubCategory(String subCategory)
    {
        return this.subCategories.remove(subCategory) != null;
    }

    //trying adding product by calling subcategory::addProduct
    public boolean addProduct(String productName, String subCategory, String supplierName, double size)
    {
        return this.subCategories.get(subCategory).addProduct(productName, supplierName, size);
    }

    //trying deleting product by calling subcategory::deleteProdcut
    public boolean deleteProduct(String subCategory, String productName)
    {
        return this.subCategories.get(subCategory).deleteProduct(productName);
    }

    //get list of products from specific sub category
    public LinkedList<Product> getProductsBySubCategory(String subCategory)
    {
        return this.subCategories.get(subCategory).getProducts();
    }

    //get list of products from all sub categories
    public LinkedList<Product> getAllProducts()
    {
        LinkedList<Product> products = new LinkedList<>();
        for(SubCategory sc : subCategories.values())
        {
            products.addAll(sc.getProducts());
        }

        return products;
    }

    // return list of damaged items
    public LinkedList<Item> getDamagedItems()
    {
        LinkedList<Item> dmgItems = new LinkedList<>();
        for(SubCategory sc : subCategories.values()){
            dmgItems.addAll(sc.getDamagedItems());
        }
        return dmgItems;
    }

    //return list of expired items
    public LinkedList<Item> getExpiredItems()
    {
        LinkedList<Item> expItems = new LinkedList<>();
        for(SubCategory sc : subCategories.values()){
            expItems.addAll(sc.getExpiredItems());
        }
        return expItems;
    }

    public Product getProduct(String pName)
    {
        Product p;
        for(SubCategory sc : subCategories.values()){
            p = sc.getProduct(pName);
            if(p != null){
                return p;
            }
        }
        return null;
    }
    
}
