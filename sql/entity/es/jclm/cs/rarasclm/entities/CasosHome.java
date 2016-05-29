package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Casos.
 * @see es.jclm.cs.rarasclm.entities.Casos
 * @author Hibernate Tools
 */
@Stateless
public class CasosHome {

	private static final Log log = LogFactory.getLog(CasosHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Casos transientInstance) {
		log.debug("persisting Casos instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Casos persistentInstance) {
		log.debug("removing Casos instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Casos merge(Casos detachedInstance) {
		log.debug("merging Casos instance");
		try {
			Casos result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Casos findById(String id) {
		log.debug("getting Casos instance with id: " + id);
		try {
			Casos instance = entityManager.find(Casos.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
