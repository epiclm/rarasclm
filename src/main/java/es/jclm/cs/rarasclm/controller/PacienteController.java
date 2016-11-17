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

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.MergeResult;
import es.jclm.cs.rarasclm.entities.Municipio;
import es.jclm.cs.rarasclm.entities.NuevoPacienteModelView;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.service.LocalizacionesService;
import es.jclm.cs.rarasclm.service.PacienteService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.util.MergeEntity;
import es.jclm.cs.rarasclm.util.RarasClmConstantes;


@Controller
@RequestMapping("/pacientes/paciente")
@RarasClmItemModulo(caption="Paciente",deno="Paciente",modulo="pacientes",orden=2)
@RarasClmItemMenu(caption="Paciente",deno="Paciente",modulo="pacientes",orden=1)
public class PacienteController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(PacienteController.class);
	
	@Autowired
	PacienteService servicio;
	
	@Autowired
	LocalizacionesService servicioLocalizaciones;
	
	@Autowired
	HttpServletRequest request;
		
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
	    binder.registerCustomEditor(Date.class, editor);
	}
	
	// Página inicial del módulo
	@RequestMapping(method = RequestMethod.GET)
	public String inicioEnvio(Model model) {
		if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION)==null) {
			return "pacientes/index-pacientes";
		} else {
			Paciente p = (Paciente)request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION);
			return String.format("redirect:/pacientes/paciente/edit/%d",p.getIdPaciente());
		}
	}
	
	@RequestMapping(value = "/nuevaBusqueda", method = RequestMethod.GET) 
	public String nuevoPaciente() {
		if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION)!=null) {
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION, null);
		}
		if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_CASO_SESION)!=null) {
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_CASO_SESION, null);
		}
		return "redirect:/pacientes/paciente";
	}
	
	
	///////////////////////////////////
	// PACIENTE EN JSON
	///////////////////////////////////
	@RequestMapping(value = "/json/{id}", method = RequestMethod.GET)
	public  @ResponseBody Paciente showJsonPaciente(@PathVariable String id) {
		try {
			Integer clave = Integer.valueOf(id);
			Paciente ret = servicio.Buscar(clave);
			return ret;
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage());
			return null; //TO DO Mandar mensaje de error a la vista
		}
	}
	
	
	///////////////////////////////////
	//EDICIÓN DE UN PACIENTE SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String  editPaciente(@PathVariable String id, Model model) {
		Paciente paciente;
		Integer clave = Integer.valueOf(id);
		try {
			if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION)==null) {
				paciente = servicio.Buscar(clave);
				request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION,paciente);
			} else {
				paciente = (Paciente)request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION);
				if(paciente.getIdPaciente()!=clave) {
					//Buscamos ya que se está haciendo una petición de un paciente distinto
					//al que tenemeos guardado en sesión
					paciente = servicio.Buscar(clave);
					request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION,paciente);
				}
			} 
			model.addAttribute("paciente", paciente);
			List<Municipio> municipiosResidencia = new ArrayList<Municipio>();
			Municipio municipioDesconocido = new Municipio();
			municipioDesconocido.setMunicipio("99999");
			municipioDesconocido.setDeno("DESCONOCIDO");
			municipiosResidencia.add(municipioDesconocido);
			if(paciente.getMunicipioResidencia()!=null && paciente.getMunicipioResidencia().trim().length()==5) {
				municipiosResidencia.addAll(servicioLocalizaciones.getMunicipioDeProvincia(paciente.getMunicipioResidencia().substring(0, 2)));
			} else {
				municipiosResidencia.addAll(servicioLocalizaciones.getMunicipioDeProvincia("99"));
			}				
			model.addAttribute("municipiosProvinciaResidencia",municipiosResidencia);
		} catch (ServiceRarasCLMException ex) {
			ex.printStackTrace();
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "pacientes/forms/edit-paciente";
	}
	
	
	///////////////////////////////////
	//EDICIÓN DE UN PACIENTE SEND POST
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String  submitPaciente(@PathVariable String id, @ModelAttribute("paciente") Paciente paciente, Model model) {
		MensajeResultado mensaje = new MensajeResultado();
		try {		
			int pacienteId = Integer.parseInt(id);
			Paciente pacienteSinEditar = servicio.Buscar(pacienteId);
			MergeResult<Paciente> pacienteMerge = new MergeEntity<Paciente>().merge(pacienteSinEditar,paciente);
			servicio.Actualizar(pacienteMerge.getMergeObject());
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("<p><b>ACTUALIZACIÓN CORRECTA</b></p>paciente %07d%n</p>\n",pacienteSinEditar.getIdPaciente()));
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(sb.toString());
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION,mensaje);
			paciente = servicio.Buscar(pacienteId);
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION,paciente);		
		} catch(Exception ex) {
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje(ex.getMessage());
			log.error(ex.getMessage(),ex);
		}
		return "redirect:/pacientes/paciente/edit/{id}";
	}
	
	///////////////////////////////////
	//NUEVO PACIENTE SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String  nuevoPacienteGet(Model model) {
		NuevoPacienteModelView nuevoPaciente = null;
		if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_PRE_SESSION)!=null) {
			nuevoPaciente = (NuevoPacienteModelView)request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_PRE_SESSION);
		} else {
			nuevoPaciente = new NuevoPacienteModelView();
			nuevoPaciente.setSexo('9');
		}
		model.addAttribute("nuevo", nuevoPaciente);
		return "pacientes/forms/nuevo-paciente-pre";
	}
	
	///////////////////////////////////
	//NUEVO PACIENTE SEND POST
	///////////////////////////////////
	@RequestMapping(value = "/nuevo", method = RequestMethod.POST)
	public String  nuevoPacientePost(Model model,  @ModelAttribute("nuevo") NuevoPacienteModelView nuevoPaciente) {
		MensajeResultado mensaje = new MensajeResultado();
		try {
			nuevoPaciente = servicio.busquedaBusquedaPre(nuevoPaciente);
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_PRE_SESSION,nuevoPaciente);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage());
			mensaje.setMensaje(ex.getMessage());
			mensaje.setTipo(MensajeTipo.ERROR);
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION,mensaje);
		}
		
		return "pacientes/forms/nuevo-paciente-pre";
	}
	
	
	///////////////////////////////////
	//NUEVO POSTERIOR PACIENTE SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/nuevo/posterior", method = RequestMethod.GET)
	public String  nuevoPacientePosteriorGet(Model model) {
		NuevoPacienteModelView nuevoPaciente = null;
		Paciente paciente = null;
		//Sigue el mismo patrón con la diferencia de que a esta url solamente se
		//continua si se ha completado el paso previo
		if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_NUEVO_SESSION)!=null) {
			paciente = (Paciente)request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_NUEVO_SESSION);
			return "pacientes/forms/nuevo-paciente";
		}
		else if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_PRE_SESSION)!=null) {
			nuevoPaciente = (NuevoPacienteModelView)request.getSession().getAttribute(RarasClmConstantes.OBJETO_PACIENTE_PRE_SESSION);
			paciente = new Paciente();
			paciente.setApellido01(nuevoPaciente.getApellido01());
			paciente.setApellido02(nuevoPaciente.getApellido02());
			paciente.setNombre(nuevoPaciente.getNombre());
			paciente.setFechaNacimiento(nuevoPaciente.getFechaNacimiento());
			paciente.setSexo(nuevoPaciente.getSexo());
			paciente.setCip(nuevoPaciente.getCip());
			model.addAttribute("paciente",paciente);
			return "pacientes/forms/nuevo-paciente";
		} else {
			return "redirect:/pacientes/paciente";
		}
	}
	
	////////////////////////////////////
	//NUEVO POSTERIOR PACIENTE SEND POST
	////////////////////////////////////
	@RequestMapping(value = "/nuevo/posterior", method = RequestMethod.POST)
	public String  nuevoPacientePosteriorPost(Model model, @ModelAttribute("paciente") Paciente paciente) {
		MensajeResultado mensaje = new MensajeResultado();
		try {
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION, mensaje);
			paciente = servicio.saveNuevoPaciente(paciente);
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje("El paciente se ha guardado correctamente");
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION, paciente);
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, mensaje);
			//Borramos la referencia a paciente nuevo para que no vuelva a aparecer.
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_NUEVO_SESSION,null);
			return "redirect:/pacientes/paciente/";

		} catch (Exception ex) {
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje(ex.getMessage());
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, mensaje);
			return "pacientes/forms/nuevo-paciente";
		}
	}
	

}
