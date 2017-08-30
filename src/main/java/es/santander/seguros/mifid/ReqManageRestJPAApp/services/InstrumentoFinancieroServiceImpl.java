package es.santander.seguros.mifid.ReqManageRestJPAApp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.santander.seguros.mifid.ReqManageRestJPAApp.domain.InsFinanciero;
import es.santander.seguros.mifid.ReqManageRestJPAApp.repositories.InstrumentoFinancieroRepository;

/**
 * @author CMC
 */
@Service
public class InstrumentoFinancieroServiceImpl implements InstrumentoFinancieroService {

    private InstrumentoFinancieroRepository instrumentoFinancieroRepository;    

    @Autowired
    public InstrumentoFinancieroServiceImpl(InstrumentoFinancieroRepository instrumentoFinancieroRepository) {
        this.instrumentoFinancieroRepository = instrumentoFinancieroRepository;        
    }


    @Override
    public List<InsFinanciero> listAll() {
        List<InsFinanciero> instrumentosFinancieros = new ArrayList<>();
        instrumentoFinancieroRepository.findAll().forEach(instrumentosFinancieros::add);
        return instrumentosFinancieros;
    }

    @Override
    public InsFinanciero getByIfin_Idinsfin(String ifin_idinsfin) {
        return instrumentoFinancieroRepository.findOne(ifin_idinsfin);
    }

    @Override
    public InsFinanciero create(InsFinanciero instrumentoFinanciero) {
    	instrumentoFinancieroRepository.save(instrumentoFinanciero);
        return instrumentoFinanciero;
    }
    
    @Override
    public InsFinanciero saveOrUpdate(InsFinanciero instrumentoFinanciero) {
    	instrumentoFinancieroRepository.save(instrumentoFinanciero);
        return instrumentoFinanciero;
    }

    @Override
    public void delete(String ifin_idinsfin) {
    	instrumentoFinancieroRepository.delete(ifin_idinsfin);

    }   
    
    @Override
    public boolean exists(InsFinanciero instrumentoFinanciero) {
        return getByIfin_Idinsfin(instrumentoFinanciero.getIfin_idinsfin()) != null;
    }
	
    
}
