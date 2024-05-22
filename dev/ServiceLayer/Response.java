package dev.ServiceLayer;

public class Response
{
    public String ErrorMessage;
    public Object ReturnValue;

    public Response(Object returnValue, String errorMessage)
    {
        ErrorMessage = errorMessage;
        ReturnValue = returnValue;
    }

    
}