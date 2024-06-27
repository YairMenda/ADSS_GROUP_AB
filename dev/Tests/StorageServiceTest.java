package dev.Tests;

import dev.ServiceLayer.Response;
import dev.ServiceLayer.StorageInit;
import dev.ServiceLayer.StorageService;

public class StorageServiceTest {

    private StorageService storageService;
    private StorageInit storageInit;

    public StorageServiceTest()
    {
        this.storageInit = new StorageInit();
        this.storageService = this.storageInit.getStorageService();
        this.storageInit.init();
    }

    //add new storage test
    public void addStorage()
    {
        Response r = this.storageService.addStorage("Test");
        if(r.ErrorOccured())
            System.out.println("Add storage test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Add storage test ----> succeded. ");
    }


    //add new category test
    public void addCategory()
    {
        Response r = this.storageService.addCategory("Test","Test-Category");
        if(r.ErrorOccured())
            System.out.println("Add category test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Add category test ----> succeded. ");
    }

    //add new sub-category test
    public void addSubCategory()
    {
        Response r = this.storageService.addSubCategory("Test","Test-Category","Test-Sub-Category");
        if(r.ErrorOccured())
            System.out.println("Add Sub-Category test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Add Sub-Category test ----> succeded. ");
    }

    //get all products by specific size
    public void getProductsBySize()
    {
        Response r = this.storageService.getProductsBySize("A","Snacks","all",0.5);
        if(r.ErrorOccured())
            System.out.println("Get prodcuts by size ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Get prodcuts by size ----> succeded. ");
    }

    //gets a report by specific category
    public void reportByCategory()
    {
        Response r = this.storageService.reportByCategory("Test","Test-Category");
        if(r.ErrorOccured())
            System.out.println("Report by category ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Report by category ----> succeded. ");
    }

    //gets a report of expired or damaged items
    public void reportByBadItems()
    {
        Response r = this.storageService.reportByBadItems("Test");
        if(r.ErrorOccured())
            System.out.println("Report by bad items ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Report by bad items ----> succeded. ");
    }

    //delete sub-category test
    public void deleteSubCategory()
    {
        Response r = this.storageService.deleteSubCategory("Test","Test-Category","Test-Sub-Category");
        if(r.ErrorOccured())
            System.out.println("Delete sub-sategory test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Delete sub-sategory test ----> succeded. ");
    }

    //delete storage test
    public void deleteStorage()
    {
        Response r = this.storageService.deleteStorage("Test");
        if(r.ErrorOccured())
            System.out.println("Delete storage test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Delete storage test ----> succeded. ");
    }
    
}
