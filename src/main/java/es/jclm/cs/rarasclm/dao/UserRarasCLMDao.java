package es.jclm.cs.rarasclm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.UserRarasClm;

@Repository
public class UserRarasCLMDao extends BaseEntityDao<UserRarasClm, String>{
	
	static Log log = LogFactory.getLog("UserDao");
	
	//private SessionFactory sessionFactory;
	
	
	public UserRarasCLMDao() {

	}

	@SuppressWarnings("unchecked")
	public UserRarasClm findByUserName(String username) {

		List<UserRarasClm> users = new ArrayList<UserRarasClm>();

		Session session = getSessionFactory().openSession();

		users = session.createQuery("SELECT u from UserRarasClm u where u.username=?")
			.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
