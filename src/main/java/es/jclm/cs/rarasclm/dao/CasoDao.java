package es.jclm.cs.rarasclm.dao;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import es.jclm.cs.rarasclm.config.ConfiguracionRarasCLM;
import es.jclm.cs.rarasclm.entities.Caso;


@Repository
@SuppressWarnings("unchecked")
public class CasoDao extends BaseEntityDao<Caso,String>{
	
	@Autowired
	private ConfiguracionRarasCLM config;
	
	public List<Caso> busqueda(
			String seccion,
			String cip,
			String nombre,
			String apellido1,
			String apellido2,
			String provincia,
			String municipio,
			int anioNac,
			String sexo,
			String cie9MC,
			String cie10,
			String enfraraCLM,
			Byte baseDiagnostico) {
		
		Session session = this.sf.openSession();
		Criteria cr = session.createCriteria(Caso.class);
		cr.setMaxResults(config.getMaxResultadosBusqueda()); // VARIABLE DE CONFIGURACIÓN
		
		//Creamos un criteria para filtrar paciente ONE TO MANY
		Criteria crPaciente = cr.createCriteria("paciente");
		if(cip!=null && !cip.isEmpty())
			crPaciente.add(Restrictions.like("cip", cip+"%"));
		if(nombre!=null && !nombre.isEmpty())
			crPaciente.add(Restrictions.like("nombre", "%"+nombre+"%"));
		if(apellido1!=null && !apellido1.isEmpty())
			crPaciente.add(Restrictions.like("apellido01","%"+apellido1+"%"));
		if(apellido2!=null && !apellido2.isEmpty())
			crPaciente.add(Restrictions.like("apellido02","%"+apellido2+"%"));
		if(provincia!=null && !provincia.isEmpty() && !provincia.equals("99"))
			crPaciente.add(Restrictions.eq("provinciaResidencia", provincia));
		if(municipio!=null && !municipio.isEmpty() && !municipio.equals("99999"))
			crPaciente.add(Restrictions.eq("municipioResidencia", municipio));
		
		if(anioNac>1800) {
			//java ugly complex Date
			GregorianCalendar gcFrom = new GregorianCalendar();
			GregorianCalendar gcTo = new GregorianCalendar();
			gcFrom.set(GregorianCalendar.YEAR, anioNac);
			gcFrom.set(GregorianCalendar.MONTH, 1);
			gcFrom.set(GregorianCalendar.DAY_OF_MONTH,1);
			gcTo.set(GregorianCalendar.YEAR, anioNac);
			gcTo.set(GregorianCalendar.MONTH, 12);
			gcTo.set(GregorianCalendar.DAY_OF_MONTH,31);
			crPaciente.add(Restrictions.ge("fechaNacimiento",gcFrom.getTime()));
			crPaciente.add(Restrictions.le("fechaNacimiento",gcTo.getTime()));
		}

		if(sexo!=null && !sexo.isEmpty() && !sexo.equals("9"))
			crPaciente.add(Restrictions.eq("pacient.sexo", sexo));
		
		crPaciente.addOrder(Property.forName("apellido01").asc());
		crPaciente.addOrder(Property.forName("apellido02").asc());
		crPaciente.addOrder(Property.forName("nombre").asc());
		
		Criterion crCie9MC;
		Criterion crCie9MCNotEmpty;
		Criterion crCie10;
		Criterion crCie10NotEmpty;
		Criterion crEnfRaraClm;
		Criterion crEnfRaraClmNotEmpty;
		Criterion crEnfRaraClmNotDesconocida;

		crCie9MC = Restrictions.eqOrIsNull("codCie9mc", cie9MC);
		crCie9MCNotEmpty = Restrictions.ne("codCie9mc","");
		crCie10 = Restrictions.eqOrIsNull("codCie10", cie10);
		crCie10NotEmpty = Restrictions.ne("codCie10", "");
		crEnfRaraClm = Restrictions.eqOrIsNull("codEnfRara", enfraraCLM);
		crEnfRaraClmNotEmpty = Restrictions.ne("codEnfRara", "");
		crEnfRaraClmNotDesconocida = Restrictions.ne("codEnfRara","9999999999");
		
		Criterion crCie9MCNotNullAndNotEmpty = Restrictions.and(crCie9MC,crCie9MCNotEmpty);
		Criterion crCie10NotNullAndNotEmpty = Restrictions.and(crCie10,crCie10NotEmpty);
		
		cr.add(Restrictions.or(crCie9MCNotNullAndNotEmpty,crCie10NotNullAndNotEmpty));

		return cr.list();

	}
	
}
