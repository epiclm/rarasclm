/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainController.
 */
@Controller
public class AutenticaController {
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
	public ModelAndView autentica(@RequestParam(value = "error", required = false) String error,
								  @RequestParam(value = "logout", required = false) String logout, 
								  HttpServletRequest request) {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Error en el nombre de usuario o password");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("autentica");
		return model;
	}
}
