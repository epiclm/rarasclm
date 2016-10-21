package es.jclm.cs.rarasclm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;

@Controller
@RequestMapping("/admin/descargas")
@RarasClmItemMenu(caption="Descargas",deno="Descargas",modulo="admin",orden=3)
public class AdminDescargaDatosController extends BaseController{

	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		
		return "admin/descargas/index-descargas";
	}
	
}