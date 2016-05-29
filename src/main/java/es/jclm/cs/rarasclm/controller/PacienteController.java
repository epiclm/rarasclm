package es.jclm.cs.rarasclm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;

@Controller
@RequestMapping("/pacientes")
@RarasClmItemModulo(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
@RarasClmItemMenu(caption="Pacientes",deno="Pacientes",modulo="pacientes",orden=1)
@SessionAttributes("enfermedades")
public class PacienteController extends BaseController {
	
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicio(Model model) {
		getRoute().setId("");
		return "pacientes";
	}
	

}
