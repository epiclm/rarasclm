package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraCie10.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraCie10
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraCie10Home {

	private static final Log log = LogFactory.getLog(EnfermedadRaraCie10Home.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraCie10 transientInstance) {
		log.debug("persisting EnfermedadRaraCie10 instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraCie10 persistentInstance) {
		log.debug("removing EnfermedadRaraCie10 instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraCie10 merge(EnfermedadRaraCie10 detachedInstance) {
		log.debug("merging EnfermedadRaraCie10 instance");
		try {
			EnfermedadRaraCie10 result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraCie10 findById(String id) {
		log.debug("getting EnfermedadRaraCie10 instance with id: " + id);
		try {
			EnfermedadRaraCie10 instance = entityManager.find(EnfermedadRaraCie10.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
