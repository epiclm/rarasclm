package es.jclm.cs.rarasclm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.BusquedaModelView;
import es.jclm.cs.rarasclm.entities.Caso;
import es.jclm.cs.rarasclm.service.BusquedaService;
import es.jclm.cs.rarasclm.service.LocalizacionesService;

@Controller
@RequestMapping("/busqueda")
@RarasClmItemModulo(caption="Búsqueda",deno="Búsqueda",modulo="busqueda",orden=1)
@RarasClmItemMenu(caption="Búsqueda",deno="Búsqueda",modulo="busqueda",orden=1)
@SessionAttributes("busqueda")
public class BusquedaController extends BaseController {
	
	public static final String OBJETO_BUSQUEDA_SESION="busqueda";
	
	@Autowired
	LocalizacionesService servicioLocalizaciones;
	
	@Autowired
	BusquedaService busquedaService;
	
	@Autowired
	HttpServletRequest request;

	///////////////////////////////////
	// BÚSQUEDA DE UN CASO SEND GET
	///////////////////////////////////
	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		
		BusquedaModelView busquedaModel;
		
		if(request.getSession().getAttribute(OBJETO_BUSQUEDA_SESION)==null)
			busquedaModel = new BusquedaModelView();
		else
			busquedaModel = (BusquedaModelView)request.getSession().getAttribute(OBJETO_BUSQUEDA_SESION);
		
		
		model.addAttribute("busqueda", busquedaModel);
		
		if(busquedaModel.getMunicipio().length()==5 && busquedaModel.getMunicipio().equals("99999"))
			model.addAttribute("municipiosProvinciaResidencia",servicioLocalizaciones.getMunicipioDeProvincia(busquedaModel.getMunicipio().substring(0, 2)));
		
		return "busqueda/index-busqueda";
	}
	
	
	/////////////////////////////////////
	// BÚSQUEDA DE UN CASO SUBMIT POST
	/////////////////////////////////////
	@RequestMapping(method = RequestMethod.POST)
	public String busquedaSubmit(Model model, @ModelAttribute("busqueda") BusquedaModelView busquedaModel) {
				
		model.addAttribute("busqueda", busquedaModel);
		
		request.getSession().setAttribute(OBJETO_BUSQUEDA_SESION,busquedaModel);
		
		busquedaModel.setCasos(busquedaService.buscaCasos(busquedaModel));
		
		if(busquedaModel.getMunicipio().length()==5 && busquedaModel.getMunicipio().equals("99999"))
			model.addAttribute("municipiosProvinciaResidencia",servicioLocalizaciones.getMunicipioDeProvincia(busquedaModel.getMunicipio().substring(0, 2)));
		
		
		return "redirect:busqueda";
	}
	
	
	
}
