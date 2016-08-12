package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.entities.EnfermedadCie9mc;



/**
 * The Class EnfermedadRaraCie9mcDao.
 */
@Repository
@Transactional
public class EnfermedadRaraCie9mcDao extends BaseEntityDao<EnfermedadCie9mc,String> {

	public EnfermedadCie9mc getEnfermedadRaraCie9mcById(String cie9mc) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadCie9mc e WHERE e.cie9Id = :cie9mc");
			List<?> res = query.setParameter("cie9mc", cie9mc).list();
			if (res.size() == 1) {
				return (EnfermedadCie9mc) res.get(0);
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
	public List<EnfermedadCie9mc> getAllEnfermedadesRaraCie9mc()
	{
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadCie9mc e ORDER BY e.literal");	
			List<EnfermedadCie9mc> ret = (List<EnfermedadCie9mc>) query.list();
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
