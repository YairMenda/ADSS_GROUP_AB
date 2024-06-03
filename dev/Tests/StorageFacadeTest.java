package dev.Tests;

import dev.BusinessLayer.StorageFacade;
import dev.ServiceLayer.StorageInit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StorageFacadeTest {

    private StorageFacade storageFacade;
    @BeforeAll
    public void init()
    {
        StorageInit storageInit = new StorageInit();
        storageInit.init();
        storageFacade = storageInit.getStorageService().getStorageFacade();
    }
    @Test
    void addCategory()
    {
        try
        {
            storageFacade.addCategory("A","Test-Category");
            Assertions.assertEquals(storageFacade.getAllCategories("A").contains("Test-Category"),true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void deleteSubCategory()
    {
        try
        {
            storageFacade.deleteSubCategory("A", "Snacks","Salties");
            Assertions.assertEquals(storageFacade.getAllSubCategories("A","Snacks").contains("Salties"),false);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void addProduct()
    {
        try
        {
            storageFacade.addProduct("A", "Pharmacy", "Pills", "Akamol", "Supplier L", 0.2, 7.0, 3.0);
            Assertions.assertEquals(storageFacade.getProduct("A","Akamol") != null ,true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void getAllProducts()
    {
        try
        {
            Assertions.assertEquals(storageFacade.getAllProducts("A").size(),21);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void setDamagedItem()
    {
        try
        {
            storageFacade.setDamagedItem("A",1,1);
            Assertions.assertEquals(storageFacade.getDamagedItems("A").size(),1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void updateProductPrice()
    {
        try
        {
            storageFacade.updateProductPrice("A",1,100);
            Assertions.assertEquals(storageFacade.getProduct("A",1).getStoragePrice(),100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}