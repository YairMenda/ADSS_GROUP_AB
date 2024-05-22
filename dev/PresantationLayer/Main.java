package dev.PresantationLayer;

import dev.ServiceLayer.StorageService;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    StorageService storageService = new StorageService();

    public static void main(String[] args) {
        Main mainApp = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter storage name to manage: ");
        String storageName = scanner.nextLine();
        mainApp.StorageLoop(storageName);

        scanner.close();
    }

    public void StorageLoop(String storageName) {
        Scanner s = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Please select an action: ");
            System.out.println("1. Add category of products");
            System.out.println("2. Add sub-category of products");
            System.out.println("3. Add items");
            System.out.println("4. Add a product");
            System.out.println("5. View product");
            System.out.println("6. Get a report");
            System.out.println("7. Get products by size");
            System.out.println("8. Sell items");
            System.out.println("9. Delete product, categories or sub categories");
            System.out.println("10. Exit this storage");
            actionHandler(storageName, s.nextInt());
        }
        s.close();
    }

    public void actionHandler(String storageName, int action) {
        Scanner s = new Scanner(System.in);
        switch (action) {
            case 1:
                System.out.print("Enter category name to add: ");
                String categoryName = s.next();
                try {
                    System.out.println(storageService.addCategory(storageName, categoryName));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                break;

            case 2:
                System.out.print("Enter category name: ");
                categoryName = s.next();
                System.out.print("Enter sub-category name to add: ");
                String subCategoryName = s.next();
                try {
                    System.out.println(storageService.addSubCategory(storageName, categoryName, subCategoryName));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                break;

            case 3:
                System.out.print("Enter product ID: ");
                int productId = s.nextInt();
                System.out.print("Enter quantity: ");
                int quantity = s.nextInt();
                System.out.print("Enter expiration year: ");
                int year = s.nextInt();
                System.out.print("Enter expiration month: ");
                int month = s.nextInt();
                System.out.print("Enter expiration day: ");
                int day = s.nextInt();
                LocalDate expDate = LocalDate.of(year, month, day);
                try {
                    storageService.addItem(storageName, productId, quantity, expDate);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                break;

            case 4:
                System.out.print("Enter category name: ");
                categoryName = s.next();
                System.out.print("Enter sub-category name: ");
                subCategoryName = s.next();
                System.out.print("Enter product ID: ");
                productId = s.nextInt();
                System.out.print("Enter product name: ");
                String productName = s.next();
                System.out.print("Enter supplier name: ");
                String supplierName = s.next();
                System.out.print("Enter product size: ");
                double size = s.nextDouble();
                System.out.print("Enter product price: ");
                double price = s.nextDouble();
                System.out.print("Enter supplier price: ");
                double supplierPrice = s.nextDouble();
                try {
                    storageService.addProduct(storageName, categoryName, subCategoryName, productId, productName, supplierName, size, price, supplierPrice);
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                break;

            case 5:
                System.out.print("Enter product ID: ");
                productId = s.nextInt();
                System.out.println(storageService.getProduct(storageName, productId));
                break;

            case 6:
                System.out.println("Please select a report type: ");
                System.out.println("1. Report by category");
                System.out.println("2. Report by sub-category");
                System.out.println("3. Report by bad items");
                int reportType = s.nextInt();
                switch (reportType) {
                    case 1:
                        System.out.print("Enter category name: ");
                        categoryName = s.next();
                        try {
                            System.out.println(storageService.reportByCategory(storageName, categoryName));
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case 2:
                        System.out.print("Enter category name: ");
                        categoryName = s.next();
                        System.out.print("Enter sub-category name: ");
                        subCategoryName = s.next();
                        try {
                            System.out.println(storageService.reportBySubCategory(storageName, categoryName, subCategoryName));
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case 3:
                        System.out.println(storageService.reportByBadItems(storageName));
                        break;
                    default:
                        System.out.println("Invalid report type selected.");
                        break;
                }
                break;

            case 7:
                System.out.print("Enter product size: ");
                size = s.nextDouble();
                System.out.println(storageService.getProductsBySize(storageName, (int) size));
                break;

            case 8:
                System.out.print("Enter product ID: ");
                productId = s.nextInt();
                System.out.print("Enter item ID: ");
                int itemId = s.nextInt();
                try {
                    System.out.println(storageService.sellItem(storageName, itemId, productId));
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
                break;

            case 9:
                System.out.println("Please select a delete type: ");
                System.out.println("1. Delete product");
                System.out.println("2. Delete category");
                System.out.println("3. Delete sub-category");
                int deleteType = s.nextInt();
                switch (deleteType) {
                    case 1:
                        System.out.print("Enter product ID: ");
                        productId = s.nextInt();
                        try {
                            System.out.println(storageService.deleteProduct(storageName, productId));
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case 2:
                        System.out.print("Enter category name: ");
                        categoryName = s.next();
                        try {
                            System.out.println(storageService.deleteCategory(storageName, categoryName));
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    case 3:
                        System.out.print("Enter category name: ");
                        categoryName = s.next();
                        System.out.print("Enter sub-category name: ");
                        subCategoryName = s.next();
                        try {
                            System.out.println(storageService.deleteSubCategory(storageName, categoryName, subCategoryName));
                        } catch (Exception e) {
                            System.out.println(e.toString());
                        }
                        break;
                    default:
                        System.out.println("Invalid delete type selected.");
                        break;
                }
                break;

            case 10:
                System.out.println("Exiting storage management.");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid action selected.");
                break;
        }
    }
}
