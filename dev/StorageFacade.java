package dev;

import java.util.HashMap;

public class StorageFacade {

    private HashMap<String, Storage> storages;
    
    public StorageFacade()
    {
        this.storages = new HashMap<>();
    }

    public boolean addStorage(String storageName)
    {
        return this.storages.put(storageName, new Storage(storageName)) == null;
    }

    public boolean deleteStorage(String storageName)
    {
        return this.storages.remove(storageName) == null;
    }
    
}
