/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.jclm.cs.rarasclm.entities.AutenticaModel;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
@Controller
public class AutenticaController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AutenticaController.class);

	@RequestMapping("/autentica")
	public String autentica(HttpServletRequest req, Model model) {
		
	
		AutenticaModel autenticaModel = (AutenticaModel)req.getAttribute("autentica");
		if(autenticaModel==null) 
			autenticaModel = (AutenticaModel)req.getSession().getAttribute("autentica");
		if(autenticaModel==null) {
			autenticaModel = new AutenticaModel();
			autenticaModel.setError(false);
			autenticaModel.setMensaje("");
		}
		
		model.addAttribute("autentica",autenticaModel);
		
		log.info("Autorización y autenticación necesaria para accceder a la aplicación");

		return "autentica";
	}
	
}
