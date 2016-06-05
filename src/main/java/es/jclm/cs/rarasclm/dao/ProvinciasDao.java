package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.controller.IndexController;
import es.jclm.cs.rarasclm.entities.Municipios;
import es.jclm.cs.rarasclm.entities.Provincias;

@Repository
public class ProvinciasDao extends BaseEntityDao<Provincias,String>{
	

	private static final Logger log = LoggerFactory.getLogger(ProvinciasDao.class);
	
	@SuppressWarnings("unchecked")
	public List<Provincias> getProvincias() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT p FROM Provincias p ORDER BY p.deno");
			return (List<Provincias>) query.list();
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
	public Provincias getProvincia(String idProvincia) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT p FROM Provincias p WHERE ORDER BY p.provincia :provincia");
			query.setParameter("provincia", idProvincia);
			return (Provincias) query.list().get(0);
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
