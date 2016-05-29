package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraHasEnfermedadRaraCie9mc.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraCie9mc
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraHasEnfermedadRaraCie9mcHome {

	private static final Log log = LogFactory.getLog(EnfermedadRaraHasEnfermedadRaraCie9mcHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraHasEnfermedadRaraCie9mc transientInstance) {
		log.debug("persisting EnfermedadRaraHasEnfermedadRaraCie9mc instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraHasEnfermedadRaraCie9mc persistentInstance) {
		log.debug("removing EnfermedadRaraHasEnfermedadRaraCie9mc instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraCie9mc merge(EnfermedadRaraHasEnfermedadRaraCie9mc detachedInstance) {
		log.debug("merging EnfermedadRaraHasEnfermedadRaraCie9mc instance");
		try {
			EnfermedadRaraHasEnfermedadRaraCie9mc result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraCie9mc findById(EnfermedadRaraHasEnfermedadRaraCie9mcId id) {
		log.debug("getting EnfermedadRaraHasEnfermedadRaraCie9mc instance with id: " + id);
		try {
			EnfermedadRaraHasEnfermedadRaraCie9mc instance = entityManager
					.find(EnfermedadRaraHasEnfermedadRaraCie9mc.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
