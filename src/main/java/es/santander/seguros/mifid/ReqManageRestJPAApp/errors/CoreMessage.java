package es.santander.seguros.mifid.ReqManageRestJPAApp.errors;

/**
 * 
 * @author CMC
 *
 */
public class CoreMessage  {
    private final String fieldName;
    private final String message;
 
    public CoreMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
     
    public String getFieldName() {
        return fieldName;
    }
 
    public String getMessage() {
        return message;
    }
    
}
