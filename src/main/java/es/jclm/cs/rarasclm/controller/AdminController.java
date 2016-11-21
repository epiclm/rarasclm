package es.jclm.cs.rarasclm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;


@Controller
@RequestMapping("/admin")
@RarasClmItemModulo(caption="Administración",deno="Administración",modulo="admin",orden=5)
@RarasClmItemMenu(caption="Inicio",deno="Inicio",modulo="admin",orden=1)
public class AdminController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		
		return "admin/index-admin";
	}
}