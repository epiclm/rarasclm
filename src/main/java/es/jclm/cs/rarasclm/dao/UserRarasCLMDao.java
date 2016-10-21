package es.jclm.cs.rarasclm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import es.jclm.cs.rarasclm.entities.UserRarasClm;

@Repository
@SuppressWarnings("unchecked")
public class UserRarasCLMDao extends BaseEntityDao<UserRarasClm, String>{
	
	private static final Logger log = LoggerFactory.getLogger(UserRarasCLMDao.class);
	
	//private SessionFactory sessionFactory;
	
	
	public UserRarasCLMDao() {

	}
	
	public List<UserRarasClm> getAll() {
		List<UserRarasClm> users = new ArrayList<UserRarasClm>();
		Session session = getSessionFactory().openSession();
		try {
			users = session.createQuery("from UserRarasClm u order by u.seccion, u.apellido01, u.nombre").list();
			return users; }
		catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	
	public UserRarasClm findByUserName(String username) {
		List<UserRarasClm> users = new ArrayList<UserRarasClm>();
		Session session = getSessionFactory().openSession();
		try {
			users = session.createQuery("SELECT u from UserRarasClm u where u.username=?").setParameter(0, username).list();
			if (users.size() > 0) {
				return users.get(0);
			} else {
				log.warn(String.format("El usuario %s no existe",username));
				return null;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	
	public boolean isUserEnabled(String user) {
		List<UserRarasClm> users = new ArrayList<UserRarasClm>();
		Session session = getSessionFactory().openSession();
		try {
			users = session.createQuery("SELECT u from UserRarasClm u where u.username=:user and u.enabled>0")
					.setParameter("user", user).list();
			if (users.size() > 0) {
				return true;
			} else {
				log.warn(String.format("El usuario %s no está habilitado",user));
				return false;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
}
