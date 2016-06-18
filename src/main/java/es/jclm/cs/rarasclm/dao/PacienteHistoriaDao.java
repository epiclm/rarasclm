package es.jclm.cs.rarasclm.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.PacienteHistoria;
import es.jclm.cs.rarasclm.entities.PacienteHistoriaId;

@Repository
public class PacienteHistoriaDao extends BaseEntityDao<PacienteHistoria,PacienteHistoriaId>{
	
	@SuppressWarnings("unchecked")
	public int getVersion(int paciente) {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT MAX(ph.id.idVersion) FROM PacienteHistoria ph WHERE ph.id.idPaciente=:paciente");
			int ret = (int) query.setParameter("paciente", paciente).uniqueResult();
			if(ret==-1)
				return 1;
			else
				return ret+1;
		} catch (Exception ex) {
			return -1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
}
