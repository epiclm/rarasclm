package es.jclm.cs.rarasclm.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.BusquedaModelView;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin")
@RarasClmItemModulo(caption="Administración",deno="Administración",modulo="admin",orden=5)
@RarasClmItemMenu(caption="Administración",deno="Administración",modulo="admin",orden=1)
public class AdministracionController extends BaseController {

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		
		return "admin/index-admin";
	}
	 
	
	
}
