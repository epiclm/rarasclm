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
import es.jclm.cs.rarasclm.entities.AccionResultado;
import es.jclm.cs.rarasclm.entities.EnfermedadCie9mc;
import es.jclm.cs.rarasclm.entities.EnfermedadCodigoLiteral;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie10Service;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie9mcService;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;

/**
 * The Class EnfermedadRaraController.
 */
@Controller
@RequestMapping("/enfermedades/enfrara")
@RarasClmItemMenu(caption="Enfermedad Rara CLM",deno="Enfermedades",modulo="enfermedades",orden=1)
@SessionAttributes("enfrara")
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
		getRoute().setId("");
		return "enfermedades/enfRara";
	}

	
	/*EDICION DE ENFRARA*/
	/*Carga el formulario*/
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String setupEditForm(@PathVariable String id, Model model) {
		getRoute().setId(id);
		model.addAttribute("enfermedadRaraModel", enfermedadService.getEnfermedadRaraById(id));
		return "enfermedades/forms/enfRaraEdit";
	}

	
	/* EDICION DE ENFRARA */
	/* Procesa el formulario */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("enfermedadRaraModel") EnfermedadRara enf,
			@ModelAttribute("resultado") AccionResultado resultado, SessionStatus status) {
		try {
			enfermedadService.update(enf);
			//Se actualiza para que carge las entidades relacionadas a la vista
			String id = enf.getEnfermedadRaraId();
			resultado.setSuccess(true);
			resultado.setMensaje(String.format("Se ha actualizado correctamente COD. %s %s",
					enf.getEnfermedadRaraId(),
					enf.getLiteral()));
			status.setComplete();
		} catch (Exception ex) {
			String mensaje = String.format("Error al intentar actualizar enfRara %s - %s", 
					enf.getEnfermedadRaraId(),
					ex.getMessage());
			resultado.setError(true);
			resultado.setMensaje(String.format("ERROR : %s",ex.getMessage()));
			log.error(String.format("ERROR CONTROLADOR %s", mensaje));
			status.setComplete();
		}
		return "enfermedades/forms/enfRaraEdit";
	}

	
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String setupShowForm(@PathVariable String id, Model model) {
		getRoute().setId(id);
		EnfermedadRara enfRara = enfermedadService.getEnfermedadRaraById(id);
		model.addAttribute("enfermedadRaraModel", enfRara);
		return "enfermedades/shows/enfRaraShow";
	}

	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String nuevo(Model model) {
		EnfermedadRara enfRara = new EnfermedadRara();
		model.addAttribute("enfermedadRaraModel", enfRara);
		return "enfermedades/forms/enfRaraNuevo";
	}

	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitNuevoForm(@ModelAttribute("enfermedadRara") EnfermedadRara enf, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			return "enfermedades/forms/enfRara/Nuevo";
		}
		enfermedadService.save(enf);
		status.setComplete();
		return "redirect:/enfermedades/enfrara/show/" + enf.getEnfermedadRaraId();
	}

	
	@RequestMapping(value = "/addcie9mc/{id}/{id2}", method = RequestMethod.GET)
	public String addEnfermedadRaraHasCie9mc(@PathVariable String id, @PathVariable String id2, Model model,
			SessionStatus status) {
//		EnfermedadRaraHasEnfermedadRaraCie9mc hasCie9  =  new EnfermedadRaraHasEnfermedadRaraCie9mc();
//		EnfermedadRaraHasEnfermedadRaraCie9mcId cie9id =  new EnfermedadRaraHasEnfermedadRaraCie9mcId();
//		cie9id.setEnfermedadRaraId(id);
//		cie9id.setCie9Id(id2);
//		hasCie9.setId(cie9id);
//		hasCie9.setNumPrioridad(-1);
//		hasCie9.setNotas("");
//		if (enfermedadService.addHasCie9mc(id, hasCie9)) {
//			return "redirect:/enfermedades/enfrara/edit/" + id;
//		} else {
//			EnfermedadRaraView enfRara = new EnfermedadRaraView(enfermedadService.getEnfermedadRaraById(id));
//			model.addAttribute("enfermedadRaraModel", enfRara);
//			EnfermedadRaraCie9mc enfCie9mc = enfermedadCie9mcService.getEnfermedadRaraCie9ById(id2);
//			model.addAttribute("error",
//					String.format("Error: No se ha podido asociar la enfermedad de la clasificación CIE9. Compruebe si la enfermedad %s %s ya está asociada.",
//							enfCie9mc.getCie9Id(), enfCie9mc.getLiteral()));
			//return "enfermedades/forms/enfRaraEdit";
		//}
		return "enfermedades/forms/enfRaraEdit";
	}

	@RequestMapping(value = "/addcie10/{id}/{id2}", method = RequestMethod.GET)
	public String addEnfermedadRaraHasCie10(@PathVariable String id, @PathVariable String id2, Model model,
			SessionStatus status) {
//		EnfermedadRaraHasEnfermedadRaraCie10 hasCie10 = new EnfermedadRaraHasEnfermedadRaraCie10();
//		EnfermedadRaraHasEnfermedadRaraCie10Id cie10 = new EnfermedadRaraHasEnfermedadRaraCie10Id();
//		cie10.setEnfermedadRaraId(id);
//		cie10.setCie10Id(id2);
//		hasCie10.setId(cie10);
//		hasCie10.setNumPrioridad(-1);
//		hasCie10.setNotas("");
//		if (enfermedadService.addHasCie10(id, hasCie10)) {
//			return "redirect:/enfermedades/enfrara/edit/" + id;
//		} else {
//			EnfermedadRaraView enfRara = new EnfermedadRaraView(enfermedadService.getEnfermedadRaraById(id));
//			model.addAttribute("enfermedadRaraModel", enfRara);
//			EnfermedadRaraCie10 enfCie10 = enfermedadCie10Service.getEnfermedadRaraCie10ById(id2);
//			model.addAttribute("error",
//					String.format(
//							"Error: No se ha podido asociar la enfermedad de la clasificación CIE10. Compruebe si la enfermedad %s %s ya está asociada.",
//							enfCie10.getCie10Id(), enfCie10.getLiteral()));
		//return "enfermedades/forms/enfRaraEdit";
		//}
		return "enfermedades/forms/enfRaraEdit";
	}

	@RequestMapping(value = "/updatecie9mc/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody AccionResultado updateEnfermedadRaraHasCie9Mc(@PathVariable String id,
			@PathVariable String id2,
			@RequestParam("notas") String notas,
			@RequestParam("numPrioridad") String numPrioridad) {
//		AccionRespuesta resultado = new AccionRespuesta();
//		try {
//			enfermedadService.updateHasCie9mc(id, id2, notas, Integer.parseInt(numPrioridad));
//			resultado.setSuccess(true);
//			resultado.setMensaje(String.format("Actualizada la asociacion de la enfermedad rara %s con cie9mc %s", id, id2));
//			return resultado;
//		} catch (Exception ex) {
//			resultado.setError(true);
//			resultado.setMensaje(String.format("ERROR al asociar %s con cie9mc %s \n %s", id, id2, ex.getMessage()));
//			return resultado;
//		}
		return null;
	}

	@RequestMapping(value = "/deletecie9mc/{id}/{id2}", method = RequestMethod.POST)
	public @ResponseBody AccionResultado deleteEnfermedadRaraHasCie9Mc(@PathVariable String id,
			@PathVariable String id2) {
//		AccionRespuesta respuesta = new AccionRespuesta();
//		respuesta.setId(id2);
//		try {
//			if (enfermedadService.deleteHasCie9mc(id, id2)) {
//				respuesta.setMensaje(String.format("Se ha desasociado la enfermedad CIE9MC %s correctamente", id2));
//				respuesta.setSuccess(true);
//			} else {
//				respuesta.setError(true);
//				respuesta.setMensaje(String.format("Error al borrar la enfermedad asociada de la clasificación CIE9MC %s", id2));
//			}
//		} catch (Exception ex) {
//			respuesta.setError(true);
//			respuesta.setMensaje(ex.getMessage());
//		}
//		return respuesta;
		return null;
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
