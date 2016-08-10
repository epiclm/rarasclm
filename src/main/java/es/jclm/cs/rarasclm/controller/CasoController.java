package es.jclm.cs.rarasclm.controller;

import java.text.SimpleDateFormat;
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


import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.MergeResult;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.CasoService;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;
import es.jclm.cs.rarasclm.service.PacienteService;
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
	private PacienteService pacienteServicio;
	
	@Autowired 
	private LocalizacionesService localizacionesService;
	
	@Autowired
	HttpServletRequest request;
	
	public static final String OBJETO_CASO_SESION = "caso";
	
	
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
		if(request.getSession().getAttribute(OBJETO_CASO_SESION)==null) {
			return "casos/index-casos";
		} else {
			Caso c = (Caso)request.getSession().getAttribute(OBJETO_CASO_SESION);
			return String.format("redirect:/casos/edit/%s",c.getIdCaso());
		}
	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public String inicioVuelta(Model model, 
//			@ModelAttribute("pacientes") PacientesModelView pacientesMV,
//			SessionStatus status) {
//
//		getRoute().setId("");
//		return "redirect:/casos"; 
//	}
	
	@RequestMapping(value = "/nuevaBusqueda", method = RequestMethod.GET) 
	public String nuevoPaciente() {
		if(request.getSession().getAttribute(OBJETO_CASO_SESION)!=null) {
			request.getSession().setAttribute(OBJETO_CASO_SESION, null);
		}
		return "redirect:/casos";
	}

	///////////////////////////////////
	//MOSTRAR CASO EN JSON
	///////////////////////////////////
	@RequestMapping(value = "/json/{id}", method = RequestMethod.GET)
	public  @ResponseBody Caso showJsonCasoPaciente(@PathVariable String id) {
		try {
			Caso ret = servicio.Buscar(id);
			return ret;
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
			return null; //TO DO Mandar mensaje de error a la vista
		}
	}

	
	///////////////////////////////////
	//EDICIÓN DE UN CASO SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String  editPacienteEnviaFormulario(@PathVariable String id, Model model) {
		Caso caso;
		try {
			if(request.getSession().getAttribute(OBJETO_CASO_SESION)==null) {
				caso = servicio.Buscar(id);
				request.getSession().setAttribute(OBJETO_CASO_SESION,caso);
			} else {
				caso = (Caso)request.getSession().getAttribute(OBJETO_CASO_SESION);
				if(!caso.getIdCaso().equals(id)) {
					//Buscamos ya que se está haciendo una petición de un caso distinto
					//al que tenemeos guardado en sesión
					caso = servicio.Buscar(id);
				}
			}
			model.addAttribute("caso", caso);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
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
			UserRarasClm user = (UserRarasClm)model.asMap().get("userCLM");
			caso.setUsuarioModificacion(user.getUsername());
			caso.setFechahoraModificacion(new Date());
			MergeResult<Caso> casoMerge = new MergeEntity<Caso>().merge(casoSinEditar, caso);
			servicio.Actualizar(casoMerge.getMergeObject());
			
	
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("<p><b>ACTUALIZACIÓN CORRECTA</b></p>caso %07d%n (%s)</p>\n",
					casoSinEditar.getPaciente().getIdPaciente(),casoSinEditar.getNumCaso()));

//			for(FieldChange f : casoMerge.getFieldsChange())
//				sb.append(String.format("\t<p>Se ha cambiado campo %s valor: %s por %s</p>\n",f.getFieldName(),
//						f.getSerializeOldValue(),
//						f.getSerializeNewValue()));
			
			//Esta acción genera un mensaje al usuario
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(sb.toString());
			request.getSession().setAttribute("mensaje",mensaje);
			caso = servicio.Buscar(id);
			request.getSession().setAttribute(OBJETO_CASO_SESION,caso);
			
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "redirect:/casos/edit/"+id;
	}
	
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String  deletePacienteRecibeFormulario(@PathVariable String id, Model model) {
		int idPaciente = -1;
		try {
			Caso caso = servicio.Buscar(id);
			idPaciente = caso.getPaciente().getIdPaciente();
			UserRarasClm user = (UserRarasClm)model.asMap().get("userCLM");
			caso.setUsuarioModificacion(user.getUsername());
			caso.setFechahoraModificacion(new Date());
			
			servicio.saveHistoriaBorrada(caso);
		
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("<p><b>ELIMINACIÓN CORRECTA</b></p>caso %07d%n (%s)</p>\n",caso.getPaciente().getIdPaciente(),caso.getNumCaso()));

			servicio.Borrar(caso);
			
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(sb.toString());
			request.getSession().setAttribute("mensaje",mensaje);
			request.getSession().setAttribute(OBJETO_CASO_SESION,null);		
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "redirect:/pacientes/edit/"+ String.valueOf(idPaciente);
	}
	
	
}
