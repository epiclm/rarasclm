package es.jclm.cs.rarasclm.entities;
// Generated 27-may-2016 13:20:44 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRenal.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRenal
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRenalHome {

	private static final Log log = LogFactory.getLog(EnfermedadRenalHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRenal transientInstance) {
		log.debug("persisting EnfermedadRenal instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRenal persistentInstance) {
		log.debug("removing EnfermedadRenal instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRenal merge(EnfermedadRenal detachedInstance) {
		log.debug("merging EnfermedadRenal instance");
		try {
			EnfermedadRenal result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRenal findById(String id) {
		log.debug("getting EnfermedadRenal instance with id: " + id);
		try {
			EnfermedadRenal instance = entityManager.find(EnfermedadRenal.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
