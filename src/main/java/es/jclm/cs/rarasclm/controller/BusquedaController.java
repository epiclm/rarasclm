package es.jclm.cs.rarasclm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;

@Controller
@RequestMapping("/busqueda")
@RarasClmItemModulo(caption="Búsqueda",deno="Búsqueda",modulo="busqueda",orden=1)
@RarasClmItemMenu(caption="Búsqueda",deno="Búsqueda",modulo="busqueda",orden=1)
@SessionAttributes("pacientes")
public class BusquedaController extends BaseController {

	
	
	
}
