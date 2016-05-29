package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraCie9mc.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraCie9mcHome {

	private static final Log log = LogFactory.getLog(EnfermedadRaraCie9mcHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraCie9mc transientInstance) {
		log.debug("persisting EnfermedadRaraCie9mc instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraCie9mc persistentInstance) {
		log.debug("removing EnfermedadRaraCie9mc instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraCie9mc merge(EnfermedadRaraCie9mc detachedInstance) {
		log.debug("merging EnfermedadRaraCie9mc instance");
		try {
			EnfermedadRaraCie9mc result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraCie9mc findById(String id) {
		log.debug("getting EnfermedadRaraCie9mc instance with id: " + id);
		try {
			EnfermedadRaraCie9mc instance = entityManager.find(EnfermedadRaraCie9mc.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
