package es.jclm.cs.rarasclm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.UserRarasCLM;

@Repository
public class UserRarasCLMDao extends BaseEntityDao<UserRarasCLM, String>{
	
	static Log log = LogFactory.getLog("UserDao");
	
	//private SessionFactory sessionFactory;
	
	
	public UserRarasCLMDao() {

	}

	@SuppressWarnings("unchecked")
	public UserRarasCLM findByUserName(String username) {

		List<UserRarasCLM> users = new ArrayList<UserRarasCLM>();

		Session session = getSessionFactory().openSession();

		users = session.createQuery("SELECT u from UserRarasCLM u where u.username=?")
			.setParameter(0, username).list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
