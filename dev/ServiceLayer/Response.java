package dev.ServiceLayer;

public class Response {
    
    public Object returnValue;
    public String errorMessage;

    public Response(Object returnValue, String errorMessage) 
    {

        this.returnValue = returnValue;
        this.errorMessage = errorMessage;
    }
    public Boolean ErrorOccured()
    {
        return this.errorMessage != null;
    }
}
