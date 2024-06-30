package dev.DataAccessLayer;

import java.util.List;

public class StorageDTO {

    private String storageName;
    private List<CategoryDTO> categories;
    private StorageController storageController;
    private CategoryController categoryController = new CategoryController();

    public StorageDTO(String storageName) {
        this.storageName = storageName;
        this.categories = categoryController.getStorageCategories(storageName);
        this.storageController = new StorageController();
    }

    public String getStorageName()
    {
        return this.storageName;
    }

    public List<CategoryDTO> getCategories()
    {
        return this.categories;
    }

    public StorageController getStorageController()
    {
        return this.storageController;
    }

    public boolean deleteStorage()
    {
        return this.storageController.deleteStorage(this);
    }
    
}
