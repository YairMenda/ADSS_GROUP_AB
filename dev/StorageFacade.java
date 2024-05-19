package dev;

import java.util.HashMap;
import java.util.LinkedList;

public class StorageFacade {

    private HashMap<String, Storage> storages;
    
    public StorageFacade()
    {
        this.storages = new HashMap<>();
    }

    public boolean addStorage(String storageName)
    {
        return this.storages.put(storageName, new Storage(storageName)) == null;
    }

    public boolean deleteStorage(String storageName)
    {
        return this.storages.remove(storageName) == null;
    }

    public boolean isExist(String storageName)
    {
        return this.storages.containsKey(storageName);
    }

    public boolean addCategory(String storageName, String CategoryName)
    {
        if(!isExist(storageName))
            return false;
        return this.storages.get(storageName).addCategory(CategoryName);
    }

    public boolean deleteCategory(String storageName, String CategoryName)
    {
        if(!isExist(storageName))
            return false;
        return this.storages.get(storageName).deleteCategory(CategoryName);
    }

    public boolean addSubCategory(String storageName, String categoryName ,String subCategoryName)
    {
        if(!isExist(storageName))
            return false;
        return this.storages.get(storageName).addSubCategory(categoryName, subCategoryName);
    }

    public boolean deleteSubCategory(String storageName, String categoryName ,String subCategoryName)
    {
        if(!isExist(storageName))
            return false;
        return this.storages.get(storageName).deleteSubCategory(categoryName, subCategoryName);
    }

    public boolean addProduct(String storageName, String categoryName, String subCategoryName, String productName, String supplierName, double size)
    {
        if(!isExist(storageName))
            return false;
        return this.storages.get(storageName).addProduct(categoryName, subCategoryName, productName, supplierName, size);
    }

    public boolean deleteProduct(String storageName, String categoryName, String subCategoryName, String productName)
    {
        if(!isExist(storageName))
            return false;
        return this.storages.get(storageName).deleteProduct(categoryName, subCategoryName, productName);
    }

    public boolean addItem(int quantity, String storageName, String category, String subCategory, String productName, double boughtPrice)
    {
        if(!isExist(storageName) || quantity <= 0)
            return false;
        while(quantity != 0)
        {
            this.storages.get(storageName).addItem(category, subCategory, productName, boughtPrice);
            quantity--;
        }
        return true;
    }

    public boolean deleteItem(String storageName, String category, String subCategory, String productName, int id)
    {
        if(!isExist(storageName))
            return false;
        return this.storages.get(storageName).deleteItem(category, subCategory, productName, id);
    }

    // get a list of expired date items
    public LinkedList<Item> getExpiredItems(String storageName)
    {
        //exception
        return this.storages.get(storageName).getExpiredItems();
    }

    //get a list of damaged items
    public LinkedList<Item> getDamagedItems(String storageName)
    {
        //exception
        return this.storages.get(storageName).getDamagedItems();
    }

    public boolean sellItem(String storageName, String category,String subCategory, String productName, int itemId, double price)
    {
        if(!isExist(storageName))
            return false;
        this.storages.get(storageName).sellItem(category, subCategory, productName, itemId, price);
        return true;
    }

    //return report of items of expired or damaged items
    public ItemReport reportByBadItems(String storageName)
    {
        if(!isExist(storageName))
            return null;
        return this.storages.get(storageName).reportByBadItems();
    }

    // return report of products by specific subCategory
    public ProductReport reportBySubCategory(String storageName, String categoryName, String subCategory)
    {
        if(!isExist(storageName))
            return null;
        return this.storages.get(storageName).reportBySubCategory(categoryName,subCategory);
    }

    //return report of products by specific category
    public ProductReport reportByCategory(String storageName, String categoryName)
    {
        if(!isExist(storageName))
            return null;
        return this.storages.get(storageName).reportByCategory(categoryName);
    }
    
    //get all products by specific size
    public LinkedList<Product> getProdcutsBySize(String storageName, int size)
    {
        if(!isExist(storageName))
            return null;
        return this.storages.get(storageName).getProdcutsBySize(size);
    }

    //get all products by specific category
    public LinkedList<Product> getProdcutsByCategory(String storageName, String category)
    {
        if(!isExist(storageName))
            return null;
        return this.storages.get(storageName).getProductsByCategory(category);
    }

    //get all products by specific sub category
    public LinkedList<Product> getProdcutsByCategory(String storageName, String category, String subCategory)
    {
        if(!isExist(storageName))
            return null;
        return this.storages.get(storageName).getProductsBySubCategory(category, subCategory);
    }

    public Product getProduct(String storageName, String productName)
    {
        if(!isExist(storageName))
            return null;
        return this.storages.get(storageName).getProduct(productName);
    }

}
