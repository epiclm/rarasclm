package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jclm.cs.rarasclm.dao.PacienteDao;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacientesModelView;


@Service
public class PacienteService extends BaseCRUDService<Paciente, Integer> {
	
	PacienteDao dao;
	
	//Se inyecta aquí el dao porque hay que pasarselo a 
	//la clase Base BaseCRUDService
	@Autowired
	private PacienteService(PacienteDao dao)
	{
		this.baseDao = dao;
		this.dao = dao;
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
