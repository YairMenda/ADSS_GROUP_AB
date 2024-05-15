package dev;
import java.util.LinkedList;

public class ProductReport extends Report{

    private LinkedList<Product> products;
    private String title;

    public ProductReport(LinkedList<Product> products, String title)
    {
        super(title);
        this.products = products;
    }
    
    public String printReport()
    {
        String result = title + " report: \n";
        for (Product p : this.products)
        {
            result += p.toString() + "\n";
        }
        return result;
    }
    
}
