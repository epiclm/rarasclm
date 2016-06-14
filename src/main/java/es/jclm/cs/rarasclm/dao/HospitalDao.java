package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.Hospital;

@Repository
@SuppressWarnings("unchecked")
public class HospitalDao extends BaseEntityDao<Hospital,String>{

	private static final Logger log = LoggerFactory.getLogger(HospitalDao.class);
	
	@SuppressWarnings("unchecked")
	public List<Hospital> getHospitales() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT h FROM Hospital h ORDER BY h.idHospital");
			return (List<Hospital>) query.list();
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
}
