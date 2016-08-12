/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.entities.EnfermedadCie10;
import es.jclm.cs.rarasclm.entities.EnfermedadCodigoLiteral;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie10Service;

@Controller
@RequestMapping("/enfermedades/cie10")
@RarasClmItemMenu(caption="CIE10",deno="Enfermedades",modulo="enfermedades",orden=3)
@SessionAttributes("enfermedadescie10")
public class EnfermedadCie10Controller extends BaseController {


	@Autowired
	private EnfermedadRaraCie10Service enfermedadService;

	public EnfermedadCie10Controller() {

	}

	
	@ModelAttribute("allCie10")
	public List<EnfermedadCie10> populateEmployees() {
		List<EnfermedadCie10> enfermedadesCie10 = enfermedadService.getAllEnfermedadesRarasCie10(true);
		return enfermedadesCie10;
	}

	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(Model model) {
		return "enfermedades/cie10";
	}

	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String setupShow(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadCie10 enfRara = enfermedadService.getEnfermedadRaraCie10ById(id);
		model.addAttribute("enfermedadCie10Model", enfRara);
		return "enfermedades/shows/cie10Show";
	}


	@RequestMapping(value = "/json", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<EnfermedadCodigoLiteral> json() {
		return enfermedadService.getListCodLiteral();
	}


	@RequestMapping(value= "/create", method = RequestMethod.GET)
	public String nuevo(Model model) {
		EnfermedadCie10 enfRara = new EnfermedadCie10();
		model.addAttribute("enfermedadCie10Model", enfRara);
		return "enfermedades/forms/cie10Nuevo";
	}


	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitNuevoForm(@ModelAttribute("enfermedadCie10") EnfermedadCie10 enf, BindingResult result,
			SessionStatus status) {

		if (result.hasErrors()) {
			return "enfermedades/cie10Nuevoc";
		}

		enfermedadService.save(enf);
		
		status.setComplete();
		return "redirect:/enfermedades/cie10/edit/" + enf.getCie10Id();
	}
	
	
	//Edición de la Entidad CIE10
	@RequestMapping(value="edit/{id}", method = RequestMethod.GET)
	public String setupEditIdForm(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadCie10 enfRara = enfermedadService.getEnfermedadRaraCie10ById(id);
		model.addAttribute("enfermedadCie10Model", enfRara);
		return "enfermedades/forms/cie10Edit";
	}
	
	
	@RequestMapping(value="edit/{id}",method = RequestMethod.POST)
	public String submitEditIdForm(@ModelAttribute("enfermedadCie10") EnfermedadCie10 enf, BindingResult result) {
		enfermedadService.update(enf);
		return "redirect:/enfermedades/cie10/edit/"+enf.getCie10Id();
	}
	
	
	@RequestMapping(value = "/json/{id}", produces = "application/json; charset=UTF-8")
	public @ResponseBody EnfermedadCie10 jsonId(@PathVariable String id) {
		return enfermedadService.getEnfermedadRaraCie10ById(id);
	}
}
