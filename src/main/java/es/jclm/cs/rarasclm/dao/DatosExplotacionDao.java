package es.jclm.cs.rarasclm.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;


import es.jclm.cs.rarasclm.entities.VPacienteCaso;

/*
 * DAO de exportación de datos
 * 
Nota: El rendimiento con Hibernate era muy deficiente
      Por esta razón se recurre a obtener la conexión y lanzar
      la consulta con SQL directamente
*/

@Repository
@Transactional
public class DatosExplotacionDao {
	
	private static final Logger log = LoggerFactory.getLogger(DatosExplotacionDao.class);
	
	private String szSql = "SELECT pc FROM VPacienteCaso pc ORDER BY pc.idPaciente asc, pc.numCaso asc";
	
	@Autowired 
	private ApplicationContext appSpringContext;
	
	@Autowired
	protected SessionFactory sf;
	
	//Rendimiento no óptimo (Mediante Hibernate)
	public List<VPacienteCaso> getDatosExplotacionHibernate() throws BusquedaDAOException {
		Session session = sf.openSession();
		session.setDefaultReadOnly(true);
		session.setFlushMode(FlushMode.NEVER);
		try {
			Query query = session.createQuery(szSql);
			query.setReadOnly(true);
			return (List<VPacienteCaso>) query.list();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new BusquedaDAOException(ex);
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	public List<VPacienteCaso> getDatosExplotacionJDBC() throws BusquedaDAOException, SQLException {
		
		/*Intento de obtener el datasourece a través de de la sesion de hibernate
		* ERROR DETECTADO AL SUBIR A SERVIDOR AZSI DE PRUEBAS
		*		-> Se mandaba siempre el usuario root al servidor de bd.
		*		   cuando mysql no lo tenía configurado
		*		   Daba error, cuando era root funcionaba bien
		* 		   (Esto se hace para no "hardcorear" la conexión aquí)
		*/
		
		//Session session = sf.getCurrentSession();
		//org.hibernate.SessionFactory sessionFactory=session.getSessionFactory();
		//ConnectionProvider cp=((SessionFactoryImpl)sessionFactory).getConnectionProvider();
		/* Segundo intento obtener el datasource, esta vez mediante spring */
		DataSource ds = (DataSource) appSpringContext.getBean("dataSourceRarasCLM");
		Connection connection = ds.getConnection();
		
		try {
			List<VPacienteCaso> ret = new ArrayList<VPacienteCaso>();
			//Connection connection = cp.getConnection();
			Statement st = connection.createStatement();
			String sSqlQuery = "SELECT * FROM v_paciente_caso ORDER BY id_caso";
			ResultSet rs = st.executeQuery(sSqlQuery);
			while(rs.next()) {
				VPacienteCaso pc = new VPacienteCaso();
				pc.setIdCaso(rs.getString("id_caso"));
				pc.setNumCaso(rs.getShort("num_caso"));
				pc.setNombre(rs.getString("nombre"));
				pc.setApellido01(rs.getString("apellido_01"));
				pc.setApellido02(rs.getString("apellido_02"));
				pc.setBaseDiagnostico(rs.getByte("base_diagnostico"));
				pc.setCasoFechahoraCreacion(rs.getDate("caso_fechahora_creacion"));
				pc.setCasoFechahoraModificacion(rs.getDate("caso_fechahora_modificacion"));
				pc.setCasoUsuarioCreacion(rs.getString("caso_usuario_creacion"));
				pc.setCasoUsuarioModificacion(rs.getString("caso_usuario_modificacion"));
				pc.setCip(rs.getString("cip"));
				pc.setCodCie10(rs.getString("cod_cie10"));
				pc.setCodCie9mc(rs.getString("cod_cie9mc"));
				pc.setCodEdta(rs.getString("cod_edta"));
				pc.setCodOtros(rs.getString("cod_otros"));
				pc.setCodOtroDeno(rs.getString("cod_otro_deno"));
				pc.setCodOmin(rs.getString("cod_omin"));
				pc.setCodSnomed(rs.getString("cod_snomed"));
				pc.setDeclarada(rs.getShort("declarada"));
				if(rs.getString("dni")!=null)
					pc.setDni(rs.getString("dni"));
				else
					pc.setDni("");
				pc.setDomCp(rs.getString("dom_cp"));
				pc.setDomNombreVia(rs.getString("dom_nombre_via"));
				pc.setDomNumero(rs.getString("dom_numero"));
				pc.setDomOtros(rs.getString("dom_otros"));
				pc.setDomPisopuerta(rs.getString("dom_pisopuerta"));
				pc.setDomTipoVia(rs.getString("dom_tipo_via"));
				pc.setEmail(rs.getString("email"));
				pc.setEnfermedadesCronicas(rs.getString("enfermedades_cronicas"));
				pc.setEnfrara(rs.getString("enfrara"));
				pc.setFallecido(rs.getByte("fallecido"));
				pc.setFallecidoFecha(rs.getDate("fallecido_fecha"));
				pc.setFallecidoEtiquetaComprobacion(rs.getString("fallecido_etiqueta_comprobacion"));
				pc.setFallecidoFechaComprobacion(rs.getDate("fallecido_fecha_comprobacion"));
				pc.setFallecidoNumbol(rs.getString("fallecido_numbol"));
				pc.setFamiliaresEnfermedadesRaras(rs.getByte("familiares_enfermedades_raras"));
				pc.setFechaDeteccion(rs.getDate("fecha_deteccion"));
				pc.setFechaDiagnostico(rs.getDate("fecha_diagnostico"));
				pc.setFechaInicioSintomas(rs.getDate("fecha_inicio_sintomas"));
				pc.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
				pc.setFuentePacientesA(rs.getBoolean("fuente_pacientes_A"));
				pc.setFuenteCmbdC(rs.getBoolean("fuente_cmbd_C"));
				pc.setFuenteDefcongD(rs.getBoolean("fuente_defcong_D"));
				pc.setFuenteEdoE(rs.getBoolean("fuente_edo_E"));
				pc.setFuenteIsocialesG(rs.getBoolean("fuente_isociales_G"));
				pc.setFuenteMhuerfH(rs.getBoolean("fuente_mhuerf_H"));
				pc.setFuenteMetabolN(rs.getBoolean("fuente_metabol_N"));
				pc.setFuenteRinvI(rs.getBoolean("fuente_rinv_I"));
				pc.setFuenteRmortM(rs.getBoolean("fuente_rmort_M"));
				pc.setFuenteRcancerT(rs.getBoolean("fuente_rcancer_T"));
				pc.setFuenteRenalR(rs.getBoolean("fuente_renal_R"));
				pc.setFuenteHcEspecializadaMasivaQ(rs.getBoolean("fuente_hc_especializada_masiva_Q"));
				pc.setFuenteHcEspecializadaU(rs.getBoolean("fuente_hc_especializada_U"));
				pc.setFuenteHcPrimariaMasivaP(rs.getBoolean("fuente_hc_primaria_masiva_P"));
				pc.setFuenteHcPrimariaV(rs.getBoolean("fuente_hc_primaria_V"));
				pc.setFuenteOtrosO(rs.getBoolean("fuente_otros_O"));
				pc.setHospital(rs.getString("hospital"));
				pc.setIdPaciente(rs.getInt("id_paciente"));
				pc.setIdpacnac(rs.getString("idpacnac"));
				pc.setJucioClinico(rs.getString("jucio_clinico"));
				pc.setLiteralEnfermedad(rs.getString("literal_enfermedad"));
				pc.setMunicipioNacimiento(rs.getString("municipio_nacimiento"));
				pc.setProvinciaNacimiento(rs.getString("provincia_nacimiento"));
				pc.setMunicipioResidencia(rs.getString("municipio_residencia"));
				pc.setProvinciaResidencia(rs.getString("provincia_residencia"));
				if(rs.getString("sexo")!=null)
					pc.setSexo(rs.getString("sexo").charAt(0));
				pc.setTelefono(rs.getString("telefono"));
				pc.setTratamiento(rs.getString("tratamiento"));
				pc.setUsuarioDeclara(rs.getString("usuario_declara"));
				
				ret.add(pc);
			}
			st.close();
			return ret;
		} catch (SQLException ex) {
			log.error(ex.getMessage(),ex);
			throw new BusquedaDAOException(ex);
		} finally {
			if (connection!=null && !connection.isClosed()) {
				connection.close();
			}
		}
	}

}
