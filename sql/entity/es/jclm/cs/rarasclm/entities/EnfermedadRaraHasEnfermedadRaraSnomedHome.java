package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraHasEnfermedadRaraSnomed.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraSnomed
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraHasEnfermedadRaraSnomedHome {

	private static final Log log = LogFactory.getLog(EnfermedadRaraHasEnfermedadRaraSnomedHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraHasEnfermedadRaraSnomed transientInstance) {
		log.debug("persisting EnfermedadRaraHasEnfermedadRaraSnomed instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraHasEnfermedadRaraSnomed persistentInstance) {
		log.debug("removing EnfermedadRaraHasEnfermedadRaraSnomed instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraSnomed merge(EnfermedadRaraHasEnfermedadRaraSnomed detachedInstance) {
		log.debug("merging EnfermedadRaraHasEnfermedadRaraSnomed instance");
		try {
			EnfermedadRaraHasEnfermedadRaraSnomed result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraSnomed findById(EnfermedadRaraHasEnfermedadRaraSnomedId id) {
		log.debug("getting EnfermedadRaraHasEnfermedadRaraSnomed instance with id: " + id);
		try {
			EnfermedadRaraHasEnfermedadRaraSnomed instance = entityManager
					.find(EnfermedadRaraHasEnfermedadRaraSnomed.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
