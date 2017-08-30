package es.santander.seguros.mifid.ReqManageRestJPAApp.errors;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author CMC
 *
 */
public class CoreException extends Exception {
    
	private static final long serialVersionUID = 1L;
	
	private List<CoreMessage> coreMessages = new ArrayList<>();

    public CoreException(List<CoreMessage> coreMessages) {
        this.coreMessages.addAll(coreMessages);
    }

    public CoreException(CoreMessage coreMessage) {
        this.coreMessages.add(coreMessage);
    }

    public List<CoreMessage> getCoreMessages() {
        return coreMessages;
    }
}