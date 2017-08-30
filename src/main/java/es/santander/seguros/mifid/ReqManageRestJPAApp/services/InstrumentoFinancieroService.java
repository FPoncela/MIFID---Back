package es.santander.seguros.mifid.ReqManageRestJPAApp.services;

import java.util.List;

import es.santander.seguros.mifid.ReqManageRestJPAApp.domain.InsFinanciero;

/**
 * @author CMC
 */
public interface InstrumentoFinancieroService {
   
    
    List<InsFinanciero> listAll();

    InsFinanciero getByIfin_Idinsfin(String ifin_idinsfin);

    InsFinanciero create(InsFinanciero instrumentoFinanciero);
    
    InsFinanciero saveOrUpdate(InsFinanciero instrumentoFinanciero);  
    
    void delete(String ifin_idinsfin);    
    
    boolean exists(InsFinanciero instrumentoFinanciero);
    
}
