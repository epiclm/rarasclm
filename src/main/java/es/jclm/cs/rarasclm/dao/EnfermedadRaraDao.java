/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
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

/**
 * The Class EnfermedadRara.
 */
@Repository
@Transactional
public class EnfermedadRaraDao extends BaseEntityDao<EnfermedadRara,String> {

	public EnfermedadRara getEnfermedadRaraById(String enfRaraId) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadRara e WHERE e.enfermedadRaraId = :enfRaraId");
			List<?> res = query.setParameter("enfRaraId", enfRaraId).list();
			if (res.size() == 1) {
				return (EnfermedadRara) res.get(0);
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

	public List<EnfermedadRara> getAllEnfermedadesRaras() {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT e FROM EnfermedadRara e");
			return (List<EnfermedadRara>) query.list();
		} catch (Exception ex) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public void update(EnfermedadRara enf) {
		try {
			guardar(enf);
		} catch (UnableToSaveException e) {
			e.printStackTrace();
		}
	}

}