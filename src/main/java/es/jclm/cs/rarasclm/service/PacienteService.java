package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.BaseEntityDao;
import es.jclm.cs.rarasclm.dao.PacienteDao;
import es.jclm.cs.rarasclm.dao.PacienteHistoriaDao;
import es.jclm.cs.rarasclm.dao.UnableToSaveException;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacienteHistoria;
import es.jclm.cs.rarasclm.entities.PacienteHistoriaId;
import es.jclm.cs.rarasclm.entities.PacientesModelView;


@Service
public class PacienteService extends BaseCRUDService<Paciente, Integer> {
	
	private static final Logger log = LoggerFactory.getLogger(PacienteService.class);
	
	PacienteDao dao;
	
	@Autowired
	PacienteHistoriaDao pacienteHistoriaDao;
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private PacienteService(PacienteDao dao)
	{
		this.baseDao = dao;
		this.dao = dao;
	}
	
	
	public void saveHistoria(Paciente paciente) throws ServiceRarasCLMException {
		PacienteHistoriaId id = new PacienteHistoriaId();
		id.setIdVersion(pacienteHistoriaDao.getVersion(paciente.getIdPaciente()));
		id.setIdPaciente(paciente.getIdPaciente());
		PacienteHistoria ph = new PacienteHistoria(id,
				paciente.getIdpacnac(), 
				paciente.getCip(), 
				paciente.getDni(), 
				paciente.getNumeroSegSocial(), 
				paciente.getNombre(), 
				paciente.getApellido01(), 
				paciente.getApellido02(), 
				paciente.getFechaNacimiento(), 
				paciente.getSexo(), 
				paciente.getProvinciaNacimiento(), 
				paciente.getMunicipioNacimiento(), 
				paciente.getPaisNacimiento(), 
				paciente.getDomTipoVia(), 
				paciente.getDomNombreVia(), 
				paciente.getDomNumero(), 
				paciente.getDomPisopuerta(), 
				paciente.getDomOtros(), 
				paciente.getDomCp(), 
				paciente.getProvinciaResidencia(), 
				paciente.getMunicipioResidencia(), 
				paciente.getFallecido(), 
				paciente.getFallecidoFechaComprobacion(), 
				paciente.getFallecidoEtiquetaComprobacion(), 
				paciente.getFallecidoNumbol(), 
				paciente.getEmail(), 
				paciente.getTelefono());
		try {
			pacienteHistoriaDao.guardar(ph);
		} catch (UnableToSaveException ex) {
			log.error(ex.getMessage(),ex);
			new ServiceRarasCLMException(ex.getMessage());
		}
	}

	
	public List<Paciente> buscaPacientes(PacientesModelView pmv) {
		return dao.busqueda(pmv.getBusquedaCIP(),
				pmv.getBusquedaNombre(),
				pmv.getBusquedaApellido1(),
				pmv.getBusquedaApellido2(),
				pmv.getBusquedaProvincia(),
				pmv.getBusquedaMunicipio(),
				pmv.getBusquedaFechaNacimiento());
	}
	
	
}
