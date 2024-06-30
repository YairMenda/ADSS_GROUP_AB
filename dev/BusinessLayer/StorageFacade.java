package dev.BusinessLayer;

import dev.DataAccessLayer.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class StorageFacade {

    private HashMap<String, Storage> storages;
    private StorageController storageController = new StorageController();
    private CategoryController categoryController = new CategoryController();
    private SubCategoryController subCategoryController = new SubCategoryController();
    private ProductController productController = new ProductController();
    private ItemController itemController = new ItemController();
    
    public StorageFacade()
    {
        this.storages = new HashMap<String,Storage>();
    }

    public boolean addStorage(String storageName)
    {
        return this.storages.put(storageName, new Storage(storageName)) == null;
    }
    
    public boolean deleteStorage(String storageName)
    {
        Storage storage = this.storages.get(storageName);
        if(storage == null)
            return false;
        this.storages.remove(storageName);
        return storage.getStorageDTO().deleteStorage();
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
        categoryController.insert(storageName,categoryName);
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
        subCategoryController.insert(storageName,categoryName,subCategoryName);
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

    public boolean addProduct(String storageName, String category, String subCategory, String productName,
     String supplierName, double size, double price, double supplierPrice, int minimumRequired) throws Exception
    {
        if(!storages.get(storageName).doesCatExists(category))
            throw new Exception ("Category doesn't exist");
        if(!storages.get(storageName).doesSubCatExists(category, subCategory))
            throw new Exception ("Sub Category doesn't exist");
        if(getProduct(storageName,productName) != null)
           throw new Exception ("Product name already exists");

        this.storages.get(storageName).addProduct(category, subCategory, productName, supplierName, size, price, supplierPrice, minimumRequired);
        this.productController.insert(this.getProduct(storageName,productName).getId(),storageName,category,subCategory,productName,supplierName,size,price,supplierPrice,minimumRequired);
        return true;
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

    public ProductReport reportByProductsBelowMin(String storageName) throws Exception
    {
        if(this.storages.get(storageName) == null)
            throw new Exception("Storage name doesnt exist");
        return this.storages.get(storageName).reportByProductsBelowMin();
    }
    
    //get all products by specific size
    public LinkedList<Product> getProdcutsBySize(String storageName,String category, String subCategory, double size) throws Exception
    {
        if(this.storages.get(storageName) == null)
            throw new Exception("Storage name doesnt exist");
        if(!this.storages.get(storageName).doesCatExists(category))
            throw new Exception("Category doesn't exist");
        if(!this.storages.get(storageName).doesSubCatExists(category, subCategory) && !subCategory.equals("all"))
            throw new Exception("Sub-Category doesn't exist");
        return this.storages.get(storageName).getProdcutsBySize(category,subCategory,size);
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

    public LinkedList<Product> getAllProducts(String storageName) throws Exception
    {
        if(this.storages.get(storageName) == null)
            throw new Exception("Storage name doesnt exist");
        return this.storages.get(storageName).getAllProducts();
    }

    public Product getProduct(String storageName, String productName) 
    {
        return this.storages.get(storageName).getProduct(productName);
    }

    public Product getProduct(String storageName, int productId) 
    {
        return this.storages.get(storageName).getProduct(productId);
    }

    public LinkedList<Item> getAllItems(String storageName, int productId) throws Exception
    {
        Product p = this.getProduct(storageName, productId);
        if(p == null)
            throw new Exception("Product doesnt exist");
        return p.getItems();
    }

    public void setDamagedItem(String storageName ,int productId ,int itemId) throws Exception
    {
        Product p = getProduct(storageName, productId);
        if(p == null)
            throw new Exception("Product doesnt exist");
        p.setDamagedItem(itemId);
    }

    public LinkedList<String> getAllSubCategories(String storageName, String categoryName) throws Exception
    {
        if(!this.storages.containsKey(storageName))
            throw new Exception("storage name doesnt exist");
        LinkedList<String> result = this.storages.get(storageName).getAllSubCategories(categoryName);
        if(result.size() == 0)
            throw new Exception("category name doesnt exist");
        return result;     
    }

    public LinkedList<String> getAllCategories(String storageName) throws Exception
    {
        if(!this.storages.containsKey(storageName))
            throw new Exception("storage name doesnt exist");
        return this.storages.get(storageName).getAllCategories();     
    }

    public boolean updateProductPrice(String storageName, int productId, double productPrice) throws Exception
    {
        if(!this.storages.containsKey(storageName))
            throw new Exception("storage name doesnt exist");
        Product p = this.getProduct(storageName, productId);
        if(p == null)
            throw new Exception("product name doesnt exist");
        p.setProductPrice(productPrice);
        return true;
    }

    public void loadData()
    {
        List<StorageDTO> storagesDTO = this.storageController.getAllStorages();
        for(StorageDTO sDTO : storagesDTO)
        {
            this.storages.put(sDTO.getStorageName(),new Storage(sDTO));
        }
    }
    

}
