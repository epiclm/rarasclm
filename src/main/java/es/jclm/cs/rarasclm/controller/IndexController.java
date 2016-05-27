/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.UserRarasCLM;

/**
 * The Class IndexController.
 */




@Controller
@RequestMapping("/")
@RarasClmItemMenu(caption="Inicio",deno="Inicio",modulo="inicio",orden=0)
@RarasClmItemModulo(caption="Inicio",deno="Inicio",modulo="inicio",orden=1)
@SessionAttributes("enfrara")
public class IndexController extends BaseController {
	

private static final Logger log = LoggerFactory.getLogger(IndexController.class);



@Autowired
HttpServletRequest request;

	@RequestMapping("/")
	private String index()
	{
		return "index";
	}
	
}
