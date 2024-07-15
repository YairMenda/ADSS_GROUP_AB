package BussinessLayer;
import DataAccessLayer.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SubCategory {

    private HashMap<Integer,Product> products;
    private HashMap<String,Integer> namesToId;
    private String subCategoryName;
    private String category;
    private String storageName;
    private SubCategoryDTO subCategoryDTO;

    public SubCategory(String storageName, String categoryName, String subCategoryname)
    {
        this.storageName = storageName;
        this.products = new HashMap<Integer,Product>();
        this.namesToId = new HashMap<String,Integer>();
        this.category = categoryName;
        this.subCategoryName = subCategoryname;
        this.subCategoryDTO = new SubCategoryDTO(storageName, category, subCategoryName);
    }

    public SubCategory(SubCategoryDTO scDTO)
    {
        this.storageName = scDTO.getStorageName();
        this.subCategoryName = scDTO.getSubCategoryName();
        this.category = scDTO.getCategoryName();
        this.products = new HashMap<Integer,Product>();
        this.namesToId = new HashMap<String,Integer>();
        for(ProductDTO pDTO : scDTO.getProducts())
        {
            this.namesToId.put(pDTO.getProductName(),pDTO.getProductId());
            this.products.put(pDTO.getProductId(),new Product(pDTO));
        }
        this.subCategoryDTO = scDTO;
    }

    public SubCategoryDTO getSubCategoryDTO()
    {
        return this.subCategoryDTO;
    }
   

    public String getSubCategory()
    {
        return this.subCategoryName;
    }

    public String getCategory()
    {
        return this.category;
    }
    //delete product from products dictionary. return true if deleted, false otherwise
    public boolean deleteProduct(int id)
    {
        Product p = this.products.get(id);
        if(p == null)
            return false;
        this.products.remove(id);
        this.namesToId.remove(p.getProductName());
        return p.getProductDTO().deleteProduct();
    }
    
    //return products list of current subcategory
    public LinkedList<Product> getAllProducts()
    {
        return new LinkedList<>(products.values());
    }

    //get all products by specific size
    public LinkedList<Product> getProductsBySize(double size)
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

    public LinkedList<Product> getProductsBelowMin()
    {
        LinkedList<Product> result = new LinkedList<>();
        for(Product p : this.products.values())
        {
            if(p.belowMin())
                result.add(p);
        }
        return result;
    }

    public boolean addProduct(String productName,String supplierName, double size, double price, double supplierPrice, int minimumRequired)
    {
        if(this.namesToId.containsKey(productName))
            return false;
        Product p = new Product(this.storageName,productName, category, subCategoryName, supplierName, size, price, supplierPrice, minimumRequired);
        this.products.put(p.getId(),p);
        this.namesToId.put(p.getProductName(), p.getId());
        return true;
    }

    
    //get Product by specific name
    public Product getProduct(String productName)
    {
        return this.products.get(this.namesToId.get(productName));
    }

    public Product getProduct(int productId)
    {
        return this.products.get(productId);
    }



}
