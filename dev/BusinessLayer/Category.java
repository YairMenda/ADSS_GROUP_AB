package dev.BusinessLayer;

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


    //trying deleting product by calling subcategory::deleteProdcut
    public boolean deleteProduct(String subCategory, int productId)
    {
        return this.subCategories.get(subCategory).deleteProduct(productId);
    }

    //get list of products from specific sub category
    public LinkedList<Product> getProductsBySubCategory(String subCategory)
    {
        return this.subCategories.get(subCategory).getAllProducts();
    }

    //get all products by specific size
    public LinkedList<Product> getProductsBySize(String subCategory, double size)
    {
        LinkedList<Product> result = new LinkedList<>();
        if(subCategory.equals(""))
        {
            for (SubCategory sc : this.subCategories.values()) 
                result.addAll(sc.getProductsBySize(size));   
        }
        else
            return this.subCategories.get(subCategory).getProductsBySize(size);
        return result;
    }

    public boolean doesSubCatExists(String sCatName){
        return this.subCategories.containsKey(sCatName);
    }

    public boolean addProduct(String subCategory, String productName, String supplierName, double size, double price ,
    double supplierPrice, int minimumRequired)
    {
        return this.subCategories.get(subCategory).addProduct(productName, supplierName, size, price, supplierPrice, minimumRequired);
    }


    //get list of products from all sub categories
    public LinkedList<Product> getAllProducts()
    {
        LinkedList<Product> products = new LinkedList<>();
        for(SubCategory sc : subCategories.values())
        {
            products.addAll(sc.getAllProducts());
        }

        return products;
    }

    // return list of damaged items
    public LinkedList<Item> getDamagedItems(boolean drop)
    {
        LinkedList<Item> dmgItems = new LinkedList<>();
        for(SubCategory sc : subCategories.values()){
            dmgItems.addAll(sc.getDamagedItems(drop));
        }
        return dmgItems;
    }

    //return list of expired items
    public LinkedList<Item> getExpiredItems(boolean drop)
    {
        LinkedList<Item> expItems = new LinkedList<>();
        for(SubCategory sc : subCategories.values()){
            expItems.addAll(sc.getExpiredItems(drop));
        }
        return expItems;
    }

    public LinkedList<Product> getProductsBelowMin()
    {
        LinkedList<Product> result = new LinkedList<>();
        for(SubCategory sc : this.subCategories.values())
        {
            result.addAll(sc.getProductsBelowMin());
        }
        return result;
    }

    public Product getProduct(String productName)
    {
        Product p;
        for(SubCategory sc : subCategories.values()){
            p = sc.getProduct(productName);
            if(p != null){
                return p;
            }
        }
        return null;
    }

    public Product getProduct(int productId)
    {
        Product p;
        for(SubCategory sc : subCategories.values()){
            p = sc.getProduct(productId);
            if(p != null){
                return p;
            }
        }
        return null;
    }

    public LinkedList<String> getAllSubCategories()
    {
        LinkedList<String> result = new LinkedList<>();
        for (SubCategory sc : this.subCategories.values()) 
        {
            result.add(sc.getSubCategory());
        }
        return result;
    }

    public String getCategoryName()
    {
        return this.categoryName;
    }
    
}
