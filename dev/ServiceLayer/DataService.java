package dev.ServiceLayer;

import dev.BusinessLayer.StorageFacade;

public class DataService {

        private StorageFacade storageFacade;
        public DataService(StorageFacade sf){
            this.storageFacade = sf;
        }

        public void LoadData(){storageFacade.loadData();}

}
