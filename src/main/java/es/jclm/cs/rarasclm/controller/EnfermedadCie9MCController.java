package es.jclm.cs.rarasclm.controller;

import java.util.List;

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
import es.jclm.cs.rarasclm.entities.EnfermedadCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadCodigoLiteral;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie9mcService;

@Controller
@RequestMapping("/enfermedad/cie9mc")
@RarasClmItemMenu(caption="CIE9MC",deno="Enfermedades cie9mc",modulo="enfermedad",orden=2)
@SessionAttributes("enfermedadescie9mc")
public class EnfermedadCie9MCController extends BaseController {

	@Autowired
	private EnfermedadRaraCie9mcService enfermedadService;

	public EnfermedadCie9MCController() {

	
	}

	@ModelAttribute("allCie9mc")
	public List<EnfermedadCie9mc> populateAllCie9Mcs() {
		List<EnfermedadCie9mc> enfermedadesCie9mc = enfermedadService.getAllEnfermedadesRarasCie9mc(true);
		return enfermedadesCie9mc;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		return "enfermedades/cie9mc";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String setupForm(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadCie9mc enfRara = enfermedadService.getEnfermedadRaraCie9ById(id);
		model.addAttribute("enfermedadCie9mcModel", enfRara);
		return "enfermedades/shows/cie9mcShow";
	}

	@RequestMapping(value = "/json", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<EnfermedadCodigoLiteral> json() {
		return enfermedadService.getListCodLiteral();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String nuevo(Model model) {
		EnfermedadCie9mc enfRara = new EnfermedadCie9mc();
		model.addAttribute("enfermedadCie9mcModel", enfRara);
		return "enfermedades/forms/cie9mcNuevo";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitNuevoForm(@ModelAttribute("enfermedadCie9mc") EnfermedadCie9mc enf, BindingResult result,
			SessionStatus status) {

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
	

	@RequestMapping(value ="edit/{id}", method = RequestMethod.GET)
	public String setupEditIdForm(@PathVariable String id, 
			Model model) {
		
		getRoute().setId(id);
		EnfermedadCie9mc enfRara = enfermedadService.getEnfermedadRaraCie9ById(id);
		model.addAttribute("enfermedadCie9mcModel", enfRara);
		return "enfermedades/forms/cie9mcEdit";
		
	}
	
	@RequestMapping(value="edit/{id}",method = RequestMethod.POST)
	public String submitIdForm(@ModelAttribute("enfermedadCie9mc") EnfermedadCie9mc enf, 
								BindingResult result,
								SessionStatus status) {
		
		enfermedadService.update(enf);
		status.setComplete();
		
		return "redirect:/enfermedades/cie9mc/edit/"+enf.getCie9Id();
	}
	
	@RequestMapping(value = "/json/{id}", produces = "application/json; charset=UTF-8")
	public @ResponseBody EnfermedadCie9mc jsonId(@PathVariable String id) {
		return enfermedadService.getEnfermedadRaraCie9ById(id);
	}
	

}
