package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.entities.EstadisticaModelCasosBaseDTCO;

@Service
public class EstadisticasService {
	
	@Autowired
	CasoDao casoDao;

	static Log log = LogFactory.getLog(EstadisticasService.class.getName());
	
	public List<EstadisticaModelCasosBaseDTCO> getBaseDiagnosticoCount() throws ServiceRarasCLMException {
		try {
			return casoDao.getBasesDiagnosticoCount();
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage());
			throw new ServiceRarasCLMException(ex);
		}
	}
	
}
