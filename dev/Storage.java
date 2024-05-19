package dev;
import java.util.LinkedList;
import java.util.HashMap;

public class Storage {

    private String storageName;
    private HashMap<String, Category> categories;

    public Storage(String storageName)
    {
        this.storageName = storageName;
        this.categories = new HashMap<>();
    }

    // adds new category to dictionary. return true if added, false otherwise
    public boolean addCategory(String categoryName)
    {
        return this.categories.put(categoryName, new Category(categoryName)) == null ? true : false;
    }

    // remove category from dictionary. return true if removed, false otherwise
    public boolean deleteCategory(String categoryName)
    {
        return this.categories.remove(categoryName) == null ? false : true;
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

    //trying add product by calling category::addproduct
    public boolean addProduct(String category, String productName, String subCategory, String supplierName, double size)
    {
        return this.categories.get(category).addProduct(productName, subCategory, supplierName, size);
    }

    //trying delete product by calling category::deleteProduct
    public boolean deleteProduct(String category, String subCategory, String productName)
    {
        return this.categories.get(category).deleteProduct(subCategory, productName);
    }

    public LinkedList<Product> getProductsByCategory(String categoryName){
        return categories.get(categoryName).getAllProducts();
    }

    public LinkedList<Product> getProductsBySubCategory(String categoryName, String subCategory){
        return this.categories.get(categoryName).getProductsBySubCategory(subCategory);
    }

    //get all products by specific size
    public LinkedList<Product> getProdcutsBySize(int size)
    {
        LinkedList<Product> result = new LinkedList<>();
        for (Category c : this.categories.values())
        {
            result.addAll(c.getProductsBySize(size));
        }
        return result;
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


    //rerturn report of items of expired or damaged items
    public ItemReport reportByBadItems()
    {
        LinkedList<Item> items = new LinkedList<>();
        items.addAll(getDamagedItems());
        items.addAll(getExpiredItems());
        return new ItemReport(items, "Bad items ");

    }

    // return Product by specific name
    public Product getProduct(String pName)
    {
        Product p;
        for(Category c : categories.values()){
            p = c.getProduct(pName);
            if(p != null)
            {
                return p;
            }
        }
        return null;
    }

    public void addItem(String category, String subCategory, String productName, double boughtPrice)
    {
        this.categories.get(category).addItem(subCategory, productName, boughtPrice);
    }

    public boolean deleteItem(String category, String subCategory, String productName, int id)
    {
        return this.categories.get(category).deleteItem(subCategory, productName, id);
    }

    // return list of damaged items
    public LinkedList<Item> getExpiredItems()
    {
        LinkedList<Item> expItems = new LinkedList<>();
        for(Category c : this.categories.values()){
            expItems.addAll(c.getExpiredItems());
        }
        return expItems;
    }

    // return list of damaged items
    public LinkedList<Item> getDamagedItems()
    {
        LinkedList<Item> dmgItems = new LinkedList<>();
        for(Category c : categories.values()){
            dmgItems.addAll(c.getDamagedItems());
        }
        return dmgItems;
    }

    public void sellItem(String category,String subCategory, String productName, int itemId, double price)
    {
        this.categories.get(category).sellItem(subCategory, productName, itemId, price);
    }
}
