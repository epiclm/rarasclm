package es.jclm.cs.rarasclm.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.anotations.RarasClmItemModulo;
import es.jclm.cs.rarasclm.entities.EstadisticaModelCasosBaseDTCO;
import es.jclm.cs.rarasclm.service.EstadisticasService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;


@Controller
@RequestMapping("/admin")
@RarasClmItemModulo(caption="Administración",deno="Administración",modulo="admin",orden=5)
@RarasClmItemMenu(caption="Inicio",deno="Inicio",modulo="admin",orden=1)
public class AdminController extends BaseController {

	@Autowired
	EstadisticasService estadisticaService;
	
	static Log log = LogFactory.getLog(AdminController.class.getName());
	
	@RequestMapping(method = RequestMethod.GET)
	public String indexAdmin(Model model) {
		try {
			List<EstadisticaModelCasosBaseDTCO> estadisticaCasosBaseDTCO = estadisticaService.getBaseDiagnosticoCount();
			model.addAttribute("estadisticaCasosBaseDTCO", estadisticaCasosBaseDTCO);
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(), ex);
		}
		return "admin/index-admin";
	}
	
	@RequestMapping(value="/estadistica/basedtco/json")
	public @ResponseBody List<EstadisticaModelCasosBaseDTCO> getNumCasosPorBaseDTCO() {
		try {
			List<EstadisticaModelCasosBaseDTCO> estadisticaCasosBaseDTCO = estadisticaService.getBaseDiagnosticoCount();
			return estadisticaCasosBaseDTCO;
		} catch (ServiceRarasCLMException ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}
}