package dev.ServiceLayer;
import dev.BusinessLayer.StorageFacade;


/**
 * StorageService
 */
public class StorageService {

    private StorageFacade sFacade;

    public StorageService(StorageFacade sf){
        this.sFacade = sf;
    }

    
}