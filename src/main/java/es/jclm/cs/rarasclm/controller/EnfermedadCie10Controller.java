/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;


import java.util.List;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie10;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie10Service;

// TODO: Auto-generated Javadoc
/**
 * The Class EnfermedadCie10Controller.
 */
@Controller
@RequestMapping("/enfermedades/cie10")
@RarasClmItemMenu(caption="CIE10",deno="Enfermedades",modulo="enfermedades",orden=3)
@SessionAttributes("enfermedadescie10")
public class EnfermedadCie10Controller extends BaseController {

	/** The enfermedad service. */
	@Autowired
	private EnfermedadRaraCie10Service enfermedadService;

	/** The validator. */
	private Validator validator;

	/**
	 * Instantiates a new enfermedad cie10 controller.
	 */
	public EnfermedadCie10Controller() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	/**
	 * Populate employees.
	 *
	 * @return the list
	 */
	@ModelAttribute("allCie10")
	public List<EnfermedadRaraCie10> populateEmployees() {
		List<EnfermedadRaraCie10> enfermedadesCie10 = enfermedadService.getAllEnfermedadesRarasCie10(true);
		return enfermedadesCie10;
	}

	/**
	 * Setup form.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		return "enfermedades/cie10";
	}

	/**
	 * Setup form.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String setupShow(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadRaraCie10 enfRara = enfermedadService.getEnfermedadRaraCie10ById(id);
		model.addAttribute("enfermedadCie10Model", enfRara);
		return "enfermedades/shows/cie10Show";
	}

	/**
	 * Json.
	 *
	 * @return the list
	 */
	@RequestMapping(value = "/json", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<EnfermedadRaraCie10> json() {
		return enfermedadService.getAllEnfermedadesRarasCie10(true);
	}

	/**
	 * Nuevo.
	 *
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public String nuevo(Model model) {
		EnfermedadRaraCie10 enfRara = new EnfermedadRaraCie10();
		model.addAttribute("enfermedadCie10Model", enfRara);
		return "enfermedades/forms/cie10Nuevo";
	}

	/**
	 * Submit nuevo form.
	 *
	 * @param enf
	 *            the enf
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitNuevoForm(@ModelAttribute("enfermedadCie10") EnfermedadRaraCie10 enf, BindingResult result,
			SessionStatus status) {

		Set<ConstraintViolation<EnfermedadRaraCie10>> violations = validator.validate(enf);

		for (ConstraintViolation<EnfermedadRaraCie10> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			// Add JSR-303 errors to BindingResult
			// This allows Spring to display them in view via a FieldError
			result.addError(
					new FieldError("enfermedadCie10", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors()) {
			return "enfermedades/cie10Nuevoc";
		}
		// Store the employee information in database
		enfermedadService.save(enf);

		// Mark Session Complete and redirect to URL so that page refresh do not
		// re-submit the form
		status.setComplete();
		return "redirect:/enfermedades/cie10/edit/" + enf.getCie10Id();
	}
	
	
	//Edición de la Entidad CIE10
	@RequestMapping(value="edit/{id}", method = RequestMethod.GET)
	public String setupEditIdForm(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadRaraCie10 enfRara = enfermedadService.getEnfermedadRaraCie10ById(id);
		model.addAttribute("enfermedadCie10Model", enfRara);
		return "enfermedades/forms/cie10Edit";
	}
	
	@RequestMapping(value="edit/{id}",method = RequestMethod.POST)
	public String submitEditIdForm(@ModelAttribute("enfermedadCie10") EnfermedadRaraCie10 enf, BindingResult result,
			SessionStatus status) {

/*		Set<ConstraintViolation<EnfermedadRaraCie10>> violations = validator.validate(enf);

		for (ConstraintViolation<EnfermedadRaraCie10> violation : violations) {
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			// Add JSR-303 errors to BindingResult
			// This allows Spring to display them in view via a FieldError
			result.addError(
					new FieldError("enfermedadCie10", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors()) {
			return "enfermedades/cie10";
		}*/
		// Store the employee information in database
		enfermedadService.update(enf);

		// Mark Session Complete and redirect to URL so that page refresh do not
		// re-submit the form
		status.setComplete();
		return "redirect:/enfermedades/cie10/edit/"+enf.getCie10Id();
	}
}
