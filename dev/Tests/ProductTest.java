package dev.Tests;

import dev.BusinessLayer.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductTest {

    private Product p;

    @BeforeAll
    public void init()
    {
        Random r = new Random();
        //p = new Product("Tea","Drinks","Hot-Drinks",                "Test-Supplier",1.5,10,3);
        p.addItem(LocalDate.now().minusDays(r.nextInt(365)));
        p.addItem(LocalDate.now().plusDays(r.nextInt(365)));
        p.addItem(LocalDate.now().plusDays(r.nextInt(365)));
        p.setDamagedItem(2);
    }
    @org.junit.jupiter.api.Test
    void sellItem()
    {
        p.sellItem(3);
        Assertions.assertEquals(p.itemsLeft(),0);
    }

    @org.junit.jupiter.api.Test
    void getExpiredItems()
    {
        Assertions.assertEquals(p.getExpiredItems(false).size(),1);
    }

    @org.junit.jupiter.api.Test
    void getDamagedItems()
    {
        Assertions.assertEquals(p.getDamagedItems(false).size(),1);
    }

    @org.junit.jupiter.api.Test
    void setStorageDiscount()
    {
        p.setStorageDiscount(30,5);
        Assertions.assertEquals(p.getStoragePrice(),7);
    }
}