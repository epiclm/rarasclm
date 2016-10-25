package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.NotFoundException;
import es.jclm.cs.rarasclm.dao.PacienteDao;
import es.jclm.cs.rarasclm.dao.PacienteHistoriaDao;
import es.jclm.cs.rarasclm.dao.UnableToSaveException;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacienteHistoria;
import es.jclm.cs.rarasclm.entities.PacienteHistoriaId;


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
	
	//Proporciona el número de caso consecutivo para
	//un paciente en concreto
	public short getNumNuevoCaso(int pacienteId) throws ServiceRarasCLMException {
		try {
			Paciente paciente = dao.buscar(pacienteId);
			List<Caso> casos = paciente.getCasos();
			//Quiero linq para java!!!
			short ret=0;
			if(casos!=null) {
				for(Caso c : casos) {
					if(ret<c.getNumCaso())
						ret=c.getNumCaso();
				}
				return (short) (ret+1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new ServiceRarasCLMException(ex.getMessage());
		}
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

	
	
	
}
