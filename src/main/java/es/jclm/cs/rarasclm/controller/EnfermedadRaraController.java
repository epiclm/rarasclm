/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.controller;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.AccionResultado;
import es.jclm.cs.rarasclm.entities.EnfermedadCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadCodigoLiteral;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.MergeResult;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie10Service;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie9mcService;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.util.MergeEntity;

/**
 * The Class EnfermedadRaraController.
 */
@Controller
@RequestMapping("/enfermedad/enfrara")
@RarasClmItemModulo(caption="Enfermedad",deno="Enfermedad",modulo="enfermedad",orden=4)
@RarasClmItemMenu(caption="Enfermedad",deno="Enfermedad",modulo="enfermedad",orden=1)
public class EnfermedadRaraController extends BaseController {

	@Autowired
	private EnfermedadRaraService enfermedadService;

	@Autowired
	private EnfermedadRaraCie9mcService enfermedadCie9mcService;

	@Autowired
	private EnfermedadRaraCie10Service enfermedadCie10Service;

	static Log log = LogFactory.getLog(EnfermedadRaraController.class.getName());

	
	public EnfermedadRaraController() {

	}

	
	@ModelAttribute("allRaras")
	public List<EnfermedadRara> populateAllRaras() {
		List<EnfermedadRara> enfermedadesRaras = enfermedadService.getAllEnfermedadesRaras(true);
		return enfermedadesRaras;
	}

	
//	@ModelAttribute("allCie9mc")
//	public List<EnfermedadCie9mc> populateAllCie9mc() {
//		List<EnfermedadCie9mc> enfermedadesCie9mc = enfermedadCie9mcService.getAllEnfermedadesRarasCie9mc(true);
//		return enfermedadesCie9mc;
//	}
	
	
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicio(Model model) {
		return "enfermedad/index-enfRara";
	}

	
	/*EDICION DE ENFRARA*/
	/*Carga el formulario*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEditForm(@PathVariable String id, Model model) {
		model.addAttribute("enfermedadRara", enfermedadService.getEnfermedadRaraById(id));
		return "enfermedad/forms/enfRaraEdit";
	}

	
	/* EDICION DE ENFRARA */
	/* Procesa el formulario */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String submitForm(@PathVariable String id, @ModelAttribute("enfermedadRara") EnfermedadRara enf, SessionStatus status) {
		try {	
			EnfermedadRara enfermedadRara = enfermedadService.Buscar(id);
			MergeResult<EnfermedadRara> enfermedadMerge = new MergeEntity<EnfermedadRara>().merge(enfermedadRara,enf);
			enfermedadService.Actualizar(enfermedadMerge.getMergeObject());
		} catch (Exception ex) {
			
		}
		return "redirect:/enfermedad/enfrara/edit/"+id;
	}


	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String nuevo(Model model) {
		EnfermedadRara enfRara = new EnfermedadRara();
		model.addAttribute("enfermedadRara", enfRara);
		return "enfermedad/forms/enfRaraNuevo";
	}

	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitNuevoForm(@ModelAttribute("enfermedadRara") EnfermedadRara enf, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "enfermedad/forms/enfRara/Nuevo";
		}
		try {
			enfermedadService.save(enf);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
		}
		status.setComplete();
		return "redirect:/enfermedad/enfrara/show/" + enf.getEnfermedadRaraId();
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String setupShowForm(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadRara enfRara = enfermedadService.getEnfermedadRaraById(id);
		model.addAttribute("enfermedadRara", enfRara);
		return "enfermedad/shows/enfRara";
	}

	@RequestMapping(value = "/json", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<EnfermedadCodigoLiteral> json() {
		return enfermedadService.getListCodLiteral();
	}
	
	@RequestMapping(value = "/json/{id}", produces = "application/json; charset=UTF-8")
	public @ResponseBody EnfermedadRara jsonId(@PathVariable String id) {
		return enfermedadService.getEnfermedadRaraById(id);
	}

}
