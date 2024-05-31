package dev.ServiceLayer;

import java.time.LocalDate;
import java.util.Random;

import dev.BusinessLayer.StorageFacade;

public class StorageInit {

    StorageService storageService;
    ProductService productService;
    StorageFacade storageFacade;

    public StorageInit()
    {
        this.storageFacade = new StorageFacade();
        this.storageService = new StorageService(storageFacade);
        this.productService = new ProductService(storageFacade);
    }

    public StorageService getStorageService()
    {
        return this.storageService;
    }

    public ProductService getProductService()
    {
        return this.productService;
    }

    public void init()
    {
        generateStorages();
        generateCategories();
        generateSubCategories();
        generateProducts();
        generateItems();
    }

    public void generateStorages()
    {
        this.storageService.addStorage("A");
    }

    public void generateCategories()
    {
        this.storageService.addCategory("A", "Snacks");
        this.storageService.addCategory("A", "Drinks");
        this.storageService.addCategory("A", "Pharmacy");
        this.storageService.addCategory("A", "Toys");
    }

    public void generateSubCategories()
    {
        this.storageService.addSubCategory("A", "Snacks", "Chocolates");
        this.storageService.addSubCategory("A", "Snacks", "Salties");
        this.storageService.addSubCategory("A", "Drinks", "Alcohol");
        this.storageService.addSubCategory("A", "Drinks", "Soft-Drinks");
        this.storageService.addSubCategory("A", "Pharmacy", "Shower");
        this.storageService.addSubCategory("A", "Pharmacy", "Pills");
        this.storageService.addSubCategory("A", "Toys", "Lego");
    }

    public void generateProducts() {
        try {
            // Snacks - Chocolates
            productService.addProduct("A", "Snacks", "Chocolates", "Milk Chocolate", "Supplier A", 1.5, 2.0, 1.0);
            productService.addProduct("A", "Snacks", "Chocolates", "Dark Chocolate", "Supplier A", 1.0, 2.5, 1.5);
            productService.addProduct("A", "Snacks", "Chocolates", "White Chocolate", "Supplier B", 1.2, 2.2, 1.2);

            // Snacks - Salties
            productService.addProduct("A", "Snacks", "Salties", "Salted Chips", "Supplier C", 0.5, 1.5, 0.7);
            productService.addProduct("A", "Snacks", "Salties", "Pretzels", "Supplier C", 0.4, 1.0, 0.6);
            productService.addProduct("A", "Snacks", "Salties", "Popcorn", "Supplier D", 1.0, 1.8, 0.9);

            // Drinks - Alcohol
            productService.addProduct("A", "Drinks", "Alcohol", "Beer", "Supplier E", 0.5, 3.0, 2.0);
            productService.addProduct("A", "Drinks", "Alcohol", "Wine", "Supplier F", 1.0, 10.0, 8.0);
            productService.addProduct("A", "Drinks", "Alcohol", "Whiskey", "Supplier G", 0.7, 25.0, 20.0);

            // Drinks - Soft-Drinks
            productService.addProduct("A", "Drinks", "Soft-Drinks", "Coke", "Supplier H", 0.5, 1.0, 0.5);
            productService.addProduct("A", "Drinks", "Soft-Drinks", "Sprite", "Supplier H", 0.5, 1.0, 0.5);
            productService.addProduct("A", "Drinks", "Soft-Drinks", "Fanta", "Supplier H", 0.5, 1.0, 0.5);

            // Pharmacy - Shower
            productService.addProduct("A", "Pharmacy", "Shower", "Shampoo", "Supplier I", 0.5, 4.0, 2.0);
            productService.addProduct("A", "Pharmacy", "Shower", "Body Wash", "Supplier I", 0.5, 3.5, 2.0);
            productService.addProduct("A", "Pharmacy", "Shower", "Conditioner", "Supplier J", 0.5, 4.5, 2.5);

            // Pharmacy - Pills
            productService.addProduct("A", "Pharmacy", "Pills", "Painkillers", "Supplier K", 0.1, 5.0, 2.0);
            productService.addProduct("A", "Pharmacy", "Pills", "Vitamins", "Supplier L", 0.2, 7.0, 3.0);
            productService.addProduct("A", "Pharmacy", "Pills", "Cough Syrup", "Supplier M", 0.3, 6.0, 2.5);

            // Toys - Lego
            productService.addProduct("A", "Toys", "Lego", "Lego City", "Supplier N", 1.0, 30.0, 20.0);
            productService.addProduct("A", "Toys", "Lego", "Lego Friends", "Supplier N", 1.0, 25.0, 18.0);
            productService.addProduct("A", "Toys", "Lego", "Lego Technic", "Supplier O", 1.5, 50.0, 35.0);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

     public void generateItems() {
        Random random = new Random();
        try {
            // Adding items for each product
            this.productService.addItem("A", 1, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 2, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 3, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 4, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 5, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 6, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 7, 10, LocalDate.now().minusDays(random.nextInt(365)));
            this.productService.addItem("A", 8, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 9, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 10, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 11, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 12, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 13, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 14, 10, LocalDate.now().minusDays(random.nextInt(365)));
            this.productService.addItem("A", 15, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 16, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 17, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.productService.addItem("A", 18, 10, LocalDate.now().minusDays(random.nextInt(365)));
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

}

