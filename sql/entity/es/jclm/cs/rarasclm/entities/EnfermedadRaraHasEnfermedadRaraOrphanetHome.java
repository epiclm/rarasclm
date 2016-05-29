package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraHasEnfermedadRaraOrphanet.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraOrphanet
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraHasEnfermedadRaraOrphanetHome {

	private static final Log log = LogFactory.getLog(EnfermedadRaraHasEnfermedadRaraOrphanetHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraHasEnfermedadRaraOrphanet transientInstance) {
		log.debug("persisting EnfermedadRaraHasEnfermedadRaraOrphanet instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraHasEnfermedadRaraOrphanet persistentInstance) {
		log.debug("removing EnfermedadRaraHasEnfermedadRaraOrphanet instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraOrphanet merge(EnfermedadRaraHasEnfermedadRaraOrphanet detachedInstance) {
		log.debug("merging EnfermedadRaraHasEnfermedadRaraOrphanet instance");
		try {
			EnfermedadRaraHasEnfermedadRaraOrphanet result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraHasEnfermedadRaraOrphanet findById(EnfermedadRaraHasEnfermedadRaraOrphanetId id) {
		log.debug("getting EnfermedadRaraHasEnfermedadRaraOrphanet instance with id: " + id);
		try {
			EnfermedadRaraHasEnfermedadRaraOrphanet instance = entityManager
					.find(EnfermedadRaraHasEnfermedadRaraOrphanet.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
