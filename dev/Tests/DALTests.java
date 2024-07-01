package dev.Tests;

import dev.BusinessLayer.StorageFacade;
import dev.DataAccessLayer.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DALTests {
    private StorageController storageController;
    private ProductController productController;
    private CategoryController categoryController;
    private SubCategoryController subCategoryController;
    private ItemController itemController;
    @BeforeAll
    public void init()
    {
        storageController = new StorageController();
        productController = new ProductController();
        categoryController = new CategoryController();
        subCategoryController = new SubCategoryController();
        itemController = new ItemController();

    }

    @Test
    void insertStorage()
    {
        try
        {
            int size = storageController.getAllStorages().size();
            storageController.insert("C");
            Assertions.assertEquals(storageController.getAllStorages().size(),size + 1);
            storageController.deleteStorage(new StorageDTO("C"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void deleteStorage()
    {
        try
        {
            storageController.insert("C");
            int size = storageController.getAllStorages().size();
            storageController.deleteStorage(new StorageDTO("C"));
            Assertions.assertEquals(storageController.getAllStorages().size(),size - 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void updateDiscount()
    {
        try
        {
            ProductDTO productDTO = productController.getAllProducts("A","Drinks","Alcohol").get(0);
            double beforePrice = productDTO.getPriceToProductDTO().getStoragePrice();
            productController.updateDiscount(30, LocalDate.now().toString(),10,productDTO.getProductId());
            Assertions.assertEquals(beforePrice * 0.7, productDTO.getPriceToProductDTO().getStoragePrice() * ((100 - (double)productDTO.getPriceToProductDTO().getDiscount())/100));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Test
    void insertCategory()
    {
        try
        {
            int size = categoryController.getStorageCategories("A").size();
            categoryController.insert("A","AA");
            Assertions.assertEquals(categoryController.getStorageCategories("A").size(),size + 1);
            categoryController.deleteCategory(new CategoryDTO("A","AA"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void deleteCategory()
    {
        try
        {
            categoryController.insert("A","AB");
            int size = categoryController.getStorageCategories("A").size();
            categoryController.deleteCategory(new CategoryDTO("A","AB"));
            Assertions.assertEquals(categoryController.getStorageCategories("A").size(),size - 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void insertSCategory()
    {
        try
        {
            int size = subCategoryController.getSubCategories("A","AA").size();
            subCategoryController.insert("A","AA","AAA");
            Assertions.assertEquals(subCategoryController.getSubCategories("A","AA").size(),size + 1);
            subCategoryController.deleteSubCategory(new SubCategoryDTO("A","AA","AAA"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void deleteSCategory()
    {
        try
        {
            subCategoryController.insert("A","AB","ABC");
            int size = subCategoryController.getSubCategories("A","AB").size();
            subCategoryController.deleteSubCategory(new SubCategoryDTO("A","AB","ABC"));
            Assertions.assertEquals(subCategoryController.getSubCategories("A","AB").size(),size - 1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void sellItem(){
        try{

            ItemDTO it = null;
            for(ItemDTO i : itemController.getItems(1)){
                if(i.getSoldPrice() == null || i.getSoldPrice() == 0){
                    it = i;
                    break;
                }
            }
            itemController.updateSale(it.getItemId(), 5,"2020-20-20");
            Assertions.assertEquals(it.getSoldPrice() != null,true);
            itemController.updateSale(it.getItemId(), 0.0,null);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void updateSupplierPrice(){
        try{

            ProductDTO p = productController.getAllProducts("A","Drinks","Alcohol").get(0);
            double lastPrice = p.getPriceToProductDTO().getSupplierPrice();
            p.updateSupPrice(50.0);
            Assertions.assertEquals(p.getPriceToProductDTO().getSupplierPrice(),50.0);
            p.updateSupPrice(lastPrice);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
