package es.jclm.cs.rarasclm.dao;


import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.config.ConfiguracionRarasCLM;
import es.jclm.cs.rarasclm.entities.Caso;


@Repository
@SuppressWarnings("unchecked")
public class CasoDao extends BaseEntityDao<Caso, String> {

	@Autowired
	private ConfiguracionRarasCLM rarasConfig;

	public List<Caso> busqueda(String seccion, String cip, String nombre, String apellido1, String apellido2,
			String provinciaResidencia, String municipioResidencia, int anioNac, String sexo, String cie9MC,
			String cie10, String enfraraCLM, Byte baseDiagnostico, int numPagina, int numResultadosPagina) {

		boolean addSeccion = true;
		boolean addMunicipio = true;
		boolean addProvincia = true;
		boolean addSexo = true;
		boolean addBaseDtco = true;
		boolean addCip = true;
		boolean addEnfermedades = true;
		boolean addCie9 = true;
		boolean addCie10 = true;
		boolean addEnfRaraClm = true;

		if (seccion == null || seccion.equals(""))
			addSeccion = false;

		if (municipioResidencia == null || municipioResidencia.length() != 5 || municipioResidencia.equals("99999"))
			addMunicipio = false;

		if (provinciaResidencia == null || provinciaResidencia.length() != 2 || provinciaResidencia.equals("99"))
			addProvincia = false;

		if (sexo == null || sexo.length() != 1 || sexo.equals("9"))
			addSexo = false;

		if (baseDiagnostico < 0)
			addBaseDtco = false;

		if (cip != null || !cip.equals(""))
			addCip = false;

		if ((cie9MC == null || cie9MC.equals("")) && (cie10 == null || cie10.equals(""))
				&& (enfraraCLM == null || enfraraCLM.equals("")))
			addEnfermedades = false;
		else {
			if (cie9MC == null || cie9MC.equals("")) {
				addCie9 = false;
			}
			if (cie10 == null || cie10.equals("")) {
				addCie10 = false;
			}
			if (enfraraCLM == null || enfraraCLM.equals("")) {
				addEnfRaraClm = false;
			}
		}

		Session session = this.sf.openSession();

		String sHql = "from Caso c where c.paciente.apellido01 like :apellido1 "
				+ "and  c.paciente.apellido02 like :apellido2 " + "and  c.paciente.nombre like :nombre ";

		if (addCip)
			sHql += "and c.paciente.cip like :cip ";
		if (addSeccion)
			sHql += "and c.paciente.seccion = :seccion ";
		if (addMunicipio)
			sHql += "and c.paciente.municipioResidencia = :municipio ";
		if (addProvincia)
			sHql += "and c.paciente.provinciaResidencia = :provincia ";
		if (addSexo)
			sHql += "and c.paciente.sexo = :sexo ";
		if (addBaseDtco)
			sHql += "and c.baseDiagnostico = :baseDiagnostico ";

		// Como está implementada la consulta de enfermedades
		// que es un OR por necesidad marcada por requisitos
		// hace inviable añadir otra codificación de búsqueda
		if (addEnfermedades) {
			String sHqlEnf = "";
			String sHqlCie10 = "";
			String sHqlCie9mc = "";
			String sHqlEnfRaraClm = "";
			if (addCie9)
				sHqlCie9mc = "(c.codCie9mc = :cie9) ";
			if (addCie10)
				sHqlCie10 = "(c.codCie10 = :cie10) ";
			if (addEnfRaraClm)
				sHqlEnfRaraClm = "(c.codEnfRara = :enfRara) ";
			if (addCie9 && !addCie10 && !addEnfRaraClm) {
				sHql += "and " + sHqlCie9mc;
			} else if (!addCie9 && addCie10 && !addEnfRaraClm) {
				sHql += "and " + sHqlCie10;
			} else if (!addCie9 && !addCie10 && addEnfRaraClm) {
				sHql += "and " + sHqlEnfRaraClm;
			} else if (addCie9 && addCie10 && !addEnfRaraClm) {
				sHql += "and (" + sHqlCie9mc + " or " + sHqlCie10.trim() + ")";
			} else if (!addCie9 && addCie10 && addEnfRaraClm) {
				sHql += "and (" + sHqlCie10 + " or " + sHqlEnfRaraClm.trim() + ")";
			} else if (addCie9 && !addCie10 && addEnfRaraClm) {
				sHql += "and (" + sHqlCie9mc + " or " + sHqlEnfRaraClm.trim() + ")";
			} else if (addCie9 && addCie10 && addEnfRaraClm) {
				sHql += "and (" + sHqlCie9mc + " or " + sHqlCie10 + " or " + sHqlEnfRaraClm.trim() + ")";
			}
		}

		sHql += " order by c.paciente.apellido01 asc, c.paciente.apellido02 asc, c.paciente.nombre asc";

		Query q = session.createQuery(sHql).setString("apellido1", "%" + apellido1 + "%")
				.setString("apellido2", "%" + apellido2 + "%").setString("nombre", "%" + nombre + "%");

		if (addCip)
			q.setString("cip", cip + "%");

		if (addSeccion)
			q.setString("seccion", seccion);

		if (addMunicipio)
			q.setString("municipio", municipioResidencia);

		if (addProvincia)
			q.setString("provincia", provinciaResidencia);

		if (addSexo)
			q.setString("sexo", sexo);

		if (addBaseDtco)
			q.setByte("baseDiagnostico", baseDiagnostico);

		if (addEnfermedades) {
			if (addCie9 && !addCie10 && !addEnfRaraClm) {
				q.setString("cie9", cie9MC);
			} else if (!addCie9 && addCie10 && !addEnfRaraClm) {
				q.setString("cie10", cie10);
			} else if (!addCie9 && !addCie10 && addEnfRaraClm) {
				q.setString("enfRara", enfraraCLM);
			} else if (addCie9 && addCie10 && !addEnfRaraClm) {
				q.setString("cie9", cie9MC);
				q.setString("cie10", cie10);
			} else if (!addCie9 && addCie10 && addEnfRaraClm) {
				q.setString("cie10", cie10);
				q.setString("enfRara", enfraraCLM);
			} else if (addCie9 && !addCie10 && addEnfRaraClm) {
				q.setString("cie9", cie9MC);
				q.setString("enfRara", enfraraCLM);
			} else if (addCie9 && addCie10 && addEnfRaraClm) {
				q.setString("cie9", cie9MC);
				q.setString("cie10", cie10);
				q.setString("enfRara", enfraraCLM);
			}
		}

		if (numPagina < 0 || numResultadosPagina < 0) {
			return q.list();
		} else {
			q.setFirstResult(numResultadosPagina * (numPagina - 1)).setMaxResults(numResultadosPagina);
			return q.list();
		}
			
		
		//		Criteria cr = session.createCriteria(Caso.class,"caso");
		//		cr.createAlias("caso.paciente", "paciente");
		//		cr.setMaxResults(rarasConfig.getMaxResultadosBusqueda()); // VARIABLE DE CONFIGURACIÓN
		//		
		//		//Creamos un criteria para filtrar paciente ONE TO MANY
		//		if(cip!=null && !cip.isEmpty())
		//			cr.add(Restrictions.like("cip", cip+"%"));
		//		if(nombre!=null && !nombre.isEmpty())
		//			cr.add(Restrictions.like("nombre", "%"+nombre+"%"));
		//		if(apellido1!=null && !apellido1.isEmpty())
		//			cr.add(Restrictions.like("apellido01","%"+apellido1+"%"));
		//		if(apellido2!=null && !apellido2.isEmpty())
		//			cr.add(Restrictions.like("apellido02","%"+apellido2+"%"));
		//		if(provincia!=null && !provincia.isEmpty() && !provincia.equals("99"))
		//			cr.add(Restrictions.eq("provinciaResidencia", provincia));
		//		if(municipio!=null && !municipio.isEmpty() && !municipio.equals("99999"))
		//			cr.add(Restrictions.eq("municipioResidencia", municipio));
		//		
				
		//		if(anioNac>1800) {
		//			//java ugly complex Date
		//			GregorianCalendar gcFrom = new GregorianCalendar();
		//			GregorianCalendar gcTo = new GregorianCalendar();
		//			gcFrom.set(GregorianCalendar.YEAR, anioNac);
		//			gcFrom.set(GregorianCalendar.MONTH, 1);
		//			gcFrom.set(GregorianCalendar.DAY_OF_MONTH,1);
		//			gcTo.set(GregorianCalendar.YEAR, anioNac);
		//			gcTo.set(GregorianCalendar.MONTH, 12);
		//			gcTo.set(GregorianCalendar.DAY_OF_MONTH,31);
		//			crPaciente.add(Restrictions.ge("fechaNacimiento",gcFrom.getTime()));
		//			crPaciente.add(Restrictions.le("fechaNacimiento",gcTo.getTime()));
		//		}
		//		
		//		
		//
		//		if(sexo!=null && !sexo.isEmpty() && !sexo.equals("9"))
		//			crPaciente.add(Restrictions.eq("pacient.sexo", sexo));
		//		
		//		crPaciente.addOrder(Property.forName("apellido01").asc());
		//		crPaciente.addOrder(Property.forName("apellido02").asc());
		//		crPaciente.addOrder(Property.forName("nombre").asc());
		//		
		//		Criterion crCie9MC;
		//		Criterion crCie9MCNotEmpty;
		//		Criterion crCie10;
		//		Criterion crCie10NotEmpty;
		//		Criterion crEnfRaraClm;
		//		Criterion crEnfRaraClmNotEmpty;
		//		Criterion crEnfRaraClmNotDesconocida;
		//
		//		crCie9MC = Restrictions.eqOrIsNull("codCie9mc", cie9MC);
		//		crCie9MCNotEmpty = Restrictions.ne("codCie9mc","");
		//		crCie10 = Restrictions.eqOrIsNull("codCie10", cie10);
		//		crCie10NotEmpty = Restrictions.ne("codCie10", "");
		//		crEnfRaraClm = Restrictions.eqOrIsNull("codEnfRara", enfraraCLM);
		//		crEnfRaraClmNotEmpty = Restrictions.ne("codEnfRara", "");
		//		crEnfRaraClmNotDesconocida = Restrictions.ne("codEnfRara","9999999999");
		//		
		//		Criterion crCie9MCNotNullAndNotEmpty = Restrictions.and(crCie9MC,crCie9MCNotEmpty);
		//		Criterion crCie10NotNullAndNotEmpty = Restrictions.and(crCie10,crCie10NotEmpty);
		//		
		//		cr.add(Restrictions.or(crCie9MCNotNullAndNotEmpty,crCie10NotNullAndNotEmpty));
				
		
		//		return cr.list();
				
			

	}
	
}
