package es.jclm.cs.rarasclm.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.dao.CasoHistoriaDao;
import es.jclm.cs.rarasclm.dao.UnableToSaveException;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.CasoHistoria;
import es.jclm.cs.rarasclm.entities.CasoHistoriaId;

@Service
public class CasoService extends BaseCRUDService<Caso, String>{
	
	private static final Logger log = LoggerFactory.getLogger(CasoService.class);
	
	private CasoDao dao;
	
	@Autowired
	CasoHistoriaDao casoHistoriaDao;
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private CasoService(CasoDao dao)
	{
		this.baseDao = dao;
		this.dao = dao;
	}
	
	
	
	public void saveHistoria(Caso caso) {
		try {
			saveHistoria(caso, -1);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
		}
	}
	
	/////////////////////////////////////////////////////////
	// Para conocer en el histórico las historias borraradas
	// se guarda con id de caso 0
	/////////////////////////////////////////////////////////
	public void saveHistoriaBorrada(Caso caso) {
		try {
			saveHistoria(caso, 0);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
		}
	}

	private void saveHistoria(Caso caso,int version) throws ServiceRarasCLMException {
		
		CasoHistoriaId id = new CasoHistoriaId();
		
		if(version<0) {
			id.setIdVersion(casoHistoriaDao.getVersion(caso.getIdCaso()));
			id.setIdCaso(caso.getIdCaso());
		} else {
			id.setIdVersion(version);
			id.setIdCaso(caso.getIdCaso());
		}
		
		String enfRaraId = caso.getEnfermedadRara()!=null ? caso.getEnfermedadRara().getEnfermedadRaraId() : "";
		int seccion = caso.getSeccion()!=null ? caso.getSeccion().getIdSeccion() : 0;
		
		id.setIdCaso(caso.getIdCaso());
		CasoHistoria ch = new CasoHistoria(id,
				caso.getPaciente().getIdPaciente(), 
				caso.getNumCaso(), 
				enfRaraId, 
				caso.getDeclarada(), 
				caso.getUsuarioDeclara(), 
				caso.getFechaDeclara(), 
				caso.getLiteral(), 
				caso.getJucioClinico(), 
				caso.getObservaciones(), 
				caso.getHospital(), 
				caso.getNhc(), 
				caso.getBaseDiagnostico(), 
				caso.getFuenteInformacion(), 
				caso.getFuentePacientesA(), 
				caso.getFuenteCmbdC(), 
				caso.getFuenteDefcongD(), 
				caso.getFuenteEdoE(), 
				caso.getFuenteIsocialesG(), 
				caso.getFuenteMhuerfH(), 
				caso.getFuenteMetabolN(), 
				caso.getFuenteRinvI(), 
				caso.getFuenteRmortM(), 
				caso.getFuenteRcancerT(), 
				caso.getFuenteRenalR(), 
				caso.getFuenteHcPrimariaV(), 
				caso.getFuenteHcEspecializadaU(), 
				caso.getFuenteHcPrimariaMasivaP(), 
				caso.getFuenteHcEspecializadaMasivaQ(), 
				caso.getFuenteOtrosO(), 
				caso.getFechaInicioSintomas(), 
				caso.getFechaDeteccion(), 
				caso.getFechaDiagnostico(), 
				caso.getCodCie9mc(), 
				caso.getCodCie10(),
				caso.getCodSnomed(), 
				caso.getCodOmin(), 
				caso.getCodEdta(), 
				caso.getCodOtros(), 
				caso.getCodOtroDeno(), 
				caso.getTratamiento(), 
				caso.getFamiliaresEnfermedadesRaras(), 
				caso.getOtrasEnfermedadesCronicas(), 
				caso.getEnfermedadesCronicas(), 
				caso.getFechahoraCreacion(), 
				caso.getUsuarioCreacion(), 
				caso.getFechahoraModificacion(), 
				caso.getUsuarioModificacion(),
				seccion);	
		try {
			casoHistoriaDao.guardar(ch);
		} catch (UnableToSaveException ex) {
			log.error(ex.getMessage(),ex);
		}

	}
	

}
