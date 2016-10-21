package es.jclm.cs.rarasclm.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
/**
 * The Class AbstractEntityDao.
 *
 * @param <T>
 *            the generic type
 */
@Transactional
public class BaseEntityDao<Entity, K extends Serializable> {

	static Log log = LogFactory.getLog("BaseEntityDao");

	@Autowired
	protected SessionFactory sf;
	
	public Class<Entity> domainClass = getDomainClass();
	//private Session session;

	public void setClazz(Class<Entity> clazzSet) {
	}

	protected SessionFactory getSessionFactory() {
		return sf;
	}


	@SuppressWarnings("rawtypes")
	protected Class getDomainClass() {
		if (domainClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			domainClass = (Class) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}

//	private Session getHibernateTemplate() {
//		session = sf.openSession();
//		session.beginTransaction();
//		return session;
//	}

	public Entity buscar(K id) throws NotFoundException {
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			Entity returnValue = (Entity) s.get(domainClass, id);
			tx.commit();
			return returnValue;
		} catch (Exception ex) {
			throw new NotFoundException("Error al buscar la entidad");
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
	}

	public void actualizar(Entity t) throws UnableToSaveException {
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (HibernateException e) {
			throw new UnableToSaveException(e);
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
	}

	public void guardar(Entity t) throws UnableToSaveException {
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			s.save(t);
			tx.commit();
		} catch (HibernateException e) {
			throw new UnableToSaveException(e);
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
	}

	public void eliminar(Entity t) throws UnableToSaveException {
		Session s = sf.openSession();
		try {
			Transaction tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception ex) {
			String mensaje = String.format("Error al intentar eliminar %s - %s", t.toString(), ex.getMessage());
			log.error(String.format("ERROR CAPA DAO %s", mensaje));
			throw new UnableToSaveException(mensaje);
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
	}
}