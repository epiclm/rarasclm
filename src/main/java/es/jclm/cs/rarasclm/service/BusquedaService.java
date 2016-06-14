package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.jclm.cs.rarasclm.dao.CasoDao;
import es.jclm.cs.rarasclm.dao.PacienteDao;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacientesModelView;

public class BusquedaService {
	

@Autowired	
PacienteDao pacienteDao;

@Autowired
CasoDao casoDao;

	public List<Paciente> buscaPacientes(PacientesModelView pmv) {
		
		return pacienteDao.busqueda(pmv.getBusquedaCIP(),
				pmv.getBusquedaNombre(),
				pmv.getBusquedaApellido1(),
				pmv.getBusquedaApellido2(),
				pmv.getBusquedaProvincia(),
				pmv.getBusquedaMunicipio(),
				pmv.getBusquedaFechaNacimiento());
		
	}

}
