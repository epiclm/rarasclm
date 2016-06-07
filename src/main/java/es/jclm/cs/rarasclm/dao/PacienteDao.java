package es.jclm.cs.rarasclm.dao;

import java.util.Date;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.jclm.cs.rarasclm.entities.Paciente;
@Repository
@SuppressWarnings("unchecked") /*JAVA ugly*/
public class PacienteDao extends BaseEntityDao<Paciente,Integer> {

	public List<Paciente> busqueda(
			String cip,
			String nombre,
			String apellido1,
			String apellido2,
			String provincia,
			String municipio,
			Date fechaNac) {
		Session session = this.sf.openSession();
		//String hql = "select p from Paciente p where p.nombre like :nombre";
		//List<Paciente> pacientes = (List<Paciente>)session.createQuery(hql)
		//		.setParameter("nombre", nombre).list();
		//return pacientes;
		Criteria cr = session.createCriteria(Paciente.class);
		if(cip!=null && !cip.isEmpty())
			cr.add(Restrictions.like("cip", cip+"%"));
		if(nombre!=null && !nombre.isEmpty())
			cr.add(Restrictions.like("nombre", "%"+nombre+"%"));
		if(apellido1!=null && !apellido1.isEmpty())
			cr.add(Restrictions.like("apellido01","%"+apellido1+"%"));
		if(apellido2!=null && !apellido2.isEmpty())
			cr.add(Restrictions.like("apellido02","%"+apellido2+"%"));
		if(provincia!=null && !provincia.isEmpty() && !provincia.equals("99"))
			cr.add(Restrictions.eq("provinciaResidencia", provincia));
		if(municipio!=null && !municipio.isEmpty() && !municipio.equals("99999"))
			cr.add(Restrictions.eq("municipioResidencia", municipio));
		return cr.list();
	}
}
