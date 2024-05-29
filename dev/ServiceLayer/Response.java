package dev.ServiceLayer;

public class Response {
    
    private Object returnValue;
    private String errorMessage;

    public Response(Object returnValue, String errorMessage) 
    {

        this.returnValue = returnValue;
        this.errorMessage = errorMessage;
    }

    public Boolean ErrorOccured()
    {
        return this.errorMessage != null;
    }

    public String getErrorMsg()
    {
        return this.errorMessage;
    }

    public Object getReturnValue()
    {
        return this.returnValue;
    }
}