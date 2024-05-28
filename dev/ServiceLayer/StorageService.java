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

    public Response deleteProduct(String storageName, int productId) {
        try {
            return new Response(storageFacade.deleteProduct(storageName, productId), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }
    
    public Response getProduct(String storageName, int productId){
        try {
            return new Response(storageFacade.getProduct(storageName,productId).toString(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }


    public Response deleteSubCategory(String storageName, String categoryName, String subCategoryName) {
        try {
            return new Response(storageFacade.deleteSubCategory(storageName, categoryName, subCategoryName),null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response setSupplierDiscount(String storageName, int productId, int discount, int days) {
        try {
            storageFacade.setSupplierDiscount(storageName, productId, discount, days);
            return new Response(productId,null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response setStoreDiscount(String storageName, int productId, int storageDiscount, int days) {
        try {
            storageFacade.setStoreDiscount(storageName, productId, storageDiscount, days);
            return new Response(productId, null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response addSubCategory(String storageName, String categoryName, String subCategoryName) {
        try {
            return new Response(storageFacade.addSubCategory(storageName, categoryName, subCategoryName), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response reportByCategory(String storageName, String categoryName) {
        try {
            return new Response(storageFacade.reportByCategory(storageName, categoryName).toString(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response reportBySubCategory(String storageName, String categoryName, String subCategory) {
        try {
            return new Response(storageFacade.reportBySubCategory(storageName, categoryName, subCategory).toString(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response reportByBadItems(String storageName) {
        try {
            return new Response(storageFacade.reportByBadItems(storageName).toString(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response getProductsBySize(String storageName, int size) {
        try {
            return new Response(storageFacade.getProdcutsBySize(storageName, size).toString(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }


    public Response deleteCategory(String storageName, String categoryName) {
        try {
            return new Response(storageFacade.deleteCategory(storageName, categoryName),null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response addStorage(String storageName) {
        try {
            return new Response(storageFacade.addStorage(storageName),null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response deleteStorage(String storageName) {
        try {
            return new Response(storageFacade.deleteStorage(storageName), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    
    public Response addCategory(String storageName, String categoryName) {
        try {
            return new Response(storageFacade.addCategory(storageName, categoryName), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response addItem(String storageName, int productId, int quantity, LocalDate expDate) {
        try {
            storageFacade.addItem(storageName, productId, quantity, expDate);
            return new Response(productId, null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response addProduct(String storageName, String category, String subCategory, String productName,
                             String supplierName, double size, double price, double supplierPrice) 
    {
        try {
            storageFacade.addProduct(storageName, category, subCategory, productName, supplierName, size, price, supplierPrice);
            return new Response("success", null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response sellItem(String storageName, int itemId, int productId)
    {
        try {
            return new Response(storageFacade.sellItem(storageName, itemId, productId), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }
}