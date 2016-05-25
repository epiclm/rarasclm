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
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie9mc;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie9mcService;

@Controller
@RequestMapping("/enfermedades/cie9mc")
@RarasClmItemMenu(caption="CIE9MC",deno="Enfermedades",modulo="enfermedades",orden=2)
@SessionAttributes("enfermedadescie9mc")
public class EnfermedadCie9MCController extends BaseController {

	@Autowired
	private EnfermedadRaraCie9mcService enfermedadService;

	private Validator validator;

	public EnfermedadCie9MCController() {
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@ModelAttribute("allCie9mc")
	public List<EnfermedadRaraCie9mc> populateEmployees() {
		List<EnfermedadRaraCie9mc> enfermedadesCie9mc = enfermedadService.getAllEnfermedadesRarasCie9mc(true);
		return enfermedadesCie9mc;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		return "enfermedades/cie9mc";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String setupForm(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadRaraCie9mc enfRara = enfermedadService.getEnfermedadRaraCie9ById(id);
		model.addAttribute("enfermedadCie9mcModel", enfRara);
		return "enfermedades/shows/cie9mcShow";
	}

	@RequestMapping(value = "/json", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<EnfermedadRaraCie9mc> json() {
		return enfermedadService.getAllEnfermedadesRarasCie9mc(true);
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String nuevo(Model model) {
		EnfermedadRaraCie9mc enfRara = new EnfermedadRaraCie9mc();
		model.addAttribute("enfermedadCie9mcModel", enfRara);
		return "enfermedades/forms/cie9mcNuevo";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitNuevoForm(@ModelAttribute("enfermedadCie9mc") EnfermedadRaraCie9mc enf, BindingResult result,
			SessionStatus status) {

		Set<ConstraintViolation<EnfermedadRaraCie9mc>> violations = validator.validate(enf);

		for (ConstraintViolation<EnfermedadRaraCie9mc> violation : violations) {
			String propertyvalue = violation.getPropertyPath().toString();
			String message = violation.getMessage();
			// Add JSR-303 errors to BindingResult
			// This allows Spring to display them in view via a FieldError
			//result.addError(
			//		new FieldError("enfermedadCie9mc", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors()) {
			return "enfermedades/cie9mcNuevoc";
		}
		// Store the employee information in database
		enfermedadService.save(enf);

		// Mark Session Complete and redirect to URL so that page refresh do not
		// re-submit the form
		status.setComplete();
		return "redirect:/enfermedades/cie9mc/edit/" + enf.getCie9Id();
	}
	
	/**
	 * Setup Id form.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value ="edit/{id}", method = RequestMethod.GET)
	public String setupEditIdForm(@PathVariable String id, 
			Model model) {
		
		getRoute().setId(id);
		EnfermedadRaraCie9mc enfRara = enfermedadService.getEnfermedadRaraCie9ById(id);
		model.addAttribute("enfermedadCie9mcModel", enfRara);
		return "enfermedades/forms/cie9mcEdit";
		
	}
	
	/**
	 * Submit form.
	 *
	 * @param enf
	 *            the enf
	 * @param result
	 *            the result
	 * @param status
	 *            the status
	 * @return the string
	 */
	@RequestMapping(value="edit/{id}",method = RequestMethod.POST)
	public String submitIdForm(@ModelAttribute("enfermedadCie9mc") EnfermedadRaraCie9mc enf, 
								BindingResult result,
								SessionStatus status) {
		
		enfermedadService.update(enf);
		status.setComplete();
		
		return "redirect:/enfermedades/cie9mc/edit/"+enf.getCie9Id();
	}
	
	

}
