/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.AttributeAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
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
	public ModelAndView autentica(HttpServletRequest req) {
		
		//Aquí guarda spring web la url desde la que se accede pero no tiene
		//autorización y se llama a este controlador
		//DefaultSavedRequest defRequest = (DefaultSavedRequest)req.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		
		log.info("Autorización y autenticación necesaria para accceder a la aplicación");
		
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("autentica");
		
		
		return modelView;
	}
}
