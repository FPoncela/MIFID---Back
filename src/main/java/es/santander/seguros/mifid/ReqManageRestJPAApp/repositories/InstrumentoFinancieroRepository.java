package es.santander.seguros.mifid.ReqManageRestJPAApp.repositories;

import org.springframework.data.repository.CrudRepository;

import es.santander.seguros.mifid.ReqManageRestJPAApp.domain.InsFinanciero;

/**
 * @author CMC
 */
public interface InstrumentoFinancieroRepository extends CrudRepository<InsFinanciero, String> {
	
}
