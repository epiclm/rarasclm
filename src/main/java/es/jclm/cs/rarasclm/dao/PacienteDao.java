package es.jclm.cs.rarasclm.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.entities.Paciente;
@Repository
@Transactional
@SuppressWarnings("unchecked") /*JAVA ugly*/
public class PacienteDao extends BaseEntityDao<Paciente,Integer> {

	public List<Paciente> busqueda(
			String nombre,
			String apellido1,
			String apellid2) {
		Session session = this.sf.openSession();
		//String hql = "select p from Paciente p where p.nombre like :nombre";
		//List<Paciente> pacientes = (List<Paciente>)session.createQuery(hql)
		//		.setParameter("nombre", nombre).list();
		//return pacientes;
		Criteria cr = session.createCriteria(Paciente.class);
		return cr.add(Restrictions.like("nombre", "%"+nombre+"%")).list();
	}
}
