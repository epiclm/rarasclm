package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Municipios.
 * @see es.jclm.cs.rarasclm.entities.Municipios
 * @author Hibernate Tools
 */
@Stateless
public class MunicipiosHome {

	private static final Log log = LogFactory.getLog(MunicipiosHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Municipios transientInstance) {
		log.debug("persisting Municipios instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Municipios persistentInstance) {
		log.debug("removing Municipios instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Municipios merge(Municipios detachedInstance) {
		log.debug("merging Municipios instance");
		try {
			Municipios result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Municipios findById(String id) {
		log.debug("getting Municipios instance with id: " + id);
		try {
			Municipios instance = entityManager.find(Municipios.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
