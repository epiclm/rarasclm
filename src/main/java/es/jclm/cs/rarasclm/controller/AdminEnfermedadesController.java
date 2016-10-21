package es.jclm.cs.rarasclm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;

@Controller
@RequestMapping("/admin/enfermedades")
@RarasClmItemMenu(caption="Enfermedades",deno="Enfermedades",modulo="admin",orden=4)
public class AdminEnfermedadesController extends BaseController{

	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		
		return "admin/enfermedades/index-enfermedades";
	}
	
}