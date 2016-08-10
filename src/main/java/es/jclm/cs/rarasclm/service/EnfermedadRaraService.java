/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 * 
 *  
 */
package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.EnfermedadRaraDao;
import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;


// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadRaraService.
 */
@Service
public class EnfermedadRaraService {

	/** The datos auxiliares. */
	@Autowired
	private DatosAuxiliaresCacheados datosAuxiliares;

	/** The dao. */
	@Autowired
	private EnfermedadRaraDao dao;



	/** The datos cache. */
	@Autowired
	private DatosAuxiliaresCacheados datosCache;

	/** The log. */
	static Log log = LogFactory.getLog(EnfermedadRaraService.class.getName());

	/**
	 * Instantiates a new enfermedad rara service.
	 */
	public EnfermedadRaraService() {

	}

	private void actualizaCache(String id) {
		log.info(String.format("Grabada nueva enfermedad RARA %s", id));
		datosAuxiliares.setEnfRaras(getAllEnfermedadesRaras(false));
		log.info("Actualizado entidad auxiliar Enfermedades Raras");
		log.info("Cache de tablas auxiliares actualizada");
	}


	public void save(EnfermedadRara enf) {
		try {
			dao.update(enf);
			actualizaCache(enf.getEnfermedadRaraId());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}


	public void update(EnfermedadRara enf) throws ServiceRarasCLMException {
		try {
			dao.actualizar(enf);
			actualizaCache(enf.getEnfermedadRaraId());
		} catch (Exception ex) {
			log.error(String.format("ERROR CAPA SERVICIO %s",ex.getMessage()), ex);
			throw new ServiceRarasCLMException(ex.getMessage());
		}
	}


	public List<EnfermedadRara> getAllEnfermedadesRaras(boolean cache) {
		if (cache)
			return datosCache.getEnfRaras();
		else
			return dao.getAllEnfermedadesRaras();
	}


	public EnfermedadRara getEnfermedadRaraById(String cod) {
		return dao.getEnfermedadRaraById(cod);
	}


	public EnfermedadRara getEnfermedadRaraById(String cod, boolean cache) {
		if (cache)
			return datosCache.getEnfRaraById(cod);
		else
			return dao.getEnfermedadRaraById(cod);
	}
}
