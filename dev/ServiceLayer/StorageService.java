package dev.ServiceLayer;
import dev.BusinessLayer.StorageFacade;

import java.time.LocalDate;

/**
 * StorageService
 */
public class StorageService {

    private StorageFacade storageFacade;

    public StorageService() {
        this.storageFacade = new StorageFacade();
    }

    public String deleteProduct(String storageName, int productId) {
        try {
            storageFacade.deleteProduct(storageName, productId);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }
    
    public String getProduct(String storageName, int productId){
        try {
            
            return createResponse(storageFacade.getProduct(storageName,productId).toString(), null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }


    public String deleteSubCategory(String storageName, String categoryName, String subCategoryName) {
        try {
            storageFacade.deleteSubCategory(storageName, categoryName, subCategoryName);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String setSupplierDiscount(String storageName, int productId, int discount, int days) {
        try {
            storageFacade.setSupplierDiscount(storageName, productId, discount, days);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String setStoreDiscount(String storageName, int productId, int storageDiscount, int days) {
        try {
            storageFacade.setStoreDiscount(storageName, productId, storageDiscount, days);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String addSubCategory(String storageName, String categoryName, String subCategoryName) {
        try {
            storageFacade.addSubCategory(storageName, categoryName, subCategoryName);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String reportByCategory(String storageName, String categoryName) {
        try {
            return createResponse(storageFacade.reportByCategory(storageName, categoryName).toString(), null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String reportBySubCategory(String storageName, String categoryName, String subCategory) {
        try {
            return createResponse(storageFacade.reportBySubCategory(storageName, categoryName, subCategory).toString(), null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String reportByBadItems(String storageName) {
        try {
            return createResponse(storageFacade.reportByBadItems(storageName).toString(), null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String getProductsBySize(String storageName, int size) {
        try {
            return createResponse(storageFacade.getProdcutsBySize(storageName, size).toString(), null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }


    public String deleteCategory(String storageName, String categoryName) {
        try {
            storageFacade.deleteCategory(storageName, categoryName);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String addStorage(String storageName) {
        try {
            storageFacade.addStorage(storageName);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String deleteStorage(String storageName) {
        try {
            storageFacade.deleteStorage(storageName);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    
    public String addCategory(String storageName, String categoryName) {
        try {
            storageFacade.addCategory(storageName, categoryName);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String addItem(String storageName, int productId, int quantity, LocalDate expDate) {
        try {
            storageFacade.addItem(storageName, productId, quantity, expDate);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String addProduct(String storageName, String category, String subCategory, String productName,
                             String supplierName, double size, double price, double supplierPrice) {
        try {
            storageFacade.addProduct(storageName, category, subCategory, productName, supplierName, size, price, supplierPrice);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    public String sellItem(String storageName, int itemId, int productId) {
        try {
            storageFacade.sellItem(storageName, itemId, productId);
            return createResponse("success", null);
        } catch (Exception e) {
            return createResponse(null, e.toString());
        }
    }

    private String createResponse(String result, String error) {
        /*JsonObject response = new JsonObject();
        if (result != null) {
            response.addProperty("result", result);
        }
        if (error != null) {
            response.addProperty("error", error);
        }*/
        return "A json response with result/error";
    }
}