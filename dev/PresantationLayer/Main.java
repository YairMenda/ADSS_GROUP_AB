package dev.PresantationLayer;

import dev.DataAccessLayer.ProductController;

public class Main {
    
   

    //needs to add storage.init()
    public static void main(String[] args) 
    {
         StorageHandler storageHandler = new StorageHandler();
         storageHandler.StorageLoop();
    }

}
