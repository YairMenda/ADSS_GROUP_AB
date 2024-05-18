
package ServiceLayer;

/**
 *
 * @author Omera
 */
public class Response {
    public Object returnValue;
    public String errorMessage;


public Response(Object o, String errMsg){
    this.returnValue= o;
    this.errorMessage=errMsg;
}


public Object getReturnValue(){
    return returnValue;
}


public String getErrorMessage(){
    return errorMessage;
}

 public Boolean ErrorOccured() {
    return errorMessage != null;
}


}
