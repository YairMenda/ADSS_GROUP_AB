package DataAccessLayer;

import java.util.List;

public class CategoryDTO {


    private String storageName;
    private String categoryName;
    private List<SubCategoryDTO> subCategories;
    private CategoryController categoryController;
    private SubCategoryController subCategoryController = new SubCategoryController();

    public CategoryDTO(String storageName, String categoryName)
    {
        this.storageName = storageName;
        this.categoryName = categoryName;
        this.subCategories = this.subCategoryController.getSubCategories(storageName,categoryName);
        this.categoryController = new CategoryController();
    }

    public String getStorageName()
    {
        return this.storageName;
    }

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public List<SubCategoryDTO> getSubCategories()
    {
        return this.subCategories;
    }
    
    public CategoryController getCategoryController()
    {
        return this.categoryController;
    }

    public boolean deleteCategory()
    {
        return this.categoryController.deleteCategory(this);
    }
}
