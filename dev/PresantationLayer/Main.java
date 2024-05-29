package dev.PresantationLayer;
import dev.ServiceLayer.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    
   

    //needs to add storage.init()
    public static void main(String[] args) {
        Main mainApp = new Main();
        StorageHandler storageHandler = new StorageHandler();
        storageHandler.StorageLoop();
    }

}
