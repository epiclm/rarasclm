package es.jclm.cs.rarasclm.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.dao.CasoRevisionUsuarioDao;
import es.jclm.cs.rarasclm.dao.UnableToSaveException;
import es.jclm.cs.rarasclm.dao.UserRarasCLMDao;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.CasoRevisionUsuario;
import es.jclm.cs.rarasclm.entities.CasoRevisionUsuarioId;
import es.jclm.cs.rarasclm.entities.UserRarasClm;

@Service
public class CasoRevisionService extends BaseCRUDService<CasoRevisionUsuario, CasoRevisionUsuarioId>{
	
	private static final Logger log = LoggerFactory.getLogger(CasoRevisionService.class);
	
	@Autowired 
	private UserRarasCLMDao usuarioDao;

	//Se inyecta en el construcctor
	private CasoRevisionUsuarioDao casoRevisonDao;
	
	@Autowired
	private CasoDao casoDao;
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private CasoRevisionService(CasoRevisionUsuarioDao dao)
	{
		this.baseDao = dao;
		this.casoRevisonDao = dao;
	}
	
	
	public CasoRevisionUsuarioId addCasoRevision(String idCaso, String idUsuario) throws CasoRevisionServiceException {
		CasoRevisionUsuarioId casoRevisionId = new CasoRevisionUsuarioId();
		CasoRevisionUsuario revision = new CasoRevisionUsuario();
		Caso caso;
		UserRarasClm user;
		try {
			caso = casoDao.buscar(idCaso);
		} catch (Exception e) {
			throw new CasoRevisionServiceException(String.format("No existe el caso con id %s",idCaso));
		}
		try {
			user = usuarioDao.buscar(idUsuario);
		} catch (Exception e) {
			throw new CasoRevisionServiceException(String.format("No existe el usuario %s",idUsuario));
		}
		if(usuarioDao.isUserEnabled(idUsuario)) {
			casoRevisionId.setUsuario(idUsuario);
			casoRevisionId.setCaso(idCaso);
			int numRevision = casoRevisonDao.getNumRevision(idUsuario, idCaso);
			casoRevisionId.setNumRev(numRevision+1);
			revision.setId(casoRevisionId);
			revision.setFechaCreacion(new Date());
			revision.setCaso(caso);
			revision.setUserRarasClm(user);
			revision.setRevisado(false);
			try {
				casoRevisonDao.guardar(revision);
				log.info(String.format("añadida revision de caso num. %d de caso %s usuario %s",
						casoRevisionId.getNumRev(),
						casoRevisionId.getCaso(),
						casoRevisionId.getUsuario()));
			} catch (UnableToSaveException e) {
				throw new CasoRevisionServiceException(String.format("No se puede guardar la revisión del caso para el usuario %s",idUsuario));
			}
		} else {
			throw new CasoRevisionServiceException(String.format("No existe el usuario %s o no está habilitado",idUsuario));
		}
		return casoRevisionId;
	}
	
	
	public void setRevidadoCaso(String idCaso, String idUsuario, int numRevision) throws CasoRevisionServiceException {
			CasoRevisionUsuarioId casoRevisionId = new CasoRevisionUsuarioId();
			casoRevisionId.setCaso(idCaso);
			casoRevisionId.setUsuario(idUsuario);
			casoRevisionId.setNumRev(numRevision);
			try {
				CasoRevisionUsuario casoRevision = casoRevisonDao.buscar(casoRevisionId);
				casoRevision.setRevisado(true);
				casoRevisonDao.actualizar(casoRevision);
				log.info(String.format("realizada la revision de caso num. %d de caso %s por el usuario %s",
						casoRevisionId.getNumRev(),
						casoRevisionId.getCaso(),
						casoRevisionId.getUsuario()));
			} catch (Exception e) {
				throw new CasoRevisionServiceException(String.format("No se puede borrar la revision num. %d de caso %s por el usuario %s",
						casoRevisionId.getNumRev(),
						casoRevisionId.getCaso(),
						casoRevisionId.getUsuario()));
			}
		}
	
	
	public void deleteCasoRevision(String idCaso, String idUsuario, int numRevision) throws CasoRevisionServiceException {
		CasoRevisionUsuarioId casoRevisionId = new CasoRevisionUsuarioId();
		casoRevisionId.setCaso(idCaso);
		casoRevisionId.setUsuario(idUsuario);
		casoRevisionId.setNumRev(numRevision);
		try {
			CasoRevisionUsuario casoRevision = casoRevisonDao.buscar(casoRevisionId);
			casoRevisonDao.eliminar(casoRevision);
			log.info(String.format("borrado la revision de caso num. %d de caso %s usuario %s",
					casoRevisionId.getNumRev(),
					casoRevisionId.getCaso(),
					casoRevisionId.getUsuario()));
		} catch (Exception e) {
			throw new CasoRevisionServiceException(String.format("No se puede borrar la revision num. %d de caso %s por el usuario %s",
					casoRevisionId.getNumRev(),
					casoRevisionId.getCaso(),
					casoRevisionId.getUsuario()));
		}
	}
	
	public int getNumUltimaRevision(String idCaso, String idUsuario) {
		return casoRevisonDao.getNumRevision(idUsuario, idCaso);
	}
	
	public boolean isCasoRevisionSet(String idCaso, String idUsuario) {
		return casoRevisonDao.isCasoRevisionUser(idCaso, idUsuario);
	}
	
	public CasoRevisionUsuario getCasoRevision(String idCaso, String idUsuario) {
		return casoRevisonDao.getCasoRevision(idCaso, idUsuario);
	}
	
	public List<CasoRevisionUsuario> getRevisionesPorHacer(String idUsuario, int maxResults, int numPagina) {
		return casoRevisonDao.getRevisionesPorHacer(idUsuario, maxResults, numPagina);
	}
	
	public List<CasoRevisionUsuario> getRevisionesHechas(String idUsuario, int maxResults, int numPagina) {
		return casoRevisonDao.getRevisionesHechas(idUsuario, maxResults, numPagina);
	}
	
}