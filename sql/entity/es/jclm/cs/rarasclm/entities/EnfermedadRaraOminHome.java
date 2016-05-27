package es.jclm.cs.rarasclm.entities;
// Generated 27-may-2016 13:20:44 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraOmin.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraOmin
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraOminHome {

	private static final Log log = LogFactory.getLog(EnfermedadRaraOminHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraOmin transientInstance) {
		log.debug("persisting EnfermedadRaraOmin instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraOmin persistentInstance) {
		log.debug("removing EnfermedadRaraOmin instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraOmin merge(EnfermedadRaraOmin detachedInstance) {
		log.debug("merging EnfermedadRaraOmin instance");
		try {
			EnfermedadRaraOmin result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraOmin findById(String id) {
		log.debug("getting EnfermedadRaraOmin instance with id: " + id);
		try {
			EnfermedadRaraOmin instance = entityManager.find(EnfermedadRaraOmin.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
