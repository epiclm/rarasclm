package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.BusquedaDAOException;
import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.dao.PacienteDao;
import es.jclm.cs.rarasclm.entities.BusquedaModelView;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.Paciente;


@Service
public class BusquedaService {
	

	@Autowired	
	PacienteDao pacienteDao;
	
	@Autowired
	CasoDao casoDao;
	
	private static final Logger log = LoggerFactory.getLogger(BusquedaService.class);

	public List<Paciente> buscaPacientes(BusquedaModelView bmv) throws ServiceRarasCLMException {
		
		int anioNacimiento=-1;
		
		try {
			anioNacimiento = Integer.parseInt(bmv.getAnioNacimiento());
		} catch(Exception ex) {}
			
		try {
			return pacienteDao.busquedaCipOrNombreApellidos(
					bmv.getSeccion(),
					bmv.getCip(),
					bmv.getNombre(),
					bmv.getApellido01(),
					bmv.getApellido02(),
					bmv.getProvincia(),
					bmv.getMunicipio(),
					anioNacimiento,
					bmv.getSexo()
					);
		} catch (BusquedaDAOException ex) {
			log.error(ex.getMessage());
			throw new ServiceRarasCLMException(ex.getMessage());
		}
	}
	
	public List<Caso> buscaCasos(BusquedaModelView bmv) throws CasoRevisionServiceException {
		
		int anioNacimiento=-1;
		Byte baseDiagnostico=-1;
		
		try {
			anioNacimiento = Integer.parseInt(bmv.getAnioNacimiento());
		} catch(Exception ex) {}
			
		try {
			baseDiagnostico = Byte.parseByte(bmv.getBaseDiagnostico());
		} catch(Exception ex) {}
		
		try {
			return casoDao.busqueda(
					bmv.getSeccion(),
					bmv.getCip(),
					bmv.getNombre(),
					bmv.getApellido01(),
					bmv.getApellido02(),
					bmv.getProvincia(),
					bmv.getMunicipio(),
					anioNacimiento,
					String.valueOf(bmv.getSexo()),
					bmv.getCie9MC(),
					bmv.getCie10(),
					bmv.getEnfermedadRaraCLM(),
					baseDiagnostico,
					-1,
					-1
				);
		} catch (BusquedaDAOException ex) {
			log.error(ex.getMessage());
			throw new CasoRevisionServiceException(String.format("Error al realizar búsqueda de casos : %s", ex.getMessage()));
		}		
	}
	
	public long buscaCasosNumResultados(BusquedaModelView bmv) throws CasoRevisionServiceException {
		
		int anioNacimiento=-1;
		Byte baseDiagnostico=-1;
		
		try {
			anioNacimiento = Integer.parseInt(bmv.getAnioNacimiento());
		} catch(Exception ex) {}
			
		try {
			baseDiagnostico = Byte.parseByte(bmv.getBaseDiagnostico());
		} catch(Exception ex) {}
		
		try {
			return casoDao.busquedaNumRegistros(
					bmv.getSeccion(),
					bmv.getCip(),
					bmv.getNombre(),
					bmv.getApellido01(),
					bmv.getApellido02(),
					bmv.getProvincia(),
					bmv.getMunicipio(),
					anioNacimiento,
					String.valueOf(bmv.getSexo()),
					bmv.getCie9MC(),
					bmv.getCie10(),
					bmv.getEnfermedadRaraCLM(),
					baseDiagnostico
				);
		} catch (BusquedaDAOException ex) {
			log.error(ex.getMessage());
			throw new CasoRevisionServiceException(String.format("Error al realizar búsqueda de casos : %s", ex.getMessage()));
		}		
	}
	
}
