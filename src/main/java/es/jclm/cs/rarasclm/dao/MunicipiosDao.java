package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.Municipio;


@Repository
public class MunicipiosDao extends BaseEntityDao<Municipio,String> {

	@SuppressWarnings("unchecked")
	public List<Municipio> getMunicipios() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT m FROM Municipio m ORDER BY m.deno");
			return (List<Municipio>) query.list();
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	
}
