package es.jclm.cs.rarasclm.dao;

import java.util.GregorianCalendar;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import es.jclm.cs.rarasclm.entities.Paciente;
@Repository
@SuppressWarnings("unchecked")
public class PacienteDao extends BaseEntityDao<Paciente,Integer> {
	
	private static final Logger log = LoggerFactory.getLogger(PacienteDao.class);

	public int getIdNuevoPaciente() throws NotFoundException {
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createQuery("SELECT MAX(p.idPaciente) FROM Paciente p");
			int ret = Integer.parseInt(query.uniqueResult().toString());
			return ret+1;
		} catch (Exception ex) {
			throw new NotFoundException(ex.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	

	public List<Paciente> busquedaCipOrNombreApellidos (
			int seccion,
			String cip,
			String nombre,
			String apellido1,
			String apellido2,
			String provincia,
			String municipio,
			int anioNac,
			char sexo) throws BusquedaDAOException {
		Session session = this.sf.openSession();
		try {
				Criteria cr = session.createCriteria(Paciente.class);
				Conjunction conjuncion = Restrictions.conjunction();
				if(nombre!=null && !nombre.isEmpty())
					conjuncion.add(Restrictions.like("nombre", "%"+nombre+"%"));
				if(apellido1!=null && !apellido1.isEmpty())
					conjuncion.add(Restrictions.like("apellido01","%"+apellido1+"%"));
				if(apellido2!=null && !apellido2.isEmpty())
					conjuncion.add(Restrictions.like("apellido02","%"+apellido2+"%"));
				if(provincia!=null && !provincia.isEmpty() && !provincia.equals("99"))
					conjuncion.add(Restrictions.eq("provinciaResidencia", provincia));
				if(municipio!=null && !municipio.isEmpty() && !municipio.equals("99999"))
					conjuncion.add(Restrictions.eq("municipioResidencia", municipio));
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
					conjuncion.add(Restrictions.ge("fechaNacimiento",gcFrom.getTime()));
					conjuncion.add(Restrictions.le("fechaNacimiento",gcTo.getTime()));
				}
				if(sexo!='9')
					conjuncion.add(Restrictions.eq("sexo",sexo));
				if(cip!=null && !cip.isEmpty()) {
					//Ugly! why why why linq:-(((
					//cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					//return cr.list();
					cr.add(Restrictions.or(conjuncion,Restrictions.like("cip", cip+"%")));
					cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					return cr.list();

				}	else {
					cr.add(conjuncion);
					cr.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
					return cr.list();
				}
			
			} catch(Exception ex) {
				log.error(ex.getMessage());
				throw new BusquedaDAOException(String.format("% %", "Error en DAO de paciente (búsqueda)",ex.getMessage()));
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		}
}
