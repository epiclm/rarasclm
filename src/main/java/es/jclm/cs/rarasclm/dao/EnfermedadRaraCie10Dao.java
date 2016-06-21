/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie10;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;

/**
 * The Class EnfermedadRaraCie10Dao.
 */
@Repository
@Transactional
public class EnfermedadRaraCie10Dao extends BaseEntityDao<EnfermedadRaraCie10,String> {
	
	
	public EnfermedadRaraCie10Dao()
	{
		
	}
	
	public EnfermedadRaraCie10Dao(SessionFactory sf)
	{
		this.sf = sf;
	}

	public EnfermedadRaraCie10 getEnfermedadRaraCie10ById(String cie10) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadRaraCie10 e WHERE e.cie10Id = :cie10");
			List<?> res = query.setParameter("cie10", cie10).list();
			if (res.size() == 1) {
				return (EnfermedadRaraCie10) res.get(0);
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

	public List<EnfermedadRaraCie10> getAllEnfermedadesRaraCie10() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadRaraCie10 e ORDER BY e.literal");
			return (List<EnfermedadRaraCie10>) query.list();
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}


}
