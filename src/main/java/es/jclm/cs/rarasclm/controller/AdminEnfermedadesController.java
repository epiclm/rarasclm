package es.jclm.cs.rarasclm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.EnfermedadCodigoLiteral;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.MergeResult;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie10Service;
import es.jclm.cs.rarasclm.service.EnfermedadRaraCie9mcService;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.util.MergeEntity;
import es.jclm.cs.rarasclm.util.RarasClmConstantes;

@Controller
@RequestMapping("/admin/enfermedades")
@RarasClmItemMenu(caption="Enfermedades",deno="Enfermedades",modulo="admin",orden=4)
public class AdminEnfermedadesController extends BaseController{


	
	@Autowired
	private EnfermedadRaraService enfermedadService;

	@Autowired
	private EnfermedadRaraCie9mcService enfermedadCie9mcService;

	@Autowired
	private EnfermedadRaraCie10Service enfermedadCie10Service;
	
	@Autowired
	HttpServletRequest request;

	static Log log = LogFactory.getLog(AdminEnfermedadesController.class.getName());

	
	public AdminEnfermedadesController() {

	}

	@ModelAttribute("allRaras")
	public List<EnfermedadRara> populateAllRaras() {
		List<EnfermedadRara> enfermedadesRaras = enfermedadService.getAllEnfermedadesRaras(true);
		return enfermedadesRaras;
	}

		
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicio() {
		return "admin/enfermedades/index-enfermedades";
	}
	
	/*************************/
	/*EDICION DE ENFRARA     */
	/*Carga el formulario    */
	/*************************/
	@RequestMapping(value = "/enfrara/edit/{id}", method = RequestMethod.GET)
	public String setupEditForm(@PathVariable String id, Model model) {
		model.addAttribute("enfermedadRara", enfermedadService.getEnfermedadRaraById(id));
		return "admin/enfermedades/form/edit-enfRara";
	}

	/*************************/
	/* EDICION DE ENFRARA    */
	/* Procesa el formulario */
	/*************************/
	@RequestMapping(value = "/enfrara/edit/{id}", method = RequestMethod.POST)
	public String submitForm(@PathVariable String id, @ModelAttribute("enfermedadRara") EnfermedadRara enf, SessionStatus status) {
		MensajeResultado mensaje = new MensajeResultado();
		try {	
			EnfermedadRara enfermedadRara = enfermedadService.Buscar(id);
			MergeResult<EnfermedadRara> enfermedadMerge = new MergeEntity<EnfermedadRara>().merge(enfermedadRara,enf);
			enfermedadService.Actualizar(enfermedadMerge.getMergeObject());
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(String.format("Se ha grabado la enfermedad %s %s correctamente",enf.getEnfermedadRaraId(),enf.getLiteral()));
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, mensaje);
		} catch (Exception ex) {
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje(String.format(ex.getMessage()));
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, mensaje);
			log.error(ex.getMessage());
		}
		return "redirect:/admin/enfermedades/enfrara/edit/"+id;
	}

	/*************************/
	/* NUEVA ENFRARA         */
	/* Carga el formulario   */
	/*************************/
	@RequestMapping(value = "enfrara/nueva", method = RequestMethod.GET)
	public String nuevo(Model model) {
		EnfermedadRara enfRara = new EnfermedadRara();
		model.addAttribute("enfermedadRara",enfRara);
		return "admin/enfermedades/form/nueva-enfRara";
	}

	/*************************/
	/* NUEVA ENFRARA         */
	/* Procesa el formulario */
	/*************************/
	@RequestMapping(value = "enfrara/nueva", method = RequestMethod.POST)
	public String submitNuevoForm(@ModelAttribute("enfermedadRara") EnfermedadRara enf) {
		MensajeResultado mensaje = new MensajeResultado();
		try {
			enfermedadService.save(enf);
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(String.format("Se ha grabado la enfermedad %s %s correctamente",enf.getEnfermedadRaraId(),enf.getLiteral()));
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, mensaje);
		} catch (ServiceRarasCLMException ex) {
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje(String.format(ex.getMessage()));
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, mensaje);
			log.error(ex.getMessage());
		}
		return "redirect:/admin/enfermedades/enfrara/edit/" + enf.getEnfermedadRaraId();
	}

//	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
//	public String setupShowForm(@PathVariable String id, Model model) {
//		getRoute().setId(id);
//		EnfermedadRara enfRara = enfermedadService.getEnfermedadRaraById(id);
//		model.addAttribute("enfermedadRara", enfRara);
//		return "enfermedad/shows/enfRara";
//	}

//	@RequestMapping(value = "/json", produces = "application/json; charset=UTF-8")
//	public @ResponseBody List<EnfermedadCodigoLiteral> json() {
//		return enfermedadService.getListCodLiteral();
//	}
//	
//	@RequestMapping(value = "/json/{id}", produces = "application/json; charset=UTF-8")
//	public @ResponseBody EnfermedadRara jsonId(@PathVariable String id) {
//		return enfermedadService.getEnfermedadRaraById(id);
//	}
	
}