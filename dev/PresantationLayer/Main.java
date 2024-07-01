package dev.PresantationLayer;

import dev.DataAccessLayer.ProductController;
import dev.DataAccessLayer.StorageController;
import dev.ServiceLayer.StorageInit;

public class Main {
    
   

    //needs to add storage.init()
    public static void main(String[] args) 
    {
//       StorageInit si = new StorageInit();
//       si.init();
         StorageHandler storageHandler = new StorageHandler();
         storageHandler.StorageLoop();
    }

}
