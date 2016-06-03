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
import es.jclm.cs.rarasclm.dao.EnfermedadRaraHasEnfermedadRaraCie10Dao;
import es.jclm.cs.rarasclm.dao.EnfermedadRaraHasEnfermedadRaraCie9mcDao;
import es.jclm.cs.rarasclm.dao.UnableToSaveException;
import es.jclm.cs.rarasclm.entities.DatosAuxiliaresCacheados;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraCie10;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraCie9mcId;

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

	@Autowired
	private EnfermedadRaraHasEnfermedadRaraCie9mcDao daoHasCie9mc;

	@Autowired
	private EnfermedadRaraHasEnfermedadRaraCie10Dao daoHasCie10;

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

	/**
	 * Save.
	 *
	 * @param enf
	 *            the enf
	 */
	public void save(EnfermedadRara enf) {
		try {
			dao.update(enf);
			actualizaCache(enf.getEnfermedadRaraId());
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}

	/**
	 * Update.
	 *
	 * @param enf
	 *            the enf
	 */
	public void update(EnfermedadRara enf) throws ServiceRarasCLMException {
		try {
			dao.actualizar(enf);
			actualizaCache(enf.getEnfermedadRaraId());
		} catch (Exception ex) {
			log.error(String.format("ERROR CAPA SERVICIO %s",ex.getMessage()), ex);
			throw new ServiceRarasCLMException(ex.getMessage());
		}
	}

	/**
	 * Gets the all enfermedades raras.
	 *
	 * @param cache
	 *            the cache
	 * @return the all enfermedades raras
	 */
	public List<EnfermedadRara> getAllEnfermedadesRaras(boolean cache) {
		if (cache)
			return datosCache.getEnfRaras();
		else
			return dao.getAllEnfermedadesRaras();
	}

	/**
	 * Gets the enfermedad rara by id.
	 *
	 * @param cod
	 *            the cod
	 * @return the enfermedad rara by id
	 */
	public EnfermedadRara getEnfermedadRaraById(String cod) {
		return dao.getEnfermedadRaraById(cod);
	}

	/**
	 * Gets the enfermedad rara by id.
	 *
	 * @param cod
	 *            the cod
	 * @param cache
	 *            the cache
	 * @return the enfermedad rara by id
	 */
	public EnfermedadRara getEnfermedadRaraById(String cod, boolean cache) {
		if (cache)
			return datosCache.getEnfRaraById(cod);
		else
			return dao.getEnfermedadRaraById(cod);
	}

	public void updateHasCie9mc(String enfRaraId, String cie9mcId, String notas, int numPrioridad)
			throws ServiceRarasCLMException {
		try {
			EnfermedadRaraHasEnfermedadRaraCie9mcId id = new EnfermedadRaraHasEnfermedadRaraCie9mcId(enfRaraId,
					cie9mcId);
			EnfermedadRaraHasEnfermedadRaraCie9mc hasCie9 = daoHasCie9mc.buscar(id);
			if (hasCie9 != null) {
				hasCie9.setNotas(notas);
				hasCie9.setNumPrioridad(numPrioridad);
				daoHasCie9mc.actualizar(hasCie9);
			} else {
				throw new ServiceRarasCLMException(String.format(
						"Error al modificar enfermedad rara %s asociada a CIE9 %s no existe relacion previa para modificar",
						enfRaraId, cie9mcId));
			}
		} catch (Exception ex) {
			log.error(String.format("%s", ex.getMessage()), ex);
			throw new ServiceRarasCLMException(String.format("Error al modificar enfermedad rara %s asociada a CIE9 %s - %s",
					enfRaraId, cie9mcId, ex.getMessage()));
		}
	}

	public boolean addHasCie10(String enfRaraId, EnfermedadRaraHasEnfermedadRaraCie10 hasCie10) {
		try {
			if (daoHasCie10.buscar(hasCie10.getId()) == null) {
				daoHasCie10.guardar(hasCie10);
				actualizaCache(enfRaraId);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean addHasCie9mc(String enfRaraId, EnfermedadRaraHasEnfermedadRaraCie9mc hasCie9mc) {
		try {
			EnfermedadRaraHasEnfermedadRaraCie9mc cie9 = daoHasCie9mc.buscar(hasCie9mc.getId());
			if (cie9 == null) {
				daoHasCie9mc.guardar(hasCie9mc);
				actualizaCache(enfRaraId);
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error(ex.getMessage(), ex);
		}
		return false;
	}

	public boolean deleteHasCie9mc(String enfRaraId, String cie9mcId) throws ServiceRarasCLMException {
		try {
			EnfermedadRara enfRara = getEnfermedadRaraById(enfRaraId);
			EnfermedadRaraHasEnfermedadRaraCie9mc hasCie9mc = daoHasCie9mc
					.buscar(new EnfermedadRaraHasEnfermedadRaraCie9mcId(enfRaraId, cie9mcId));
			if (hasCie9mc != null) {
				daoHasCie9mc.eliminar(hasCie9mc);
				return true;
			} else {
				String mensaje = String.format("Error al desasociar la enfermedad cie9mc %s de la enfermedad %s %s",
						cie9mcId, enfRara.getEnfermedadRaraId(), enfRara.getLiteral());
				log.error(String.format("ERROR EN LA CAPA DE SERVICIO - %s", mensaje));
				throw new ServiceRarasCLMException(mensaje);
			}
		} catch (Exception ex) {
			String mensaje = String.format("Error al desasociar la enfermedad cie9mc %s de la enfermedad %s ", cie9mcId,
					enfRaraId);
			log.error(String.format("ERROR EN LA CAPA DE SERVICIO %s - %s", mensaje, ex.getMessage()));
			throw new ServiceRarasCLMException(mensaje);
		}
	}
}
