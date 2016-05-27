package es.jclm.cs.rarasclm.entities;
// Generated 27-may-2016 13:20:44 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Roles.
 * @see es.jclm.cs.rarasclm.entities.Roles
 * @author Hibernate Tools
 */
@Stateless
public class RolesHome {

	private static final Log log = LogFactory.getLog(RolesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Roles transientInstance) {
		log.debug("persisting Roles instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Roles persistentInstance) {
		log.debug("removing Roles instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Roles merge(Roles detachedInstance) {
		log.debug("merging Roles instance");
		try {
			Roles result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Roles findById(int id) {
		log.debug("getting Roles instance with id: " + id);
		try {
			Roles instance = entityManager.find(Roles.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
