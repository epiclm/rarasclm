package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Ccaa.
 * @see es.jclm.cs.rarasclm.entities.Ccaa
 * @author Hibernate Tools
 */
@Stateless
public class CcaaHome {

	private static final Log log = LogFactory.getLog(CcaaHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Ccaa transientInstance) {
		log.debug("persisting Ccaa instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Ccaa persistentInstance) {
		log.debug("removing Ccaa instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Ccaa merge(Ccaa detachedInstance) {
		log.debug("merging Ccaa instance");
		try {
			Ccaa result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Ccaa findById(String id) {
		log.debug("getting Ccaa instance with id: " + id);
		try {
			Ccaa instance = entityManager.find(Ccaa.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
