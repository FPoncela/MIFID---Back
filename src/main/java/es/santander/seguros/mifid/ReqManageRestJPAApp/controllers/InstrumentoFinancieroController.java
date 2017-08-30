package es.santander.seguros.mifid.ReqManageRestJPAApp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.santander.seguros.mifid.ReqManageRestJPAApp.domain.InsFinanciero;
import es.santander.seguros.mifid.ReqManageRestJPAApp.services.InstrumentoFinancieroService;
import es.santander.seguros.mifid.ReqManageRestJPAApp.transformers.JsonTransformer;

/**
 * @author CMC
 */
@RestController
public class InstrumentoFinancieroController {

	@Autowired
	private JsonTransformer jsonTransformer;
	
	private InstrumentoFinancieroService insfinancieroService;      	
	private InsFinanciero instrumentoFinanciero;
	private String jsonSalida;

	/**
	 * 
	 * @param insfinancieroService
	 */
    @Autowired
    public void setProductService(InstrumentoFinancieroService insfinancieroService, JsonTransformer jsonTransformer) {
        this.insfinancieroService = insfinancieroService;
        this.jsonTransformer = jsonTransformer;
    }   

    
    /**
     * 
     * @param httpServletRequest
     * @param httpServletResponse
     */
    @RequestMapping(value="/InstrumentoFinanciero/list", method = RequestMethod.GET, produces = "application/json")
    public void getAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {    	
    	    	    	
        try {
	    	System.out.println("Obteniendo todos los Instrumentos Financieros");
	    	List<InsFinanciero> insfinancieros = new ArrayList();
	    	insfinancieros = insfinancieroService.listAll();
	
	        if (insfinancieros == null || insfinancieros.isEmpty()){
	        	System.out.println("No se encontraron Instrumentos Financieros");
	        	httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
	        } else {
	        	jsonSalida = jsonTransformer.toJson(insfinancieros);
	            
	            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	            httpServletResponse.setContentType("application/json; charset=UTF-8");
	            try {
	            	httpServletResponse.getWriter().println(jsonSalida);
	            } catch (IOException ex1) {
	            	System.out.println("Se ha producido un error en getAll()");
	                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            }
	        }
	        
    	} catch (Exception ex) {
             httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);             
        }
	                                    
    }

    
    /**
     * 
     * @param httpServletRequest
     * @param httpServletResponse
     * @param ifin_idinsfin
     */
    @RequestMapping(value="/InstrumentoFinanciero/{ifin_idinsfin}", method = RequestMethod.GET, produces = "application/json")
    public void getById(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("ifin_idinsfin") String ifin_idinsfin) {
    	
    	try {
	    	System.out.println("Obteniendo Instrumento Financiero con ifin_idinsfin: " + ifin_idinsfin);
	        instrumentoFinanciero = insfinancieroService.getByIfin_Idinsfin(ifin_idinsfin);
	
	        if (instrumentoFinanciero == null){
	        	System.out.println("Instrumento Financiero con  " + ifin_idinsfin + " no encontrado");
	        	httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);        	            
	        } else {
	        	jsonSalida = jsonTransformer.toJson(instrumentoFinanciero);
	        	
	        	httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	            httpServletResponse.setContentType("application/json; charset=UTF-8");
	            try {
	            	httpServletResponse.getWriter().println(jsonSalida);
	            } catch (IOException ex1) {
	            	System.out.println("Se ha producido un error en get()");
	                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            }
	        }
	        
    	} catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);             
       }
                              
    }
 
        
    /**
     * ALTA
     * @param httpServletRequest
     * @param httpServletResponse
     * @param jsonEntrada
     */
	@RequestMapping(value="/InstrumentoFinanciero/add", method = RequestMethod.POST, consumes = "application/json", produces="application/json")
    public void create(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {    			
								
    	System.out.println("Creando nuevo Instrumento Financiero");
    	System.out.println("create()");		
				
    	try {
			instrumentoFinanciero = new InsFinanciero();
			instrumentoFinanciero = (InsFinanciero) jsonTransformer.fromJson(jsonEntrada, InsFinanciero.class);						    	        				
			
	    	if (insfinancieroService.exists(instrumentoFinanciero)) {
	    		System.out.println("El Instrumento Financiero " + instrumentoFinanciero.getIfin_idinsfin() + " ya existe"); 
	    		httpServletResponse.setStatus(HttpServletResponse.SC_CONFLICT);
	        } else {
	        	instrumentoFinanciero = insfinancieroService.create(instrumentoFinanciero);
	        	
	        	jsonSalida = jsonTransformer.toJson(instrumentoFinanciero);
	            
	            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	            httpServletResponse.setContentType("application/json; charset=UTF-8");
	            
	            try {
	            	httpServletResponse.getWriter().println(jsonSalida);
	            } catch (IOException ex1) {
	                Logger.getLogger(InstrumentoFinancieroController.class.getName()).log(Level.SEVERE, null, ex1);
	                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            }
	        }
    	     	
        } catch (Exception ex) {
             httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             httpServletResponse.setContentType("text/plain; charset=UTF-8");
             try {
                 ex.printStackTrace(httpServletResponse.getWriter());
             } catch (IOException ex1) {
                 Logger.getLogger(InstrumentoFinancieroController.class.getName()).log(Level.SEVERE, null, ex1);
             }
        }
                     
    }
    
        
	/**
	 * MODIFICACIÓN
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param jsonEntrada
	 * @param ifin_idinsfin
	 */
    @RequestMapping(value = "/InstrumentoFinanciero/edit/{ifin_idinsfin}", method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
    public void update(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("ifin_idinsfin") String ifin_idinsfin) {
    	
    	System.out.println("Modificando el Instrumento Financiero");
    	System.out.println("update()");		
	
    	try {		
			
    		instrumentoFinanciero = insfinancieroService.getByIfin_Idinsfin(ifin_idinsfin);
			
			if (instrumentoFinanciero == null){
		        	System.out.println("Instrumento Financiero con  " + ifin_idinsfin + " no encontrado");
		        	httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);        	            
		        } else {
		        	instrumentoFinanciero = (InsFinanciero) jsonTransformer.fromJson(jsonEntrada, InsFinanciero.class);
		        	insfinancieroService.saveOrUpdate(instrumentoFinanciero);
		        	
		        	jsonSalida = jsonTransformer.toJson(instrumentoFinanciero);
		        	
		        	httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		            httpServletResponse.setContentType("application/json; charset=UTF-8");
		            try {
		            	httpServletResponse.getWriter().println(jsonSalida);
		            } catch (IOException ex1) {
		            	System.out.println("Se ha producido un error en update()");
		                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		            }
		    }    	
	        
 		} catch (Exception ex) {
 			httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);             
 		}
               
    }
    
          
    /**
     * BAJA
     * @param httpServletRequest
     * @param httpServletResponse
     * @param ifin_idinsfin
     */
    @RequestMapping(value="/InstrumentoFinanciero/delete/{ifin_idinsfin}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable("ifin_idinsfin") String ifin_idinsfin) {
    	
    	System.out.println("deleteProduct()");
		System.out.println("ifin_idinsfin en deleteProduct(): " + ifin_idinsfin);		
				
		try {
			instrumentoFinanciero = insfinancieroService.getByIfin_Idinsfin(ifin_idinsfin);		
			
			if (instrumentoFinanciero == null) {
	        	System.out.println("Instrumento Financiero con  " + ifin_idinsfin + " no encontrado");
	        	httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);        	            
	        } else {
	        	insfinancieroService.delete(ifin_idinsfin);						
	    		System.out.println("Instrumento Financiero con ifin_idinsfin " + ifin_idinsfin + " borrado correctamente");
	    		
	        	jsonSalida = jsonTransformer.toJson(instrumentoFinanciero);
	        	
	        	httpServletResponse.setStatus(HttpServletResponse.SC_OK);
	            httpServletResponse.setContentType("application/json; charset=UTF-8");
	            try {
	            	httpServletResponse.getWriter().println(jsonSalida);
	            } catch (IOException ex1) {
	            	System.out.println("Se ha producido un error en deleteProduct()");
	                httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            }
	        }	        			
                   
        } catch (Exception ex) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            
        }		
						
    } 
    
}
