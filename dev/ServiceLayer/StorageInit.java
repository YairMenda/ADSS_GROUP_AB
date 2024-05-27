package dev.ServiceLayer;

import java.io.StreamCorruptedException;
import java.time.LocalDate;
import java.util.Random;

public class StorageInit {

    StorageService storageService = new StorageService();

    public StorageInit()
    {

    }

    public StorageService getStorageService()
    {
        return this.storageService;
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
        this.storageService.addStorage("Roy and Menash store");
    }

    public void generateCategories()
    {
        this.storageService.addCategory("Roy and Menash store", "Snacks");
        this.storageService.addCategory("Roy and Menash store", "Drinks");
        this.storageService.addCategory("Roy and Menash store", "Pharmecy");
        this.storageService.addCategory("Roy and Menash store", "Toys");
    }

    public void generateSubCategories()
    {
        this.storageService.addSubCategory("Roy and Menash store", "Snacks", "Chocolates");
        this.storageService.addSubCategory("Roy and Menash store", "Snacks", "Salties");
        this.storageService.addSubCategory("Roy and Menash store", "Drinks", "Alcohol");
        this.storageService.addSubCategory("Roy and Menash store", "Drinks", "Soft-Drinks");
        this.storageService.addSubCategory("Roy and Menash store", "Pharmecy", "Shower");
        this.storageService.addSubCategory("Roy and Menash store", "Pharmecy", "Pills");
        this.storageService.addSubCategory("Roy and Menash store", "Toys", "Lego");
    }

    public void generateProducts() {
        try {
            // Snacks - Chocolates
            storageService.addProduct("Roy and Menash store", "Snacks", "Chocolates", "Milk Chocolate", "Supplier A", 1.5, 2.0, 1.0);
            storageService.addProduct("Roy and Menash store", "Snacks", "Chocolates", "Dark Chocolate", "Supplier A", 1.0, 2.5, 1.5);
            storageService.addProduct("Roy and Menash store", "Snacks", "Chocolates", "White Chocolate", "Supplier B", 1.2, 2.2, 1.2);

            // Snacks - Salties
            storageService.addProduct("Roy and Menash store", "Snacks", "Salties", "Salted Chips", "Supplier C", 0.5, 1.5, 0.7);
            storageService.addProduct("Roy and Menash store", "Snacks", "Salties", "Pretzels", "Supplier C", 0.4, 1.0, 0.6);
            storageService.addProduct("Roy and Menash store", "Snacks", "Salties", "Popcorn", "Supplier D", 1.0, 1.8, 0.9);

            // Drinks - Alcohol
            storageService.addProduct("Roy and Menash store", "Drinks", "Alcohol", "Beer", "Supplier E", 0.5, 3.0, 2.0);
            storageService.addProduct("Roy and Menash store", "Drinks", "Alcohol", "Wine", "Supplier F", 1.0, 10.0, 8.0);
            storageService.addProduct("Roy and Menash store", "Drinks", "Alcohol", "Whiskey", "Supplier G", 0.7, 25.0, 20.0);

            // Drinks - Soft-Drinks
            storageService.addProduct("Roy and Menash store", "Drinks", "Soft-Drinks", "Coke", "Supplier H", 0.5, 1.0, 0.5);
            storageService.addProduct("Roy and Menash store", "Drinks", "Soft-Drinks", "Sprite", "Supplier H", 0.5, 1.0, 0.5);
            storageService.addProduct("Roy and Menash store", "Drinks", "Soft-Drinks", "Fanta", "Supplier H", 0.5, 1.0, 0.5);

            // Pharmacy - Shower
            storageService.addProduct("Roy and Menash store", "Pharmacy", "Shower", "Shampoo", "Supplier I", 0.5, 4.0, 2.0);
            storageService.addProduct("Roy and Menash store", "Pharmacy", "Shower", "Body Wash", "Supplier I", 0.5, 3.5, 2.0);
            storageService.addProduct("Roy and Menash store", "Pharmacy", "Shower", "Conditioner", "Supplier J", 0.5, 4.5, 2.5);

            // Pharmacy - Pills
            storageService.addProduct("Roy and Menash store", "Pharmacy", "Pills", "Painkillers", "Supplier K", 0.1, 5.0, 2.0);
            storageService.addProduct("Roy and Menash store", "Pharmacy", "Pills", "Vitamins", "Supplier L", 0.2, 7.0, 3.0);
            storageService.addProduct("Roy and Menash store", "Pharmacy", "Pills", "Cough Syrup", "Supplier M", 0.3, 6.0, 2.5);

            // Toys - Lego
            storageService.addProduct("Roy and Menash store", "Toys", "Lego", "Lego City", "Supplier N", 1.0, 30.0, 20.0);
            storageService.addProduct("Roy and Menash store", "Toys", "Lego", "Lego Friends", "Supplier N", 1.0, 25.0, 18.0);
            storageService.addProduct("Roy and Menash store", "Toys", "Lego", "Lego Technic", "Supplier O", 1.5, 50.0, 35.0);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

     public void generateItems() {
        Random random = new Random();
        try {
            // Adding items for each product
            this.storageService.addItem("Roy and Menash store", 1, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 2, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 3, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 4, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 5, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 6, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 7, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 8, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 9, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 10, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 11, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 12, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 13, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 14, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 15, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 16, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 17, 10, LocalDate.now().plusDays(random.nextInt(365)));
            this.storageService.addItem("Roy and Menash store", 18, 10, LocalDate.now().plusDays(random.nextInt(365)));
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }

}

