package es.jclm.cs.rarasclm.entities;
// Generated 27-may-2016 13:20:44 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraHasEnfermedadRaraCie10.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraCie10
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraHasEnfermedadRaraCie10Home {

	private static final Log log = LogFactory.getLog(EnfermedadRaraHasEnfermedadRaraCie10Home.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraHasEnfermedadRaraCie10 transientInstance) {
		log.debug("persisting EnfermedadRaraHasEnfermedadRaraCie10 instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraHasEnfermedadRaraCie10 persistentInstance) {
		log.debug("removing EnfermedadRaraHasEnfermedadRaraCie10 instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraCie10 merge(EnfermedadRaraHasEnfermedadRaraCie10 detachedInstance) {
		log.debug("merging EnfermedadRaraHasEnfermedadRaraCie10 instance");
		try {
			EnfermedadRaraHasEnfermedadRaraCie10 result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraCie10 findById(EnfermedadRaraHasEnfermedadRaraCie10Id id) {
		log.debug("getting EnfermedadRaraHasEnfermedadRaraCie10 instance with id: " + id);
		try {
			EnfermedadRaraHasEnfermedadRaraCie10 instance = entityManager
					.find(EnfermedadRaraHasEnfermedadRaraCie10.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
