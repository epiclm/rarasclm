/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;

/**
 * The Class IndexController.
 */
@Controller
@RequestMapping("/")
@RarasClmItemMenu(caption="Inicio",deno="Inicio",modulo="inicio",orden=0)
@RarasClmItemModulo(caption="Inicio",deno="Inicio",modulo="inicio",orden=1)
@SessionAttributes("enfrara")
public class IndexController extends BaseController {

	@RequestMapping("/")
	private String index()
	{
		return "index";
	}
	
}
