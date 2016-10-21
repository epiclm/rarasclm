package es.jclm.cs.rarasclm.dao;

import java.util.GregorianCalendar;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import es.jclm.cs.rarasclm.entities.Paciente;
@Repository
@SuppressWarnings("unchecked")
public class PacienteDao extends BaseEntityDao<Paciente,Integer> {

	public List<Paciente> busqueda(
			int seccion,
			String cip,
			String nombre,
			String apellido1,
			String apellido2,
			String provincia,
			String municipio,
			int anioNac,
			String sexo) {
		Session session = this.sf.openSession();
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
		if(anioNac>1800) {
			//java ugly complex
			GregorianCalendar gcFrom = new GregorianCalendar();
			GregorianCalendar gcTo = new GregorianCalendar();
			gcFrom.set(GregorianCalendar.YEAR, anioNac);
			gcFrom.set(GregorianCalendar.MONTH, 1);
			gcFrom.set(GregorianCalendar.DAY_OF_MONTH,1);
			gcTo.set(GregorianCalendar.YEAR, anioNac);
			gcTo.set(GregorianCalendar.MONTH, 12);
			gcTo.set(GregorianCalendar.DAY_OF_MONTH,31);
			cr.add(Restrictions.ge("fechaNacimiento",gcFrom.getTime()));
			cr.add(Restrictions.le("fechaNacimiento",gcTo.getTime()));
		}
		if(sexo!=null && !sexo.isEmpty())
			cr.add(Restrictions.eq("sexo", sexo));
		return cr.list();
	}
}
