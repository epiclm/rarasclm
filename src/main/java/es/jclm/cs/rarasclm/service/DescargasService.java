package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.dao.DatosExplotacionDao;
import es.jclm.cs.rarasclm.entities.VPacienteCaso;

@Service
public class DescargasService {
	
	private static final Logger log = LoggerFactory.getLogger(DescargasService.class);
	
	@Autowired
	private DatosExplotacionDao datosExplotacionDao;
	
	public List<VPacienteCaso> getDatosExplotacion() throws ServiceRarasCLMException{
		try {
			return datosExplotacionDao.getDatosExplotacionJDBC();
		} catch(Exception ex) {
			log.error(ex.getMessage());
			throw new ServiceRarasCLMException(ex.getMessage());
		}
		
	}
	
}
