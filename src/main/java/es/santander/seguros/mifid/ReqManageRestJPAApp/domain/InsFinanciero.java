package es.santander.seguros.mifid.ReqManageRestJPAApp.domain;

import javax.persistence.Entity;

/**
 * @author CMC
 */
@Entity
public class InsFinanciero {

	
	@javax.persistence.Id    
    private String ifin_idinsfin;
    private String ifin_descrip;
    private String ifin_agrupacion;
	
    
    /**
	 * @return the ifin_idinsfin
	 */
	public String getIfin_idinsfin() {
		return ifin_idinsfin;
	}
	
	/**
	 * @param ifin_idinsfin the ifin_idinsfin to set
	 */
	public void setIfin_idinsfin(String ifin_idinsfin) {
		this.ifin_idinsfin = ifin_idinsfin;
	}
	
	/**
	 * @return the ifin_descrip
	 */
	public String getIfin_descrip() {
		return ifin_descrip;
	}
	
	/**
	 * @param ifin_descrip the ifin_descrip to set
	 */
	public void setIfin_descrip(String ifin_descrip) {
		this.ifin_descrip = ifin_descrip;
	}
	
	/**
	 * @return the ifin_agrupacion
	 */
	public String getIfin_agrupacion() {
		return ifin_agrupacion;
	}
	
	/**
	 * @param ifin_agrupacion the ifin_agrupacion to set
	 */
	public void setIfin_agrupacion(String ifin_agrupacion) {
		this.ifin_agrupacion = ifin_agrupacion;
	}
    
	
		
}
