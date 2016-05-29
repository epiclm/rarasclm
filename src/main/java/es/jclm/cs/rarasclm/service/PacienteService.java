package es.jclm.cs.rarasclm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.jclm.cs.rarasclm.dao.PacienteDao;
import es.jclm.cs.rarasclm.entities.Paciente;



public class PacienteService {
	
	@Autowired
	private PacienteDao dao;
	
	public List<Paciente> buscaPacientesNombre(String nombre) {
		return dao.busqueda(nombre, "", "");
	}

}
