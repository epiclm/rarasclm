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
import es.jclm.cs.rarasclm.entities.EnfermedadCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadCodigoLiteral;
import es.jclm.cs.rarasclm.util.DatosAuxiliaresCacheados;


// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadRaraCie9mcService.
 */
@Service
@Transactional
public class EnfermedadRaraCie9mcService {

	@Autowired
	private DatosAuxiliaresCacheados datosAuxiliares;

	@Autowired
	private EnfermedadRaraCie9mcDao dao;

	@Autowired
	private DatosAuxiliaresCacheados datosCache;

	static Log log = LogFactory.getLog(EnfermedadRaraCie9mcService.class.getName());


	public EnfermedadRaraCie9mcService() {

	}


	public void save(EnfermedadCie9mc enf) {
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
	

	public void update(EnfermedadCie9mc enf) {
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


	public EnfermedadCie9mc getEnfermedadRaraCie9ById(String cod) {
		return dao.getEnfermedadRaraCie9mcById(cod);
	}


	public List<EnfermedadCie9mc> getAllEnfermedadesRarasCie9mc(boolean cache) {
		if (cache)
			return datosCache.getCie9mcs();
		else
			return dao.getAllEnfermedadesRaraCie9mc();
	}
	
	

	public EnfermedadCie9mc getEnfermedadRaraCie9mc(String cie9, boolean cache) {
		if (cache)
			return datosCache.getCie9mcsById(cie9);
		else
			return dao.getEnfermedadRaraCie9mcById(cie9);
	}
	
	public List<EnfermedadCodigoLiteral> getListCodLiteral() {
		return datosCache.getCodLiteralesCie9mc();
	}

}
