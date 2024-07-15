package BussinessLayer;
import DataAccessLayer.CategoryDTO;
import DataAccessLayer.StorageDTO;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private String storageName;
    private HashMap<String, Category> categories;
    private StorageDTO storageDTO;

    public Storage(String storageName)
    {
        this.storageName = storageName;
        this.categories = new HashMap<>();
        this.storageDTO = new StorageDTO(storageName);
    }


    public Storage(StorageDTO sDTO)
    {
        this.storageName = sDTO.getStorageName();
        this.categories = new HashMap<>();
        for(CategoryDTO cDTO: sDTO.getCategories())
        {
            this.categories.put(cDTO.getCategoryName(),new Category(cDTO));
        }
        this.storageDTO = sDTO;
    }

    public StorageDTO getStorageDTO()
    {
        return this.storageDTO;
    }

    public String getStorageName()
    {
        return this.storageName;
    }

    // adds new category to dictionary. return true if added, false otherwise
    public boolean addCategory(String categoryName)
    {
        return this.categories.put(categoryName, new Category(this.storageName,categoryName)) == null ? true : false;
    }

    // remove category from dictionary. return true if removed, false otherwise
    public boolean deleteCategory(String categoryName)
    {
        Category category = this.categories.get(categoryName);
        if(category == null)
            return false;
        this.categories.remove(categoryName);
        return category.getCategoryDTO().deleteCategory();
    }

    // trying add subcategory by calling category::addCategory function
    public boolean addSubCategory(String category, String SubCategory)
    {
        return this.categories.get(category).addSubCategory(SubCategory);
    }

    // trying delete subcategory by calling category::deleteCategory function
    public boolean deleteSubCategory(String category, String SubCategory)
    {
        return this.categories.get(category).deleteSubCategory(SubCategory);
    }

    //trying delete product by calling category::deleteProduct
    public boolean deleteProduct(String category, String subCategory, int productId)
    {
        return this.categories.get(category).deleteProduct(subCategory, productId);
    }

    public LinkedList<Product> getProductsByCategory(String categoryName){
        return categories.get(categoryName).getAllProducts();
    }

    public LinkedList<Product> getProductsBySubCategory(String categoryName, String subCategory){
        return this.categories.get(categoryName).getProductsBySubCategory(subCategory);
    }

    //get all products by specific size
    public LinkedList<Product> getProdcutsBySize(String category, String subCategory, double size)
    {
        return this.categories.get(category).getProductsBySize(subCategory,size);
    }
    //return report of products by specific category
    public ProductReport reportByCategory(String categoryName)
    {
        return new ProductReport(getProductsByCategory(categoryName), categoryName);
    }

    //return report of products by specific sub category
    public ProductReport reportBySubCategory(String categoryName, String subCategory)
    {
        return new ProductReport(getProductsBySubCategory(categoryName, subCategory), subCategory);
    }

    public ProductReport reportByProductsBelowMin()
    {
        return new ProductReport(getProductsBelowMin(), "Products below minimum required: ");
    }


    //rerturn report of items of expired or damaged items
    public ItemReport reportByBadItems()
    {
        LinkedList<Item> items = new LinkedList<>();
        items.addAll(getDamagedItems(true));
        items.addAll(getExpiredItems(true));
        return new ItemReport(items, "Bad items ");

    }

    // return Product by specific name
    public Product getProduct(String productName)
    {
        Product p;
        for(Category c : categories.values()){
            p = c.getProduct(productName);
            if(p != null)
            {
                return p;
            }
        }
        return null;
    }

    public Product getProduct(int productId)
    {
        Product p;
        for(Category c : categories.values()){
            p = c.getProduct(productId);
            if(p != null)
            {
                return p;
            }
        }
        return null;
    }
    public boolean addProduct(String category, String subCategory,String productName, String supplierName,
     double size, double price ,double supplierPrice, int minimumRequired)
    {
        return this.categories.get(category).addProduct(subCategory, productName, supplierName, size, price, supplierPrice, minimumRequired);
    }


    public boolean deleteItem(int productId, int id)
    {
        return getProduct(productId).deleteItem(id);
    }

    // return list of damaged items
    public LinkedList<Item> getExpiredItems(boolean drop)
    {
        LinkedList<Item> expItems = new LinkedList<>();
        for(Category c : this.categories.values()){
            expItems.addAll(c.getExpiredItems(drop));
        }
        return expItems;
    }

    // return list of damaged items
    public LinkedList<Item> getDamagedItems(boolean drop)
    {
        LinkedList<Item> dmgItems = new LinkedList<>();
        for(Category c : categories.values()){
            dmgItems.addAll(c.getDamagedItems(drop));
        }
        return dmgItems;
    }

    public LinkedList<Product> getProductsBelowMin()
    {
        LinkedList<Product> result = new LinkedList<>();
        for(Category c : this.categories.values())
        {
            result.addAll(c.getProductsBelowMin());
        }
        return result;
    }


    public boolean doesCatExists(String catName){
        return this.categories.containsKey(catName);
    }

    public boolean doesSubCatExists(String catName, String sCatName){
        if(!this.categories.containsKey(catName))
            return false;
        return this.categories.get(catName).doesSubCatExists(sCatName);
    }

    public void sellItem(int productId, int itemId)
    {
        getProduct(productId).sellItem(itemId);
    }
    public boolean sellItem(int productId)
    {
        return getProduct(productId).sellItem();
    }


    public LinkedList<Product> getAllProducts()
    {
        LinkedList<Product> result = new LinkedList<>();
        for (Category category : this.categories.values()) 
        {
            result.addAll(category.getAllProducts());
        }
        return result;
    }

    public LinkedList<String> getAllSubCategories(String category)
    {
        LinkedList<String> result = new LinkedList<>();
        for (Category cat : this.categories.values()) 
        {
            if(cat.getCategoryName().equals(category))
                result.addAll(cat.getAllSubCategories());
        }
        return result;
    }

    public LinkedList<String> getAllCategories()
    {
        LinkedList<String> result = new LinkedList<>();
        for (Category cat : this.categories.values()) 
        {
            result.add(cat.getCategoryName());
        }
        return result;
    }
    
}
