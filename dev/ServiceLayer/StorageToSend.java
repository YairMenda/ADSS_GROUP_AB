package ServiceLayer;

public class StorageToSend {
    private String storageName;
    
    public StorageToSend(String storageName)
    {
        this.storageName = storageName;
    }

    public String getName(){return storageName;}
}
