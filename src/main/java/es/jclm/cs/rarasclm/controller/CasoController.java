package es.jclm.cs.rarasclm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.EnfermedadRaraCie10;
import es.jclm.cs.rarasclm.entities.Municipios;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.PacientesModelView;
import es.jclm.cs.rarasclm.entities.UserRarasCLM;
import es.jclm.cs.rarasclm.service.CasoService;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.util.MergeEntity;

@Controller
@RequestMapping("/casos")
@RarasClmItemModulo(caption="Casos",deno="Casos",modulo="casos",orden=3)
@RarasClmItemMenu(caption="Casos",deno="Casos",modulo="casos",orden=1)
@SessionAttributes("casos")
public class CasoController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(CasoController.class);

	@Autowired
	private CasoService servicio;
	
	@Autowired 
	private LocalizacionesService localizacionesService;
	
	@Autowired
	HttpServletRequest request;
	
	
	@Autowired
	private EnfermedadRaraService enfermedadService;

	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	@ModelAttribute("allRaras")
	public List<EnfermedadRara> populateAllRaras() {
		List<EnfermedadRara> enfermedadesRaras = enfermedadService.getAllEnfermedadesRaras(true);
		return enfermedadesRaras;
	}
	
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicioEnvio(Model model) {
		getRoute().setId("");
		return "casos/index-casos";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String inicioVuelta(Model model, 
			@ModelAttribute("pacientes") PacientesModelView pacientesMV,
			SessionStatus status) {

		getRoute().setId("");
		return "redirect:/casos"; //Evita nueva consulta a BD al volver atrás con el navegador
								  //o actualizar.
	}
	
	
	@RequestMapping(value = "/show/json/{id}", method = RequestMethod.GET)
	public  @ResponseBody Caso showJsonPaciente(@PathVariable String id) {
		Integer clave = Integer.valueOf(id);
		try {
			Caso ret = servicio.Buscar(id);
			return ret;
		} catch (ServiceRarasCLMException ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
	}
	
	
	///////////////////////////////////
	//EDICIÓN DE UN CASO SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String  editPacienteEnviaFormulario(@PathVariable String id, Model model) {
		try {
			Caso caso = servicio.Buscar(id);
			model.addAttribute("caso", caso);

		} catch (ServiceRarasCLMException ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "casos/forms/casoEdit";
	}
	
	
	///////////////////////////////////
	//EDICIÓN DE UN CASO SUBMIT POST
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String  editPacienteRecibeFormulario(@PathVariable String id, @ModelAttribute("caso") Caso caso, Model model) {
		try {
			Caso casoSinEditar = servicio.Buscar(id);
			servicio.saveHistoria(casoSinEditar);
			UserRarasCLM user = (UserRarasCLM)model.asMap().get("userCLM");
			caso.setUsuarioModificacion(user.getUsername());
			caso.setFechahoraModificacion(new Date());
			Caso casoMerge = new MergeEntity<Caso>().merge(casoSinEditar, caso);
			servicio.Actualizar(casoMerge);
			//model.addAttribute("caso", casoMerge);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "redirect:/casos/edit/"+id;
	}
	
	
}
