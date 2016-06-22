package es.jclm.cs.rarasclm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.dao.PacienteDao;
import es.jclm.cs.rarasclm.entities.BusquedaModelView;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacientesModelView;

@Service
public class BusquedaService {
	

@Autowired	
PacienteDao pacienteDao;

@Autowired
CasoDao casoDao;

	public List<Paciente> buscaPacientes(BusquedaModelView bmv) {
		
		int anioNacimiento=-1;
		Byte baseDiagnostico=-1;
		
		try {
			anioNacimiento = Integer.parseInt(bmv.getAnioNacimiento());
			baseDiagnostico = Byte.parseByte(bmv.getBaseDiagnostico());
		} catch(Exception ex) {}
			
		return pacienteDao.busqueda(
				bmv.getCip(),
				bmv.getNombre(),
				bmv.getApellido01(),
				bmv.getApellido02(),
				bmv.getProvincia(),
				bmv.getMunicipio(),
				anioNacimiento,
				bmv.getEnfermedadRaraCLM(),
				bmv.getCie9MC(),
				bmv.getCie10(),
				baseDiagnostico,
				bmv.getSexo()
				);
		
	}

}
