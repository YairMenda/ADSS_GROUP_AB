package Tests;

//import dev.BusinessLayer.StorageFacade;
//import dev.ServiceLayer.StorageInit;
import BussinessLayer.StorageFacade;
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
        storageFacade = new StorageFacade();
        storageFacade.loadData();
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
    void addItems()
    {
        try
        {
            int size = storageFacade.getProduct("A",0).getItems().size();
            storageFacade.addItem("A", 0,5,LocalDate.now().minusDays(1));
            Assertions.assertEquals(storageFacade.getProduct("A",0).getItems().size(), size + 5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    void removeAndReportBadItems()
    {
        try
        {
            storageFacade.reportByBadItems("A");
            int size = storageFacade.getProduct("A",1).getItems().size();
            storageFacade.addItem("A", 1,5,LocalDate.now().minusDays(1));
            storageFacade.reportByBadItems("A");
            Assertions.assertEquals(storageFacade.getProduct("A",1).getItems().size(), size);
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
            storageFacade.addProduct("A", "Pharmacy", "Pills", "Akamol", "Supplier L", 0.2, 7.0, 3.0,10);
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
            int size = storageFacade.getAllProducts("A").size();
            storageFacade.deleteProduct("A",6);
            Assertions.assertEquals(storageFacade.getAllProducts("A").size(),size - 1);
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