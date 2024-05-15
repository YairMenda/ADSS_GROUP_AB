package dev;
import java.util.LinkedList;

public class ItemReport extends Report{
    
    private LinkedList<Item> items;
    private String title;

    public ItemReport(LinkedList<Item> items, String title)
    {
        super(title);
        this.items = items;
    }
    
    public String printReport()
    {
        String result = title + " report: \n";
        for (Item it : this.items)
        {
            result += it.toString() + "\n";
        }
        return result;
    }
}
