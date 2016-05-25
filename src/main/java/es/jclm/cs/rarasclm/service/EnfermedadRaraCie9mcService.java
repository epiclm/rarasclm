/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.dao.EnfermedadRaraCie9mcDao;
import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;


// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadRaraCie9mcService.
 */
@Service
@Transactional
public class EnfermedadRaraCie9mcService {

	/** The datos auxiliares. */
	@Autowired
	private DatosAuxiliaresCacheados datosAuxiliares;

	/** The dao. */
	@Autowired
	private EnfermedadRaraCie9mcDao dao;

	/** The datos cache. */
	@Autowired
	private DatosAuxiliaresCacheados datosCache;

	/** The log. */
	static Log log = LogFactory.getLog(EnfermedadRaraCie9mcService.class.getName());

	/**
	 * Instantiates a new enfermedad rara cie9mc service.
	 */
	public EnfermedadRaraCie9mcService() {

	}

	/**
	 * Save.
	 *
	 * @param enf
	 *            the enf
	 */
	public void save(EnfermedadRaraCie9mc enf) {
		try {
			dao.guardar(enf);
			log.info(String.format("Grabada nueva enfermedad cie9mc %s",enf.toString()));
			datosAuxiliares.setCie9mcs(getAllEnfermedadesRarasCie9mc(false));
			log.info("Actualizado entidad auxiliar CIE9MC");
			log.info("Cache de tablas auxiliares actualizada");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(),ex);
		}
	}
	
	/**
	 * Update.
	 *
	 * @param enf
	 *            the enf
	 */
	public void update(EnfermedadRaraCie9mc enf) {
		try {
			dao.actualizar(enf);
			log.info(String.format("Actualizada enfermedad cie9mc %s",enf.toString()));
			datosAuxiliares.setCie9mcs(getAllEnfermedadesRarasCie9mc(false));
			log.info("Actualizado entidad auxiliar CIE9MC");
			log.info("Cache de tablas auxiliares actualizada");
		}
		catch(Exception ex)
		{
			log.error(ex.getMessage(),ex);
		}
	}

	/**
	 * Gets the enfermedad rara cie9 by id.
	 *
	 * @param cod
	 *            the cod
	 * @return the enfermedad rara cie9 by id
	 */
	public EnfermedadRaraCie9mc getEnfermedadRaraCie9ById(String cod) {
		return dao.getEnfermedadRaraCie9mcById(cod);
	}

	/**
	 * Gets the all enfermedades raras cie9mc.
	 *
	 * @param cache
	 *            the cache
	 * @return the all enfermedades raras cie9mc
	 */
	public List<EnfermedadRaraCie9mc> getAllEnfermedadesRarasCie9mc(boolean cache) {
		if (cache)
			return datosCache.getCie9mcs();
		else
			return dao.getAllEnfermedadesRaraCie9mc();
	}

	/**
	 * Gets the enfermedad rara cie9mc.
	 *
	 * @param cie9
	 *            the cie9
	 * @param cache
	 *            the cache
	 * @return the enfermedad rara cie9mc
	 */
	public EnfermedadRaraCie9mc getEnfermedadRaraCie9mc(String cie9, boolean cache) {
		if (cache)
			return datosCache.getCie9mcsById(cie9);
		else
			return dao.getEnfermedadRaraCie9mcById(cie9);
	}

}
