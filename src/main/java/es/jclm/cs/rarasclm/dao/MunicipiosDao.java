package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.Municipios;

@Repository
public class MunicipiosDao extends BaseEntityDao<Municipios,String> {

	@SuppressWarnings("unchecked")
	public List<Municipios> getMunicipios() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT m FROM Municipios m ORDER BY m.deno");
			return (List<Municipios>) query.list();
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	
}
