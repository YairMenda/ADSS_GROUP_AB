package dev.PresantationLayer;
import dev.ServiceLayer.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;


public class StorageHandler {
    
        
    private StorageInit storageInit;
    private StorageService storageService ;
    private ProductService productService ;

    public StorageHandler(){
            
        storageInit = new StorageInit();
        storageService = storageInit.getStorageService();
        productService = storageInit.getProductService();
        storageInit.init();
    }

    
    public void StorageLoop() {
        Scanner s = new Scanner(System.in);
        boolean exit = false;
        System.out.print("Enter storage name to manage: ");
        String storageName = s.nextLine();
        while (!exit) {
            System.out.println("Please select an action: ");
            System.out.println("1. Add category of products");
            System.out.println("2. Add sub-category of products");
            System.out.println("3. Add items");
            System.out.println("4. Add a product");
            System.out.println("5. View products");
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
        Response r;
        switch (action) {
            case 1:
                System.out.print("Enter category name to add: ");
                String categoryName = s.next();
                r = storageService.addCategory(storageName, categoryName);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(categoryName + " added");
                break;

            case 2:
                System.out.print("Enter category name: ");
                categoryName = s.next();
                System.out.print("Enter sub-category name to add: ");
                String subCategoryName = s.next();
                r = storageService.addSubCategory(storageName, categoryName,subCategoryName);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(subCategoryName + " added to category " + categoryName);    
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
                r = productService.addItem(storageName, productId, quantity, expDate);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(quantity + " items of product number " + productId +" have been added");    
                break;
            case 4:
                System.out.print("Enter category name: ");
                categoryName = s.next();
                System.out.print("Enter sub-category name: ");
                subCategoryName = s.next();
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
                r = productService.addProduct(storageName, categoryName, subCategoryName, productName, supplierName, size, price, supplierPrice);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(productName + " has been added");    
                break;

            case 5:
                showAllProducts(storageName);
                System.out.print("Enter product ID to see description: ");
                productId = s.nextInt();
                System.out.println();
                r = productService.getProduct(storageName, productId);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(((ProductToSend)r.getReturnValue()).getDescription()); 
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
                        r = storageService.reportByCategory(storageName, categoryName);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else{
                            System.out.println(r.getReturnValue());
                        }
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
                        r = storageService.reportBySubCategory(storageName, categoryName, subCategoryName);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println(r.getReturnValue());
                        break;
                    case 3:
                        r = storageService.reportByBadItems(storageName);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println(r.getReturnValue());
                        break;
                    default:
                        System.out.println("Invalid report type selected.");
                        break;
                
                }
                break;

            case 7:
                System.out.println("Please enter product size");
                int size2 = s.nextInt();
                r = storageService.getProductsBySize(storageName,size2);
                if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                else
                {                 
                    for(ProductToSend pToSend :((LinkedList<ProductToSend>)r.getReturnValue()))
                    {
                        System.out.println(pToSend.getDescription());
                    }
                }
                break;             
            case 8:
                showAllProducts(storageName);
                System.out.print("Enter product ID: ");
                productId = s.nextInt();
                System.out.print("Enter item ID: ");
                int itemId = s.nextInt();
                r = productService.sellItem(storageName, itemId, productId);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(productId + " item number : " + itemId + " sold");
                break;

            case 9:
                System.out.println("Please select a delete type: ");
                System.out.println("1. Delete product");
                System.out.println("2. Delete category");
                System.out.println("3. Delete sub-category");
                int deleteType = s.nextInt();
                switch (deleteType) {
                    case 1:
                        showAllProducts(storageName);
                        System.out.print("Enter product ID: ");
                        productId = s.nextInt();
                        r = productService.deleteProduct(storageName, productId);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println(productId + " deleted");
                        break;
                    case 2:
                        System.out.print("Enter category name: ");
                        categoryName = s.next();
                        r = storageService.deleteCategory(storageName, categoryName);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println(categoryName + " deleted");
                        break;
                    case 3:
                        System.out.print("Enter category name: ");
                        categoryName = s.next();
                        System.out.print("Enter sub-category name: ");
                        subCategoryName = s.next();
                        r = storageService.deleteSubCategory(storageName, categoryName, subCategoryName);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println(subCategoryName + " deleted");
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

    public void showAllProducts(String storageName){
        Response r = storageService.getAllProdcuts(storageName);
        
        if(r.ErrorOccured()){
            System.out.println(r.getErrorMsg());
        }
        else{
            LinkedList<ProductToSend> pList = (LinkedList<ProductToSend>)r.getReturnValue();
            for (ProductToSend p : pList) {
                System.out.println("ID - " + p.getProductId() + ", Name - " + p.getProductName());
            }
        }
    }

    public void showAllItems(String storageName, int productId){
        Response r = productService.getAllItems(storageName,productId);
        if(r.ErrorOccured()){
            System.out.println(r.getErrorMsg());
        }
        else{
            LinkedList<ItemToSend> iList = (LinkedList<ItemToSend>)r.getReturnValue();
            for (ItemToSend i : iList) {
                System.out.println("ID - " + i.getId());
            }
        }
    }
}
