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

import es.jclm.cs.rarasclm.dao.EnfermedadRaraCie10Dao;
import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.EnfermedadCie10;


// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadRaraCie10Service.
 */
@Service
@Transactional
public class EnfermedadRaraCie10Service {

	/** The datos auxiliares. */
	@Autowired
	private DatosAuxiliaresCacheados datosAuxiliares;

	/** The dao. */
	@Autowired
	private EnfermedadRaraCie10Dao dao;

	/** The datos cache. */
	@Autowired
	private DatosAuxiliaresCacheados datosCache;

	/** The log. */
	static Log log = LogFactory.getLog(EnfermedadRaraCie10Service.class.getName());

	/**
	 * Instantiates a new enfermedad rara cie10 service.
	 */
	public EnfermedadRaraCie10Service() {

	}

	public void save(EnfermedadCie10 enf) {
		try {
			dao.guardar(enf);
			log.info(String.format("Grabada nueva enfermedad cie10 %s", enf.toString()));
			datosAuxiliares.setCie10s(getAllEnfermedadesRarasCie10(false));
			log.info("Actualizado entidad auxiliar CIE10");
			log.info("Cache de tablas auxiliares actualizada");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}


	public void update(EnfermedadCie10 enf) {
		try {
			dao.actualizar(enf);
			log.info(String.format("Actualizada enfermedad cie9mc %s", enf.toString()));
			datosAuxiliares.setCie10s(getAllEnfermedadesRarasCie10(false));
			log.info("Actualizado entidad auxiliar CIE10");
			log.info("Cache de tablas auxiliares actualizada");
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}


	public EnfermedadCie10 getEnfermedadRaraCie10ById(String cod) {
		return dao.getEnfermedadRaraCie10ById(cod);
	}


	public List<EnfermedadCie10> getAllEnfermedadesRarasCie10(boolean cache) {
		if (cache)
			return datosCache.getCie10s();
		else
			return dao.getAllEnfermedadesRaraCie10();
	}


	public EnfermedadCie10 getEnfermedadRaraCie10(String cie10, boolean cache) {
		if (cache)
			return datosCache.getCie10sById(cie10);
		else
			return dao.getEnfermedadRaraCie10ById(cie10);
	}

}
