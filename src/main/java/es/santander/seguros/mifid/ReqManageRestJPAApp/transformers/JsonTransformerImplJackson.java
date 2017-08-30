package es.santander.seguros.mifid.ReqManageRestJPAApp.transformers;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author CMC
 *
 */
@Service
public class JsonTransformerImplJackson implements JsonTransformer {

	
	/**
	 * 
	 */
    @Override
    public String toJson(Object data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            
            return objectMapper.writeValueAsString(data);
            
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }

    
    /**
     * 
     */
    @Override
    public Object fromJson(String json, Class clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
                                    
            return objectMapper.readValue(json, clazz);
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
