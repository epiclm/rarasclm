package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraSnomed.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraSnomed
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraSnomedHome {

	private static final Log log = LogFactory.getLog(EnfermedadRaraSnomedHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraSnomed transientInstance) {
		log.debug("persisting EnfermedadRaraSnomed instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraSnomed persistentInstance) {
		log.debug("removing EnfermedadRaraSnomed instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraSnomed merge(EnfermedadRaraSnomed detachedInstance) {
		log.debug("merging EnfermedadRaraSnomed instance");
		try {
			EnfermedadRaraSnomed result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraSnomed findById(long id) {
		log.debug("getting EnfermedadRaraSnomed instance with id: " + id);
		try {
			EnfermedadRaraSnomed instance = entityManager.find(EnfermedadRaraSnomed.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
