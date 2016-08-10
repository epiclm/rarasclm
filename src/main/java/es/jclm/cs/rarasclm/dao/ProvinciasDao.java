package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.controller.IndexController;
import es.jclm.cs.rarasclm.entities.Municipio;
import es.jclm.cs.rarasclm.entities.Provincia;

@Repository
public class ProvinciasDao extends BaseEntityDao<Provincia,String>{
	

	private static final Logger log = LoggerFactory.getLogger(ProvinciasDao.class);
	
	@SuppressWarnings("unchecked")
	public List<Provincia> getProvincias() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT p FROM Provincia p ORDER BY p.deno");
			return (List<Provincia>) query.list();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public Provincia getProvincia(String idProvincia) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT p FROM Provincia p WHERE p.provincia = :provincia ORDER BY p.provincia");
			query.setParameter("provincia", idProvincia);
			return (Provincia) query.list().get(0);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
}
