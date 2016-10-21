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
@RequestMapping("/admin/cargas")
@RarasClmItemMenu(caption="Cargas",deno="Cargas",modulo="admin",orden=5)
public class AdminCargaController extends BaseController {

	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		
		return "admin/carga/index-carga";
	}
}