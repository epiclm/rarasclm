package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Pacientes.
 * @see es.jclm.cs.rarasclm.entities.Pacientes
 * @author Hibernate Tools
 */
@Stateless
public class PacientesHome {

	private static final Log log = LogFactory.getLog(PacientesHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Pacientes transientInstance) {
		log.debug("persisting Pacientes instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Pacientes persistentInstance) {
		log.debug("removing Pacientes instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Pacientes merge(Pacientes detachedInstance) {
		log.debug("merging Pacientes instance");
		try {
			Pacientes result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Pacientes findById(int id) {
		log.debug("getting Pacientes instance with id: " + id);
		try {
			Pacientes instance = entityManager.find(Pacientes.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
