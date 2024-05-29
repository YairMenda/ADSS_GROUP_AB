package dev.BusinessLayer;

public abstract class Report {
    
    private String title;

    public Report(String title)
    {
        this.title = title;
    }

    public abstract String printReport();

    public String title()
    {
        return this.title;
    }
}
