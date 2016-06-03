package es.jclm.cs.rarasclm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacientesModelView;
import es.jclm.cs.rarasclm.service.PacienteService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;

@Controller
@RequestMapping("/pacientes")
@RarasClmItemModulo(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
@RarasClmItemMenu(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
@SessionAttributes("enfermedades")
public class PacienteController extends BaseController {
	
	@Autowired
	PacienteService servicio;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicioEnvio(Model model) {
		PacientesModelView pacientesMV = new PacientesModelView();
		pacientesMV.setNumPaginas(-1);
		pacientesMV.setNumRegistrosPorPagina(-1);
		pacientesMV.setNumTotalRegistros(-1);
		pacientesMV.setBusquedaApellido1("");
		pacientesMV.setBusquedaApellido2("");
		pacientesMV.setBusquedaNombre("");
		pacientesMV.setBusquedaCIP("");
	
		model.addAttribute("pacientes", pacientesMV);
		getRoute().setId("");
		return "pacientes/index-pacientes";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String inicioVuelta(Model model, 
			@ModelAttribute("pacientes") PacientesModelView pacientesMV,
			SessionStatus status) {

		status.setComplete();
		
		model.addAttribute("pacientes", pacientesMV);
		getRoute().setId("");
		return "pacientes/index-pacientes";
	}
	
	
	@RequestMapping(value = "/show/json/{id}", method = RequestMethod.GET)
	public  @ResponseBody Paciente showJsonPaciente(@PathVariable String id) {
		Integer clave = Integer.valueOf(id);
		try {
			Paciente ret = servicio.Buscar(clave);
			return ret;
		} catch (ServiceRarasCLMException ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String  showPaciente(@PathVariable String id, Model model) {
		Integer clave = Integer.valueOf(id);
		try {
			Paciente paciente = servicio.Buscar(clave);
			model.addAttribute("paciente", paciente);
		} catch (ServiceRarasCLMException ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "pacientes/forms/pacienteEdit";
		
	}
	
	
	

}
