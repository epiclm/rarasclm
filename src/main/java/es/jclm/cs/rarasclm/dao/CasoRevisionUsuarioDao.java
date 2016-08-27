package es.jclm.cs.rarasclm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.CasoRevisionUsuario;
import es.jclm.cs.rarasclm.entities.CasoRevisionUsuarioId;

@Repository
public class CasoRevisionUsuarioDao extends BaseEntityDao<CasoRevisionUsuario,CasoRevisionUsuarioId> {
	
	private static final Logger log = LoggerFactory.getLogger(CasoRevisionUsuarioDao.class);

	
	public List<CasoRevisionUsuario> getRevisionesPorHacer(String idUsuario,int maxResults,int numPagina) {
		String sHql = "from CasoRevisionUsuario r where r.userRarasClm.username=:username and r.revisado=false and r.fechaCreacion!=null order by r.fechaCreacion desc";
		Session session = getSessionFactory().openSession();
		List<CasoRevisionUsuario> ret = new ArrayList<CasoRevisionUsuario>();
		try {
			Query q = session.createQuery(sHql);
			q.setParameter("username",idUsuario);
			q.setMaxResults(maxResults);
			q.setFirstResult(maxResults*(numPagina-1));
			return (List<CasoRevisionUsuario>) q.list();
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	public List<CasoRevisionUsuario> getRevisionesHechas(String idUsuario,int maxResults,int numPagina) {
		String sHql = "from CasoRevisionUsuario r where r.userRarasClm.username=:username and r.revisado=true and r.fechaRevision!=null order by r.fechaRevision desc";
		Session session = getSessionFactory().openSession();
		List<CasoRevisionUsuario> ret = new ArrayList<CasoRevisionUsuario>();
		try {
			Query q = session.createQuery(sHql);
			q.setParameter("username",idUsuario);
			q.setMaxResults(maxResults);
			q.setFirstResult(maxResults*(numPagina-1));
			return (List<CasoRevisionUsuario>) q.list();
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public boolean isCasoRevisionUser(String idCaso, String idUsuario) {
		String sHql = "from CasoRevisionUsuario r where r.userRarasClm.username = :username and r.revisado=false and r.caso.idCaso = :idCaso";
		Session session = getSessionFactory().openSession();
		List<CasoRevisionUsuario> ret = new ArrayList<CasoRevisionUsuario>();
		try {
			Query q = session.createQuery(sHql);
			q.setParameter("username",idUsuario);
			q.setParameter("idCaso", idCaso);
			List<CasoRevisionUsuario> revisionesCasos = (List<CasoRevisionUsuario>) q.list();
			if(revisionesCasos.size()>0)
				return true;
			else
				return false;
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public CasoRevisionUsuario getCasoRevision(String idCaso, String idUsuario) {
		String sHql = "from CasoRevisionUsuario r where r.userRarasClm.username = :username and r.revisado=false and r.caso.idCaso = :idCaso";
		Session session = getSessionFactory().openSession();
		List<CasoRevisionUsuario> ret = new ArrayList<CasoRevisionUsuario>();
		try {
			Query q = session.createQuery(sHql);
			q.setParameter("username",idUsuario);
			q.setParameter("idCaso", idCaso);
			List<CasoRevisionUsuario> revisionesCasos = (List<CasoRevisionUsuario>) q.list();
			if(revisionesCasos.size()>0)
				return revisionesCasos.get(0);
			else
				return null;
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	public int getNumRevision(String idUsuario, String idCaso) {
		String sHql = "select max(c.id.numRev) from CasoRevisionUsuario c where c.userRarasClm.username=:username and c.caso.idCaso = :idCaso";
		Session session = getSessionFactory().openSession();
		List<CasoRevisionUsuario> ret = new ArrayList<CasoRevisionUsuario>();
		try {
			Query q = session.createQuery(sHql);
			q.setString("username",idUsuario);
			q.setString("idCaso", idCaso);
			//Que cosa más fea java e hibernate!!!
			Object resultado = q.uniqueResult();
			if(resultado==null)
				return 0;
			else
				return (int)q.uniqueResult();
		} catch(Exception ex) {
			log.error(ex.getMessage(),ex);
			return -1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	
}
