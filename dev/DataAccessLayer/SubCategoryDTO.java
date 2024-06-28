package dev.DataAccessLayer;

import java.util.List;

import dev.BusinessLayer.Product;

public class SubCategoryDTO {

    private String storageName;
    private String category;
    private String subCategory;
    private List<ProductDTO> products;
    private SubCategoryController subCategoryController;
    private ProductController productController = new ProductController();


    public SubCategoryDTO(String storageName, String category, String subCategory)
    {
        this.storageName = storageName;
        this.category = category;
        this.subCategory = subCategory;
        this.subCategoryController = new SubCategoryController();
        this.products = productController.getAllProducts(storageName, category, subCategory);
    }

    public String getStorageName()
    {
        return this.storageName;
    }

    public String getCategoryName()
    {
        return this.category;
    }

    public String getSubCategoryName()
    {
        return this.subCategory;
    }

    public List<ProductDTO> getProducts()
    {
        return this.products;
    }

    public SubCategoryController getSubCategoryController()
    {
        return this.subCategoryController;
    }
    
}
