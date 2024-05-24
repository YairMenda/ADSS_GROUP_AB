package dev.BusinessLayer;

import java.time.LocalDate;
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

    
    //Menash - to remove - DONE
    public boolean addCategory(String storageName, String categoryName) throws Exception
    {
        if(storages.get(storageName).doesCatExists(categoryName))
            throw new Exception ("Category already exist");
        return this.storages.get(storageName).addCategory(categoryName);
    }
    
    //Menash - to remove - DONE
    public boolean deleteCategory(String storageName, String categoryName) throws Exception
    {
        
        if(!storages.get(storageName).doesCatExists(categoryName))
            throw new Exception ("Category doesn't exist");
        return this.storages.get(storageName).deleteCategory(categoryName);
    }

    
    //Menash - to remove - DONE
    public boolean addSubCategory(String storageName, String categoryName ,String subCategoryName) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(categoryName))
            throw new Exception ("Category doesnt exist");
        if(storages.get(storageName).doesSubCatExists(categoryName, subCategoryName))
            throw new Exception ("Sub Category already exist");
        return this.storages.get(storageName).addSubCategory(categoryName, subCategoryName);
    }

    
    public void setStoreDiscount(String storageName, int productId, int storageDiscount, int days) throws Exception{
        Product p = getProduct(storageName,productId);
        if(p == null)
            throw new Exception("Product doesnt exist");
        p.setStorageDiscount(storageDiscount,days);
    }

    
     public void setSupplierDiscount(String storageName, int productId, int discount, int days)throws Exception{
        Product p = getProduct(storageName,productId);
        if(p == null)
            throw new Exception("Product doesnt exist");
        p.setSupplierDiscount(discount);
    }

    public boolean deleteSubCategory(String storageName, String categoryName ,String subCategoryName) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(categoryName))
            throw new Exception ("Category doesn't exist");
        if(!storages.get(storageName).doesSubCatExists(categoryName, subCategoryName))
            throw new Exception ("Sub Category doesn't exist");
        return this.storages.get(storageName).deleteSubCategory(categoryName, subCategoryName);
    }

    public boolean deleteProduct(String storageName, int productId) throws Exception
    {
        Product p = getProduct(storageName, productId);
        if(p == null)
            throw new Exception("Product doesnt exist");
        return this.storages.get(storageName).deleteProduct(p.getCategory(), p.getSubCategory(), productId);
    }

    public void addItem(String storageName, int productId, int quantity , LocalDate expDate) throws Exception
    {
        Product p = getProduct(storageName, productId);
        if(p == null)
            throw new Exception("Product doesnt exist");
        if(quantity <= 0)
            throw new Exception("Cant add 0 or less items");
        while(quantity != 0)
        {
            p.addItem(expDate);
            quantity--;
        }
    }

    public void addProduct(String storageName, String category, String subCategory, int productId, String productName,
     String supplierName, double size, double price, double supplierPrice) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(category))
            throw new Exception ("Category doesn't exist");
        if(!storages.get(storageName).doesSubCatExists(category, subCategory))
            throw new Exception ("Sub Category doesn't exist");
        if(getProduct(storageName, productId) != null)
            throw new Exception("Product already exist");
        this.storages.get(storageName).addProduct(category, subCategory, productName, productId, supplierName, size, price, supplierPrice);
    }


    public void deleteItem(String storageName, String category, String subCategory, int productId, int itemId) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(category))
            throw new Exception ("Category doesn't exist");
        if(!storages.get(storageName).doesSubCatExists(category, subCategory))
            throw new Exception ("Sub Category doesn't exist");
         //need to add prod doesnt exists
        this.storages.get(storageName).deleteItem(productId, itemId);
    }

    // get a list of expired date items
    public LinkedList<Item> getExpiredItems(String storageName)
    {
        
        return this.storages.get(storageName).getExpiredItems(false);
    }

    //get a list of damaged items
    public LinkedList<Item> getDamagedItems(String storageName)
    {
        
        return this.storages.get(storageName).getDamagedItems(false);
    }

    public boolean sellItem(String storageName,  int itemId, int productId) throws Exception
    {
        Product p = getProduct(storageName, productId);
        if(p == null)
            throw new Exception("Product doesnt exist");
         //need to add prod doesnt exists
        this.storages.get(storageName).sellItem( productId, itemId);
        return true;
    }

    //return report of items of expired or damaged items
    public ItemReport reportByBadItems(String storageName)
    {
        return this.storages.get(storageName).reportByBadItems();
    }

    // return report of products by specific subCategory
    public ProductReport reportBySubCategory(String storageName, String categoryName, String subCategory) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(categoryName))
            throw new Exception ("Category doesn't exist");
        if(!storages.get(storageName).doesSubCatExists(categoryName, subCategory))
            throw new Exception ("Sub Category doesn't exist");
        return this.storages.get(storageName).reportBySubCategory(categoryName,subCategory);
    }

    //return report of products by specific category
    public ProductReport reportByCategory(String storageName, String categoryName) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(categoryName))
            throw new Exception ("Category doesn't exist");
        return this.storages.get(storageName).reportByCategory(categoryName);
    }
    
    //get all products by specific size
    public LinkedList<Product> getProdcutsBySize(String storageName, int size)
    {
        return this.storages.get(storageName).getProdcutsBySize(size);
    }

    //get all products by specific category
    public LinkedList<Product> getProdcutsByCategory(String storageName, String category) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(category))
            throw new Exception ("Category doesn't exist");
        return this.storages.get(storageName).getProductsByCategory(category);
    }

    //get all products by specific sub category
    public LinkedList<Product> getProdcutsBySubCategory(String storageName, String category, String subCategory) throws Exception
    {
         if(!storages.get(storageName).doesCatExists(category))
            throw new Exception ("Category doesn't exist");
        if(!storages.get(storageName).doesSubCatExists(category, subCategory))
            throw new Exception ("Sub Category doesn't exist");
        return this.storages.get(storageName).getProductsBySubCategory(category, subCategory);
    }

    public Product getProduct(String storageName, int productId) 
    {
        return this.storages.get(storageName).getProduct(productId);
    }

}
