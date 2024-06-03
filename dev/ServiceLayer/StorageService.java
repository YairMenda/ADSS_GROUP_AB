package dev.ServiceLayer;
import java.util.LinkedList;

import dev.BusinessLayer.Product;
import dev.BusinessLayer.StorageFacade;


/**
 * StorageService
 */
public class StorageService {

    private StorageFacade storageFacade;

    public StorageService(StorageFacade sf) {
        this.storageFacade = sf;
    }


    //response - value: boolean, error: string
    public Response deleteSubCategory(String storageName, String categoryName, String subCategoryName) {
        try {
            return new Response(storageFacade.deleteSubCategory(storageName, categoryName, subCategoryName),null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    //response - value: boolean, error: string
    public Response addSubCategory(String storageName, String categoryName, String subCategoryName) {
        try {
            return new Response(storageFacade.addSubCategory(storageName, categoryName, subCategoryName), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }
    
    //response - value: ProductReport, error: string
    public Response reportByCategory(String storageName, String categoryName) {
        try {
            return new Response(storageFacade.reportByCategory(storageName, categoryName).printReport(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }
    
    //response - value: ProductReport, error: string
    public Response reportBySubCategory(String storageName, String categoryName, String subCategory) {
        try {
            return new Response(storageFacade.reportBySubCategory(storageName, categoryName, subCategory).printReport(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    //response - value: ItemReport, error: string
    public Response reportByBadItems(String storageName) {
        try {
            return new Response(storageFacade.reportByBadItems(storageName).printReport(), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    //response - value: boolean, error: string
    public Response deleteCategory(String storageName, String categoryName) {
        try {
            return new Response(storageFacade.deleteCategory(storageName, categoryName),null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    //response - value: boolean, error: string
    public Response addStorage(String storageName) {
        try {
            return new Response(storageFacade.addStorage(storageName),null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    //response - value: boolean, error: string
    public Response deleteStorage(String storageName) {
        try {
            return new Response(storageFacade.deleteStorage(storageName), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    //response - value: boolean, error: string
    public Response addCategory(String storageName, String categoryName) {
        try {
            return new Response(storageFacade.addCategory(storageName, categoryName), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response getProductsBySize(String storageName, double size) 
    {
        try {
            LinkedList<Product> temp = storageFacade.getProdcutsBySize(storageName, size);
            LinkedList<ProductToSend> result = new LinkedList<>();
            for (Product product : temp) 
            {
                result.add(new ProductToSend(product));
            }
            return new Response(result, null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response getAllProdcuts(String storageName)
    {
        try {
            LinkedList<Product> temp = storageFacade.getAllProducts(storageName);
            LinkedList<ProductToSend> result = new LinkedList<>();
            for (Product product : temp) 
            {
                result.add(new ProductToSend(product));
            }
            return new Response(result, null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response getAllSubCategories(String storageName, String category)
    {
        try
        {
            return new Response(storageFacade.getAllSubCategories(storageName, category), null);
        }
        catch (Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    public Response getAllCategories(String storageName)
    {
        try
        {
            return new Response(storageFacade.getAllCategories(storageName), null);
        }
        catch (Exception e)
        {
            return new Response(null, e.getMessage());
        }
    }

    public StorageFacade getStorageFacade()
    {
        return this.storageFacade;
    }

    
}