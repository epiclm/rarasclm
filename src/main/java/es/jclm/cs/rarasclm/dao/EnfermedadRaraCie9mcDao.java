package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie10;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraHasEnfermedadRaraCie9mcId;

/**
 * The Class EnfermedadRaraCie9mcDao.
 */
@Repository
@Transactional
public class EnfermedadRaraCie9mcDao extends BaseEntityDao<EnfermedadRaraCie9mc,String> {

	public EnfermedadRaraCie9mc getEnfermedadRaraCie9mcById(String cie9mc) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadRaraCie9mc e WHERE e.cie9Id = :cie9mc");
			List<?> res = query.setParameter("cie9mc", cie9mc).list();
			if (res.size() == 1) {
				return (EnfermedadRaraCie9mc) res.get(0);
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
	
	
	public List<EnfermedadRaraCie9mc> getAllEnfermedadesRaraCie9mc()
	{
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadRaraCie9mc e");
			return (List<EnfermedadRaraCie9mc>) query.list();
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

//
//	public void update(EnfermedadRaraCie9mc enf) {
//		Session s = getSessionFactory().openSession();
//		Transaction tx = s.beginTransaction();
//		EnfermedadRaraCie9mc enfPer =
//				(EnfermedadRaraCie9mc) s.get(EnfermedadRaraCie9mc.class, enf.getCie9Id());
//		enfPer.setUrl(enfPer.getUrl());
//		enfPer.setNotas(enf.getNotas());
//		enfPer.setLiteral(enf.getLiteral());
//		s.update(enfPer);
//		tx.commit();
//		s.close();
//	}

}
