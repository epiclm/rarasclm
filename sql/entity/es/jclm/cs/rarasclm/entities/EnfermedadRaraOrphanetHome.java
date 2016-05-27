package es.jclm.cs.rarasclm.entities;
// Generated 27-may-2016 13:20:44 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class EnfermedadRaraOrphanet.
 * @see es.jclm.cs.rarasclm.entities.EnfermedadRaraOrphanet
 * @author Hibernate Tools
 */
@Stateless
public class EnfermedadRaraOrphanetHome {

	private static final Log log = LogFactory.getLog(EnfermedadRaraOrphanetHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EnfermedadRaraOrphanet transientInstance) {
		log.debug("persisting EnfermedadRaraOrphanet instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(EnfermedadRaraOrphanet persistentInstance) {
		log.debug("removing EnfermedadRaraOrphanet instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public EnfermedadRaraOrphanet merge(EnfermedadRaraOrphanet detachedInstance) {
		log.debug("merging EnfermedadRaraOrphanet instance");
		try {
			EnfermedadRaraOrphanet result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EnfermedadRaraOrphanet findById(String id) {
		log.debug("getting EnfermedadRaraOrphanet instance with id: " + id);
		try {
			EnfermedadRaraOrphanet instance = entityManager.find(EnfermedadRaraOrphanet.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
