/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;


/**
 * The Class IndexController.
 */




@Controller
@RequestMapping("/")
@RarasClmItemMenu(caption="Inicio",deno="Inicio",modulo="inicio",orden=1)
@RarasClmItemModulo(caption="Inicio",deno="Inicio",modulo="inicio",orden=0)
@SessionAttributes("enfrara")
public class IndexController extends BaseController {
	

private static final Logger log = LoggerFactory.getLogger(IndexController.class);



@Autowired
private HttpServletRequest request;


	@RequestMapping("/")
	private String index()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return "index";
	}
	
}
