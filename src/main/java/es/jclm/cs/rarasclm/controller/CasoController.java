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
import es.jclm.cs.rarasclm.entities.CasoRevisionUsuario;
import es.jclm.cs.rarasclm.entities.CasoRevisionUsuarioId;
import es.jclm.cs.rarasclm.entities.EnfermedadRara;
import es.jclm.cs.rarasclm.entities.IBaseModel;
import es.jclm.cs.rarasclm.entities.MensajeResultado;
import es.jclm.cs.rarasclm.entities.MensajeTipo;
import es.jclm.cs.rarasclm.entities.MergeResult;
import es.jclm.cs.rarasclm.entities.Paciente;
import es.jclm.cs.rarasclm.entities.UserRarasClm;
import es.jclm.cs.rarasclm.service.CasoService;
import es.jclm.cs.rarasclm.service.EnfermedadRaraService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;
import es.jclm.cs.rarasclm.service.PacienteService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;
import es.jclm.cs.rarasclm.service.CasoRevisionService;
import es.jclm.cs.rarasclm.util.MergeEntity;
import es.jclm.cs.rarasclm.util.RarasClmConstantes;

@Controller
@RequestMapping("/casos/caso")
@RarasClmItemModulo(caption="Caso",deno="Caso",modulo="casos",orden=3)
@RarasClmItemMenu(caption="Caso",deno="Caso",modulo="casos",orden=1)
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
	private CasoRevisionService casoRevisionService;
	
	@Autowired
	public IBaseModel base;
	
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
		if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_CASO_SESION)==null) {
			return "casos/index-casos";
		} else {
			Caso c = (Caso)request.getSession().getAttribute(RarasClmConstantes.OBJETO_CASO_SESION);
			return String.format("redirect:/casos/caso/edit/%s",c.getIdCaso());
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
		if(request.getSession().getAttribute(RarasClmConstantes.OBJETO_CASO_SESION)!=null) {
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_CASO_SESION, null);
		}
		return "redirect:/casos/caso";
	}

	///////////////////////////////////
	// MOSTRAR CASO EN JSON
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
	//NUEVO CASO SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/nuevo/{idPaciente}", method = RequestMethod.GET)
	public String nuevoPacienteGet(@PathVariable String idPaciente, Model model) {
		try {
			int id = Integer.parseInt(idPaciente);
			Paciente paciente = pacienteServicio.Buscar(id);
			Caso caso = new Caso();
			short numCaso = pacienteServicio.getNumNuevoCaso(id);
			caso.setBaseDiagnostico((byte)9);
			caso.setHospital("999999");
			caso.setNumCaso(numCaso);
			caso.setFamiliaresEnfermedadesRaras((byte)9);
			caso.setOtrasEnfermedadesCronicas((short)9);
			EnfermedadRara enfDesconocida = enfermedadService.Buscar("9999999999");
			caso.setEnfermedadRara(enfDesconocida);
			caso.setPaciente(paciente);
			model.addAttribute("caso",caso);
		} catch(Exception ex) {
			log.error(ex.getMessage());
			return "redirect:/casos/caso";
		}
		return "casos/forms/nuevo-caso";
	}
	
	///////////////////////////////////
	//NUEVO CASO SEND POST
	///////////////////////////////////
	@RequestMapping(value = "/nuevo/{idPaciente}", method = RequestMethod.POST)
	public String nuevoPacientePost(@PathVariable String idPaciente, @ModelAttribute("caso") Caso caso, Model model) {

		MensajeResultado mensaje = new MensajeResultado();
		//Validaciones de varios campos
		if(caso.getEnfermedadRara().getEnfermedadRaraId().equals("9999999999")
				&& ( caso.getCodCie10().equals("") && caso.getCodCie9mc().equals("")) ) {
			try {
				int id = Integer.parseInt(idPaciente);
				Paciente paciente = pacienteServicio.Buscar(id);
				caso.setPaciente(paciente);
			} catch (ServiceRarasCLMException ex) {
				log.error(ex.getMessage());
				mensaje.setTipo(MensajeTipo.ERROR);
				mensaje.setMensaje(String.format("ERROR: %s",ex.getMessage()));
				request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION, mensaje);
			}
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje("ERROR: Debe indicar un código de Enfermedad Rara o un código CIE9MC o CIE10");
			//no cargamos mensaje en session porque no vamos a realizar redirect
			//request.getSession().setAttribute("mensaje", mensaje);
			base.setMensaje(mensaje);
			model.addAttribute("caso", caso);
			return "casos/forms/nuevo-caso"; 
		} else {
			try {
				int id = Integer.parseInt(idPaciente);
				Paciente paciente = pacienteServicio.Buscar(id);
				caso.setIdCaso(String.format("%07d-%02d",id,caso.getNumCaso()));
				caso.setPaciente(paciente);
				servicio.Guardar(caso);
				mensaje.setTipo(MensajeTipo.OK);
				mensaje.setMensaje(String.format("El caso %s se ha guardado correctamente",caso.getIdCaso()));
				request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION,mensaje);
			} catch (ServiceRarasCLMException ex) {
				log.error(ex.getMessage());
				mensaje.setTipo(MensajeTipo.ERROR);
				mensaje.setMensaje(String.format("ERROR: %s",ex.getMessage()));
				request.getSession().setAttribute("mensaje", mensaje);
			}
		}
		return "redirect:/casos/caso/edit/" + caso.getIdCaso();
	}
	
	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevoPacienteBaseGet(@ModelAttribute("caso") Caso caso, Model model) {
		return "redirect:/casos/caso/";
	}


	///////////////////////////////////
	//EDICIÓN DE UN CASO SEND GET
	///////////////////////////////////
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String  editPacienteEnviaFormulario(@PathVariable String id, Model model) {
		Caso caso;
		try {	
			caso = servicio.Buscar(id);
			if(caso==null)
				return "redirect:/casos/caso/";
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_CASO_SESION,caso);
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION, caso.getPaciente());
			caso = (Caso)request.getSession().getAttribute(RarasClmConstantes.OBJETO_CASO_SESION);
			model.addAttribute("caso", caso);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(),ex);
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "casos/forms/edit-caso";
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
			
			//referencia a caso con persistencia
			caso = casoMerge.getMergeObject();
	
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("<p><b>ACTUALIZACIÓN CORRECTA</b></p>caso %07d%n (%s)</p>\n",
					casoSinEditar.getPaciente().getIdPaciente(),casoSinEditar.getNumCaso()));

			if(casoRevisionService.isCasoRevisionSet(caso.getIdCaso(), user.getUsername())) {
				//El caso estaba para revision. Se completa
				CasoRevisionUsuario casoRevision = casoRevisionService.getCasoRevision(caso.getIdCaso(), user.getUsername());
				casoRevision.setRevisado(true);
				casoRevision.setFechaRevision(new Date());
				casoRevisionService.Actualizar(casoRevision);
			} else {
				CasoRevisionUsuario casoRevision = new CasoRevisionUsuario();
				CasoRevisionUsuarioId idRevision = new CasoRevisionUsuarioId();
				idRevision.setCaso(caso.getIdCaso());
				idRevision.setNumRev(casoRevisionService.getNumUltimaRevision(caso.getIdCaso(), user.getUsername())+1);
				idRevision.setUsuario(user.getUsername());
				casoRevision.setId(idRevision);
				Date fecha = new Date();
				casoRevision.setFechaCreacion(fecha);
				casoRevision.setFechaRevision(fecha);
				casoRevision.setCaso(caso);
				casoRevision.setRevisado(true);
				casoRevisionService.Guardar(casoRevision);
			}
			
			request.getSession().setAttribute(RarasClmConstantes.REVISADO_CASOS, true);
				
			//Esta acción genera un mensaje al usuario
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(sb.toString());
			request.getSession().setAttribute("mensaje",mensaje);
			caso = servicio.Buscar(id);
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_CASO_SESION,caso);
			
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			return null; //TO DO Mandar mensaje de error a la vista
		}
		return "redirect:/casos/caso/edit/"+id;
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
			
			//Guardamos de nuevo el paciente en sesión para que refleje el cambio.
			Paciente paciente = pacienteServicio.Buscar(caso.getPaciente().getIdPaciente());
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_PACIENTE_SESION,paciente);
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_CASO_SESION,null);	
			
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setTipo(MensajeTipo.OK);
			mensaje.setMensaje(sb.toString());
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION,mensaje);
				
		} catch (Exception ex) {
			log.error(ex.getMessage(),ex);
			MensajeResultado mensaje = new MensajeResultado();
			mensaje.setTipo(MensajeTipo.ERROR);
			mensaje.setMensaje(ex.getMessage());
			request.getSession().setAttribute(RarasClmConstantes.OBJETO_MENSAJE_SESION,mensaje);
		}
		return "redirect:/pacientes/paciente/edit/"+ String.valueOf(idPaciente);
	}
	
	
}
