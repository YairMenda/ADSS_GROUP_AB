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
        //storageInit.init();
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
            System.out.println("6. Update product price");
            System.out.println("7. Get a report");
            System.out.println("8. Get products by size");
            System.out.println("9. Set damaged item");
            System.out.println("10. Manage product discount");
            System.out.println("11. Sell items");
            System.out.println("12. Delete product, categories or sub categories");
            System.out.println("13. Exit this storage");
            actionHandler(storageName, getIntInput(s), s);
        }
        s.close();
    }

    public void actionHandler(String storageName, int action, Scanner s) {
        Response r;
        switch (action) {
            case 1:
                System.out.print("Enter category name to add: ");
                String categoryName = s.nextLine();
                r = storageService.addCategory(storageName, categoryName);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(categoryName + " added");
                break;

            case 2:
                showAllCategories(storageName);
                System.out.print("Enter category name: ");
                categoryName = s.nextLine();
                System.out.print("Enter sub-category name to add: ");
                String subCategoryName = s.nextLine();
                r = storageService.addSubCategory(storageName, categoryName,subCategoryName);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(subCategoryName + " added to category " + categoryName);    
                break;

            case 3:
                showAllProducts(storageName);
                System.out.print("Enter product ID: ");
                int productId = getIntInput(s);
                System.out.print("Enter quantity: ");
                int quantity = getIntInput(s);
                System.out.print("Enter expiration year: ");
                int year = getIntInput(s);
                System.out.print("Enter expiration month (1-12) : ");
                int month = getIntInput(s);
                System.out.print("Enter expiration day (0-30/31) : ");
                int day = getIntInput(s);
                LocalDate expDate = LocalDate.of(year, month, day);
                r = productService.addItem(storageName, productId, quantity, expDate);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(quantity + " items of product number " + productId +" have been added");    
                break;
            case 4:
                showAllCategories(storageName);
                System.out.print("Enter category name: ");
                categoryName = s.nextLine();
                showAllSubCategories(storageName,categoryName);
                System.out.print("Enter sub-category name: ");
                subCategoryName = s.nextLine();
                System.out.print("Enter product name: ");
                String productName = s.nextLine();
                System.out.print("Enter supplier name: ");
                String supplierName = s.nextLine();
                System.out.print("Enter product size: ");
                double size = getDoubleInput(s);
                System.out.print("Enter product price: ");
                double price = getDoubleInput(s);
                System.out.print("Enter minimum required: ");
                int minimumRequired = getIntInput(s);
                System.out.print("Enter supplier price: ");
                double supplierPrice = getDoubleInput(s);
                r = productService.addProduct(storageName, categoryName, subCategoryName, productName, supplierName, size, price, supplierPrice, minimumRequired);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(productName + " has been added");    
                break;

            case 5:
                showAllProducts(storageName);
                System.out.print("Enter product ID to see description: ");
                productId = getIntInput(s);
                System.out.println();
                r = productService.getProduct(storageName, productId);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(((ProductToSend)r.getReturnValue()).getDescription()); 
                break;

            case 6:
                showAllProducts(storageName);
                System.out.println("Please enter product id: ");
                productId = getIntInput(s);
                System.out.println("Please enter new price: ");
                double productPrice = getDoubleInput(s);
                r = this.productService.updateProductPrice(storageName, productId, productPrice);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println("New price update succeded");
                break;
                
            case 7:
                System.out.println("Please select a report type: ");
                System.out.println("1. Report by category");
                System.out.println("2. Report by sub-category");
                System.out.println("3. Report by bad items");
                System.out.println("4. Report by products below minimum required");
                int reportType = getIntInput(s);
                switch (reportType) {
                    case 1:
                        showAllCategories(storageName);
                        System.out.print("Enter category name: ");
                        categoryName = s.nextLine();
                        r = storageService.reportByCategory(storageName, categoryName);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println((String)r.getReturnValue());
                        break;
                    case 2:
                        showAllCategories(storageName);
                        System.out.print("Enter category name: ");
                        categoryName = s.nextLine();
                        showAllSubCategories(storageName,categoryName);
                        System.out.print("Enter sub-category name: ");
                        subCategoryName = s.nextLine();
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
                    case 4:
                        r = storageService.reportByProductsBelowMin(storageName);
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

            case 8:
                showAllCategories(storageName);
                System.out.println("Please enter Category name");
                String category = getStringInput(s);
                showAllSubCategories(storageName, category);
                System.out.println("Please enter Sub-Category name or enter *all* for all sub categories");
                String subCategory = getStringInput(s);
                System.out.println("Please enter product size");
                double size2 = getDoubleInput(s);
                r = storageService.getProductsBySize(storageName,category,subCategory,size2);
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
            case 9:
                showAllProducts(storageName);
                System.out.println("Please select prodcut-id: ");
                int product = getIntInput(s);
                showAllItems(storageName, product);
                System.out.println("Please select item-id: ");
                int item = getIntInput(s);
                r = this.productService.setDamagedItem(storageName, product, item);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println((String)r.getReturnValue());
                break;
            case 10:
                System.out.println("Please select discount action: ");
                System.out.println("1. set product discount");
                System.out.println("2. get days left of current discount ");
                int discAct = getIntInput(s);
                System.out.println("Please select product ID: ");
                showAllProducts(storageName);
                int productSel = getIntInput(s);
                switch (discAct) {
                    case 1:
                        System.out.println("Please enter discount amount (precentage%): ");
                        int discPre = getIntInput(s);
                        System.out.println("Please enter discount period (days): ");
                        int period = getIntInput(s);
                        r = this.productService.setStoreDiscount(storageName, productSel, discPre, period);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println("Discount activated on product ID number :" + (int)r.getReturnValue());
                        break;
                    case 2:
                        r = this.productService.getStoreDiscountDaysLeft(storageName, productSel);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println("Days left: " + (int)r.getReturnValue());
                    default:
                        break;
                }
                break;
            case 11:
                System.out.println("This are the products: ");
                showAllProducts(storageName);
                System.out.print("Enter product ID to get his description: ");
                productId = getIntInput(s);
                System.out.println("Those are this products items: ");
                showAllItems(storageName, productId);
                System.out.print("Enter item ID: ");
                int itemId = getIntInput(s);
                r = productService.sellItem(storageName, itemId, productId);
                if(r.ErrorOccured())
                    System.out.println(r.getErrorMsg());
                else
                    System.out.println(productId + " item number : " + itemId + " sold");
                break;

            case 12:
                System.out.println("Please select a delete type: ");
                System.out.println("1. Delete product");
                System.out.println("2. Delete category");
                System.out.println("3. Delete sub-category");
                int deleteType = getIntInput(s);
                switch (deleteType) {
                    case 1:
                        showAllProducts(storageName);
                        System.out.print("Enter product ID: ");
                        productId = getIntInput(s);
                        r = productService.deleteProduct(storageName, productId);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println(productId + " deleted");
                        break;
                    case 2:
                        System.out.println("Our categories are: ");
                        showAllCategories(storageName);
                        System.out.print("Enter category name: ");
                        categoryName = s.nextLine();
                        r = storageService.deleteCategory(storageName, categoryName);
                        if(r.ErrorOccured())
                            System.out.println(r.getErrorMsg());
                        else
                            System.out.println(categoryName + " deleted");
                        break;
                    case 3:
                        System.out.println("Our categories are: ");
                        showAllCategories(storageName);
                        System.out.print("Enter category name: ");
                        categoryName = s.nextLine();
                        System.out.println("Our sub-categories are: ");
                        showAllSubCategories(storageName, categoryName);
                        System.out.print("Enter sub-category name: ");
                        subCategoryName = s.nextLine();
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

            case 13:
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

    public void showAllSubCategories(String storageName, String category)
    {
        Response r = this.storageService.getAllSubCategories(storageName, category);
        if(r.ErrorOccured())
            System.out.println(r.getErrorMsg());
        else
        {
            for(String subCategoryName: (LinkedList<String>)r.getReturnValue())
            {
                System.out.println("Sub-category:: " + subCategoryName);
            }
        }
    }

    public void showAllCategories(String storageName)
    {
        Response r = this.storageService.getAllCategories(storageName);
        if(r.ErrorOccured())
            System.out.println(r.getErrorMsg());
        else
        {
            for(String categoryName: (LinkedList<String>)r.getReturnValue())
            {
                System.out.println("Category: " + categoryName);
            }
        }
    }

    public double getDoubleInput(Scanner s)
    {
        double result;
        while(true)
        {
            try
            {
                result = s.nextDouble();
                s.nextLine();
                break;
            }
            catch (Exception e)
            {
                System.out.println("Please enter a double input: ");
                s.nextLine();
            }
        }
        return result;
    }

    public int getIntInput(Scanner s)
    {
        int result;
        while(true)
        {
            try
            {
                result = s.nextInt();
                s.nextLine();
                break;
            }
            catch (Exception e)
            {
                System.out.println("Please enter a int input: ");
                s.nextLine();
            }
        }
        return result;
    }

    public String getStringInput(Scanner s)
    {
        String result;
        while(true)
        {
            try
            {
                result = s.nextLine();
                if(!result.equals(""))
                    break;
            }
            catch (Exception e)
            {
                System.out.println("Please enter a int input: ");
                s.nextLine();
            }
        }
        return result;
    }
}
