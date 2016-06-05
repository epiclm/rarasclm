/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class DatosAuxiliaresCacheados.
 */
public class DatosAuxiliaresCacheados {
	
	/** The log. */
	static Log log = LogFactory.getLog(DatosAuxiliaresCacheados.class.getName());

	/** The cie9mcs. */
	private List<EnfermedadRaraCie9mc> cie9mcs;
	
	/** The cie10s. */
	private List<EnfermedadRaraCie10> cie10s;
	
	/** The raras clm. */
	private List<EnfermedadRara> rarasClm;
	
	/** Municipios. */
	private List<Municipios> municipios;
	
	/**Map de Municipios */
	private Map<String,List<Municipios>> municipiosMapProvincia;
	
	/** Provincias. */
	private List<Provincias> provincias;
	
	/** CCAA */
	private List<Ccaa> ccaas;
	
	
	public DatosAuxiliaresCacheados() {
		log.info("Se instancia cache de datos y tablas auxiliares");
	}

	/**
	 * Gets the cie9mcs.
	 *
	 * @return the cie9mcs
	 */
	public List<EnfermedadRaraCie9mc> getCie9mcs() {
		return cie9mcs;
	}

	/**
	 * Gets the cie10s.
	 *
	 * @return the cie10s
	 */
	public List<EnfermedadRaraCie10> getCie10s() {
		return cie10s;
	}

	/**
	 * Gets the enf raras.
	 *
	 * @return the enf raras
	 */
	public List<EnfermedadRara> getEnfRaras() {
		return rarasClm;
	}

	/**
	 * Sets the cie9mcs.
	 *
	 * @param cie9mcs
	 *            the new cie9mcs
	 */
	public void setCie9mcs(List<EnfermedadRaraCie9mc> cie9mcs) {
		this.cie9mcs = cie9mcs;
	}

	/**
	 * Sets the cie10s.
	 *
	 * @param cie10s
	 *            the new cie10s
	 */
	public void setCie10s(List<EnfermedadRaraCie10> cie10s) {
		this.cie10s = cie10s;
	}

	/**
	 * Sets the enf raras.
	 *
	 * @param raras
	 *            the new enf raras
	 */
	public void setEnfRaras(List<EnfermedadRara> raras) {
		this.rarasClm = raras;
	}

	/**
	 * Gets the cie9mcs by id.
	 *
	 * @param cie9
	 *            the cie9
	 * @return the cie9mcs by id
	 */
	public EnfermedadRaraCie9mc getCie9mcsById(String cie9) {
		for (EnfermedadRaraCie9mc enfermedadRaraCie9mc : cie9mcs) {
			if (enfermedadRaraCie9mc.getCie9Id().equalsIgnoreCase(cie9))
				return enfermedadRaraCie9mc;
		}
		return null;
	}

	/**
	 * Gets the cie10s by id.
	 *
	 * @param cie10
	 *            the cie10
	 * @return the cie10s by id
	 */
	public EnfermedadRaraCie10 getCie10sById(String cie10) {
		for (EnfermedadRaraCie10 enfermedadRaraCie10 : cie10s) {
			if (enfermedadRaraCie10.getCie10Id().equalsIgnoreCase(cie10))
				return enfermedadRaraCie10;
		}
		return null;
	}

	/**
	 * Gets the enf rara by id.
	 *
	 * @param cod
	 *            the cod
	 * @return the enf rara by id
	 */
	public EnfermedadRara getEnfRaraById(String cod) {
		for (EnfermedadRara enfRara : rarasClm) {
			if (enfRara.getEnfermedadRaraId().equalsIgnoreCase(cod))
				return enfRara;
		}
		return null;
	}

	public List<EnfermedadRara> getRarasClm() {
		return rarasClm;
	}

	public void setRarasClm(List<EnfermedadRara> rarasClm) {
		this.rarasClm = rarasClm;
	}

	public List<Municipios> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipios> municipios) {
		this.municipios = municipios;
	}

	public List<Provincias> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincias> provincias) {
		this.provincias = provincias;
	}

	public List<Ccaa> getCcaas() {
		return ccaas;
	}

	public void setCcaas(List<Ccaa> ccaas) {
		this.ccaas = ccaas;
	}

	public Map<String, List<Municipios>> getMunicipiosMapProvincia() {
		return municipiosMapProvincia;
	}

	public void setMunicipiosMapProvincia(Map<String, List<Municipios>> municipiosMapProvincia) {
		this.municipiosMapProvincia = municipiosMapProvincia;
	}
	
	
	
	

}
