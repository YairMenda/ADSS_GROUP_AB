package dev.DataAccessLayer;

import java.util.List;

public class CategoryDTO {


    private String storageName;
    private String categoryName;
    private List<SubCategoryDTO> subCategories;
    private CategoryController categoryController;
    private SubCategoryController SubCategoryController = new SubCategoryController();

    public CategoryDTO(String storageName, String categoryName)
    {
        this.storageName = storageName;
        this.categoryName = categoryName;
        this.subCategories = this.SubCategoryController.getSubCategories(categoryName);
        this.categoryController = new CategoryController();
    }
    
}
