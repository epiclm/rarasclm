package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Provincias.
 * @see es.jclm.cs.rarasclm.entities.Provincias
 * @author Hibernate Tools
 */
@Stateless
public class ProvinciasHome {

	private static final Log log = LogFactory.getLog(ProvinciasHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Provincias transientInstance) {
		log.debug("persisting Provincias instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Provincias persistentInstance) {
		log.debug("removing Provincias instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Provincias merge(Provincias detachedInstance) {
		log.debug("merging Provincias instance");
		try {
			Provincias result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Provincias findById(String id) {
		log.debug("getting Provincias instance with id: " + id);
		try {
			Provincias instance = entityManager.find(Provincias.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}