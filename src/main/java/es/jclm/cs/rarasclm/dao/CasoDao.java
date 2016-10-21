package es.jclm.cs.rarasclm.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.jclm.cs.rarasclm.entities.Caso;



@Repository
@SuppressWarnings("unchecked")
public class CasoDao extends BaseEntityDao<Caso, String> {

	private boolean addSeccion = true;
	private boolean addMunicipio = true;
	private boolean addProvincia = true;
	private boolean addSexo = true;
	private boolean addBaseDtco = true;
	private boolean addCip = true;
	private boolean addEnfermedades = true;
	private boolean addCie9 = true;
	private boolean addCie10 = true;
	private boolean addEnfRaraClm = true;
	
	private static final Logger log = LoggerFactory.getLogger(CasoDao.class);

	public  List<Caso> busqueda(
			int seccion, 
			String cip, 
			String nombre, 
			String apellido1, 
			String apellido2,
			String provinciaResidencia, 
			String municipioResidencia, 
			int anioNac, 
			String sexo, 
			String cie9MC,
			String cie10, 
			String enfraraCLM, 
			Byte baseDiagnostico, 
			int numPagina, 
			int numResultadosPagina) throws BusquedaDAOException {
		
		Session session = this.sf.openSession();
		
		try {
			String sHql = createBodyQuery(seccion, 
					cip, 
					nombre, 
					apellido1, 
					apellido2, 
					provinciaResidencia,
					municipioResidencia, 
					anioNac, 
					sexo, 
					cie9MC, 
					cie10, 
					enfraraCLM, 
					baseDiagnostico);

			Query q = session.createQuery(sHql).setString("apellido1", "%" + apellido1 + "%")
					.setString("apellido2", "%" + apellido2 + "%").setString("nombre", "%" + nombre + "%");

			if (addCip)
				q.setString("cip", cip + "%");

//			if (addSeccion)
//				q.setInteger("seccion", seccion);

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
		} catch (Exception e) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	@SuppressWarnings("null") // eclipse crazy??
	public  long busquedaNumRegistros (int seccion, 
			String cip, 
			String nombre, 
			String apellido1, 
			String apellido2,
			String provinciaResidencia, 
			String municipioResidencia, 
			int anioNac, 
			String sexo, 
			String cie9MC,
			String cie10, 
			String enfraraCLM, 
			Byte baseDiagnostico) throws BusquedaDAOException {

		Session session = this.sf.openSession();
		try {
			
			String sHql = createBodyQuery(seccion, 
					cip, 
					nombre, 
					apellido1, 
					apellido2, 
					provinciaResidencia,
					municipioResidencia, 
					anioNac, 
					sexo, 
					cie9MC, 
					cie10, 
					enfraraCLM, 
					baseDiagnostico);

			String countHql = "select count(*) " + sHql;

			Query q = session.createQuery(countHql).setString("apellido1", "%" + apellido1 + "%")
					.setString("apellido2", "%" + apellido2 + "%").setString("nombre", "%" + nombre + "%");

			if (addCip)
				q.setString("cip", cip + "%");

//			if (addSeccion)
//				q.setInteger("seccion", seccion);

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

			return (long) q.uniqueResult();

		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new BusquedaDAOException(String.format("Error al realizar la busqueda %s",ex.getMessage()));
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	
	
	
	private String createBodyQuery(int seccion, 
			String cip, 
			String nombre, 
			String apellido1, 
			String apellido2,
			String provinciaResidencia, 
			String municipioResidencia, 
			int anioNac, 
			String sexo, 
			String cie9MC,
			String cie10, 
			String enfraraCLM, 
			Byte baseDiagnostico) {

		// Ojo que la instancia es un singleton en el contenedor spring
		// por lo tanto se conserva el estado entre peticiones. Por lo
		// que hay que iniciar los atributos del objeto en cada busqueda
		try {
			addSeccion = false;
			addMunicipio = true;
			addProvincia = true;
			addSexo = true;
			addBaseDtco = true;
			addCip = true;
			addEnfermedades = true;
			addCie9 = true;
			addCie10 = true;
			addEnfRaraClm = true;

			if (seccion==0)
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
					&& (enfraraCLM == null || enfraraCLM.equals(""))) {
				addEnfermedades = false;
			} else {
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

			String sHql = "from Caso c where c.paciente.apellido01 like :apellido1 "
					+ "and c.paciente.apellido02 like :apellido2 " + "and  c.paciente.nombre like :nombre ";

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

			return sHql;
			
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		} finally {

		}

	}
}
