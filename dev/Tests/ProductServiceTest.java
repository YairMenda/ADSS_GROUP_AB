package dev.Tests;

import java.time.LocalDate;

import dev.ServiceLayer.ProductService;
import dev.ServiceLayer.Response;
import dev.ServiceLayer.StorageInit;

public class ProductServiceTest {

    private ProductService productService;
    private StorageInit storageInit;

    public ProductServiceTest()
    {
        this.storageInit = new StorageInit();
        this.productService = this.storageInit.getProductService();
        this.storageInit.init();
    }

    //add new product test
    public void addProduct()
    {
        Response r = this.productService.addProduct("A","Snacks","Chocolates","Test-Product","Test-Supplier",1,1,1,1);
        if(r.ErrorOccured())
            System.out.println("Add product test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Add product test ----> succeded. ");
    }

    //add new item test
    public void addItem()
    {
        Response r = this.productService.addItem("A", 25, 10, LocalDate.now());
        if(r.ErrorOccured())
            System.out.println("Add item test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Add item test ----> succeded. ");
    }

    //new store price discount test
    public void setStoreDiscount()
    {
        Response r = this.productService.setStoreDiscount("A", 25, 50, 5);
        if(r.ErrorOccured())
            System.out.println("Set store discount test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Set store discount test ----> succeded. ");
    } 

    //sell item test
    public void sellItem()
    {
        Response r = this.productService.sellItem("A", 2, 25);
        if(r.ErrorOccured())
            System.out.println("Sell item test ----> failed. error-masage: " + r.getErrorMsg());
        else
            System.out.println("Sell item test ----> succeded. ");
    } 
    
}
