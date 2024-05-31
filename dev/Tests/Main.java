package dev.Tests;

public class Main {

    public static void main(String[] args)
    {
        StorageServiceTest sst = new StorageServiceTest();
        ProductServiceTest pst = new ProductServiceTest();
        sst.addStorage();
        sst.addCategory();
        sst.addSubCategory();
        sst.getProductsBySize();
        sst.reportByCategory();
        sst.reportByBadItems();
        sst.deleteSubCategory();
        sst.deleteStorage();
        pst.addProduct();
        pst.addItem();
        pst.setStoreDiscount();
        pst.sellItem();
    }
    
}
