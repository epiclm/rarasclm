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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
@Controller
public class AutenticaController {
	
	
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);
	
	
	
	/**
	 * Autentica.
	 *
	 * @param error
	 *            the error
	 * @param logout
	 *            the logout
	 * @param request
	 *            the request
	 * @return the model and view
	 */
	@RequestMapping("/autentica")
	public ModelAndView autentica() {
		
		log.info("Autorización y autenticación necesaria para accceder a la aplicación");
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("autentica");
		
		
		return modelView;
	}
}
