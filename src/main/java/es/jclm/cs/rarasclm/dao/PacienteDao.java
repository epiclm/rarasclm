package es.jclm.cs.rarasclm.dao;

import java.util.List;

import org.hibernate.Session;

import es.jclm.cs.rarasclm.entities.Paciente;
 
@SuppressWarnings("unchecked") /*JAVA ugly*/
public class PacienteDao extends BaseEntityDao<Paciente,Integer> {

	public List<Paciente> busqueda(
			String nombre,
			String apellido1,
			String apellid2) {
		Session session = this.sf.openSession();
		String hql = "select p from Paciente p where p.nombre like :nombre";
		List<Paciente> pacientes = (List<Paciente>)session.createQuery(hql)
				.setParameter("nombre", nombre).list();
		return pacientes;
	}
	
}
