package es.santander.seguros.mifid.ReqManageRestJPAApp.transformers;

/**
 * 
 * @author CMC
 *
 */
public interface JsonTransformer {
    String toJson(Object data);
    Object fromJson(String json, Class clazz);
}
