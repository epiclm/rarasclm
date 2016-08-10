package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Paciente> buscaPacientes(BusquedaModelView bmv) {
		
		int anioNacimiento=-1;
		
		try {
			anioNacimiento = Integer.parseInt(bmv.getAnioNacimiento());
		} catch(Exception ex) {}
			
		return pacienteDao.busqueda(
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
	}
	
	public List<Caso> buscaCasos(BusquedaModelView bmv) {
		
		int anioNacimiento=-1;
		Byte baseDiagnostico=-1;
		
		try {
			anioNacimiento = Integer.parseInt(bmv.getAnioNacimiento());
			baseDiagnostico = Byte.parseByte(bmv.getBaseDiagnostico());
		} catch(Exception ex) {}
			
		return casoDao.busqueda(
				bmv.getSeccion(),
				bmv.getCip(),
				bmv.getNombre(),
				bmv.getApellido01(),
				bmv.getApellido02(),
				bmv.getProvincia(),
				bmv.getMunicipio(),
				anioNacimiento,
				bmv.getSexo(),
				bmv.getCie9MC(),
				bmv.getCie10(),
				bmv.getEnfermedadRaraCLM(),
				baseDiagnostico,
				-1,
				-1
			);
		
	}
	
	

}
