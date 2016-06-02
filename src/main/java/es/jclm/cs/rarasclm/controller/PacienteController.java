package es.jclm.cs.rarasclm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.entities.PacientesModelView;

@Controller
@RequestMapping("/pacientes")
@RarasClmItemModulo(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
@RarasClmItemMenu(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
@SessionAttributes("enfermedades")
public class PacienteController extends BaseController {
	
	
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
		return "pacientes/index";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String inicioVuelta(Model model, 
			@ModelAttribute("pacientes") PacientesModelView pacientesMV,
			SessionStatus status) {

		status.setComplete();
		
		model.addAttribute("pacientes", pacientesMV);
		getRoute().setId("");
		return "pacientes/index";
	}
	

}
