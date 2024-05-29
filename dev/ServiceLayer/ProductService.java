package dev.ServiceLayer;
import java.time.LocalDate;
import java.util.LinkedList;

import dev.BusinessLayer.*;

public class ProductService {


    private StorageFacade storageFacade;

    public ProductService(StorageFacade storageFacade)
    {
        this.storageFacade = storageFacade;
    }

    //response - value: boolean, error: string
    public Response deleteProduct(String storageName, int productId) 
    {
        try {
            return new Response(storageFacade.deleteProduct(storageName, productId), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }
    
    //response - value: productToSend, error: string
    public Response getProduct(String storageName, int productId)
    {
        try {
            return new Response(new ProductToSend(storageFacade.getProduct(storageName,productId)), null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    //response - value: string, error: string
    public Response setSupplierDiscount(String storageName, int productId, int discount, int days) 
    {
        try {
            storageFacade.setSupplierDiscount(storageName, productId, discount, days);
            return new Response(productId,null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }

    public Response setStoreDiscount(String storageName, int productId, int storageDiscount, int days) 
    {
        try {
            storageFacade.setStoreDiscount(storageName, productId, storageDiscount, days);
            return new Response(productId, null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }


    public Response addItem(String storageName, int productId, int quantity, LocalDate expDate) 
    {
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

    public Response getAllItems(String storageName, int productId)
    {
        try {
            LinkedList<Item> temp = storageFacade.getAllItems(storageName, productId);
            LinkedList<ItemToSend> result = new LinkedList<>();
            for (Item item : temp) 
            {
                result.add(new ItemToSend(item));
            }
            return new Response(result, null);
        } catch (Exception e) {
            return new Response(null, e.getMessage());
        }
    }
    
}
