package dev;

public abstract class Report {
    
    private String title;

    public Report(String title)
    {
        this.title = title;
    }

    public abstract String printReport();
}
