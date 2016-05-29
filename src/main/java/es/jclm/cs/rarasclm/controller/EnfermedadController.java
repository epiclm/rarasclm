/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.MenuModulo;

/**
 * The Class EnfermedadController.
 */
@Controller
@RequestMapping("/enfermedades")
@RarasClmItemModulo(caption="Enfermedades",deno="Enfermedades",modulo="enfermedades",orden=2)
@RarasClmItemMenu(caption="Enfermedades",deno="Enfermedades",modulo="enfermedades",orden=1)
@SessionAttributes("enfermedades")
public class EnfermedadController extends BaseController {
	
	@ModelAttribute("menuModel")
	public MenuModulo getMenuModel() {
		return base.getMenuModel().getModuloMenuById("enfermedades");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		return "enfermedades";
	}
}
