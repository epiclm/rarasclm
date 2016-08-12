/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.entities.EnfermedadCie10;

/**
 * The Class EnfermedadRaraCie10Dao.
 */
@Repository
@Transactional
public class EnfermedadRaraCie10Dao extends BaseEntityDao<EnfermedadCie10,String> {
	
	
	public EnfermedadRaraCie10Dao()
	{
		
	}
	
	public EnfermedadRaraCie10Dao(SessionFactory sf)
	{
		this.sf = sf;
	}

	public EnfermedadCie10 getEnfermedadRaraCie10ById(String cie10) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadCie10 e WHERE e.cie10Id = :cie10");
			List<?> res = query.setParameter("cie10", cie10).list();
			if (res.size() == 1) {
				return (EnfermedadCie10) res.get(0);
			}
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<EnfermedadCie10> getAllEnfermedadesRaraCie10() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadCie10 e ORDER BY e.literal");
			List<EnfermedadCie10> list = (List<EnfermedadCie10>) query.list();
			List<EnfermedadCie10> ret = list;
			return ret;
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}


}
