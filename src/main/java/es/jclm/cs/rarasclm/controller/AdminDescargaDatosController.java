package es.jclm.cs.rarasclm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.cellprocessor.FmtBool;
import org.supercsv.cellprocessor.FmtDate;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import es.jclm.cs.rarasclm.anotations.RarasClmItemMenu;
import es.jclm.cs.rarasclm.entities.VPacienteCaso;
import es.jclm.cs.rarasclm.service.DescargasService;
import es.jclm.cs.rarasclm.service.ServiceRarasCLMException;

@Controller
@RequestMapping("/admin/descargas")
@RarasClmItemMenu(caption="Descargas",deno="Descargas",modulo="admin",orden=3)
public class AdminDescargaDatosController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(AdminDescargaDatosController.class);
	
	@Autowired
	public DescargasService servicioDescargas;
	
	
	private static CellProcessor[] getProcessorsVPacienteCaso() {
	        
	        final CellProcessor[] processors = new CellProcessor[] { 
	        		 new NotNull(), //id_caso
	        		 new NotNull(), //id_paciente
	        		 new NotNull(), //num_caso
	        		 new Optional(), //idpacnac
	        		 new Optional(), //cip
	        		 new Optional(), //dni
	        		 new Optional(), //numero_seg_social
	        		 new Optional(), //nombre
	        		 new Optional(), //apellido_01
	        		 new Optional(), //apellido_02
	        		 new Optional(new FmtDate("dd/MM/yyyy")), //fecha_nacimiento
	        		 new Optional(), //sexo
	        		 new Optional(), //provincia_nacimiento
	        		 new Optional(), //municipio_nacimiento
	        		 new Optional(), //pais_nacimiento
	        		 new Optional(), //dom_tipo_via
	        		 new Optional(), //dom_nombre_via
	        		 new Optional(), //dom_numero
	        		 new Optional(), //dom_pisopuerta
	        		 new Optional(), //dom_otros
	        		 new Optional(), //dom_cp
	        		 new Optional(), //provincia_residencia
	        		 new Optional(), //municipio_residencia
	        		 new Optional(), //fallecido
	        		 new Optional(new FmtDate("dd/MM/yyyy")), //fallecido_fecha
	        		 new Optional(), //fallecido_fecha_comprobacion
	        		 new Optional(), //fallecido_etiqueta_comprobacion
	        		 new Optional(), //fallecido_numbol
	        		 new Optional(), //email
	        		 new Optional(), //telefono
	        		 new Optional(), //enfrara
	        		 new Optional(), //declarada
	        		 new Optional(), //usuario_declara
	        		 new Optional(), //literal_enfermedad
	        		 new Optional(), //jucio_clinico
	        		 new Optional(), //observaciones
	        		 new Optional(), //hospital
	        		 new Optional(), //numero_historia_clinica
	        		 new Optional(), //base_diagnostico
	        		 new Optional(), //fuente_informacion
	        		 new FmtBool("1","0"), //fuente_pacientes_A
	        		 new FmtBool("1","0"), //fuente_cmbd_C
	        		 new FmtBool("1","0"), //fuente_defcong_D
	        		 new FmtBool("1","0"), //fuente_edo_E
	        		 new FmtBool("1","0"), //fuente_isociales_G
	        		 new FmtBool("1","0"), //fuente_mhuerf_H
	        		 new FmtBool("1","0"), //fuente_metabol_N
	        		 new FmtBool("1","0"), //fuente_rinv_I
	        		 new FmtBool("1","0"), //fuente_rmort_M
	        		 new FmtBool("1","0"), //fuente_rcancer_T
	        		 new FmtBool("1","0"), //fuente_renal_R
	        		 new FmtBool("1","0"), //fuente_hc_primaria_V
	        		 new FmtBool("1","0"), //fuente_hc_especializada_U
	        		 new FmtBool("1","0"), //fuente_hc_primaria_masiva_P
	        		 new FmtBool("1","0"), //fuente_hc_especializada_masiva_Q
	        		 new FmtBool("1","0"), //fuente_otros_O
	        		 new Optional(new FmtDate("dd/MM/yyyy")), //fecha_inicio_sintomas
	        		 new Optional(new FmtDate("dd/MM/yyyy")), //fecha_deteccion
	        		 new Optional(new FmtDate("dd/MM/yyyy")), //fecha_diagnostico
	        		 new Optional(), //cod_cie9mc
	        		 new Optional(), //cod_cie10
	        		 new Optional(), //cod_snomed
	        		 new Optional(), //cod_omin
	        		 new Optional(), //cod_edta
	        		 new Optional(), //cod_otros
	        		 new Optional(), //cod_otro_deno,
	        		 new Optional(), //tratamiento
	        		 new Optional(), //familiares_enfermedades_raras
	        		 new Optional(), //otras_enfermedades_cronicas
	        		 new Optional(), //enfermedades_cronicas
	        		 new Optional(new FmtDate("dd/MM/yyyy")), //caso_fechahora_creacion
	        		 new Optional(new FmtDate("dd/MM/yyyy")), //caso_fechahora_modificacion
	        		 new Optional(), //caso_usuario_creacion
	        		 new Optional(), //caso_usuario_modificacion
	        };
	        
	        return processors;
	}
	
	private static String[] HeadersVPacienteCaso =  {
			"idCaso",
			"idPaciente",
			"numCaso",
			"idpacnac",
			"cip",
			"dni",
			"numeroSegSocial",
			"nombre",
			"apellido01",
			"apellido02",
			"fechaNacimiento",
			"sexo",
			"provinciaNacimiento",
			"municipioNacimiento",
			"paisNacimiento",
			"domTipoVia",
			"domNombreVia",
			"domNumero",
			"domPisopuerta",
			"domOtros",
			"domCp",
			"provinciaResidencia",
			"municipioResidencia",
			"fallecido",
			"fallecidoFecha",
			"fallecidoFechaComprobacion",
			"fallecidoEtiquetaComprobacion",
			"fallecidoNumbol",
			"email",
			"telefono",
			"enfrara",
			"declarada",
			"usuarioDeclara",
			"literalEnfermedad",
			"jucioClinico",
			"observaciones",
			"hospital",
			"numeroHistoriaClinica",
			"baseDiagnostico",
			"fuenteInformacion",
			"fuentePacientesA",
			"fuenteCmbdC",
			"fuenteDefcongD",
			"fuenteEdoE",
			"fuenteIsocialesG",
			"fuenteMhuerfH",
			"fuenteMetabolN",
			"fuenteRinvI",
			"fuenteRmortM",
			"fuenteRcancerT",
			"fuenteRenalR",
			"fuenteHcPrimariaV",
			"fuenteHcEspecializadaU",
			"fuenteHcPrimariaMasivaP",
			"fuenteHcEspecializadaMasivaQ",
			"fuenteOtrosO",
			"fechaInicioSintomas",
			"fechaDeteccion",
			"fechaDiagnostico",
			"codCie9mc",
			"codCie10",
			"codSnomed",
			"codOmin",
			"codEdta",
			"codOtros",
			"codOtroDeno",
			"tratamiento",
			"familiaresEnfermedadesRaras",
			"otrasEnfermedadesCronicas",
			"enfermedadesCronicas",
			"casoFechahoraCreacion",
			"casoFechahoraModificacion",
			"casoUsuarioCreacion",
			"casoUsuarioModificacion"
			};
		
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String busqueda(Model model) {
		return "admin/descargas/index-descargas";
	}
	
	@RequestMapping(value = "/datosExplotacion" , method = RequestMethod.GET, produces="text/plain")
	@ResponseBody
	public void getDatosExplotacion(HttpServletResponse response) {
		try {
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dateFormatArchivo = new SimpleDateFormat("yyyyMMddhhmm");
			java.util.Date fecha = new java.util.Date();
			String nombreArchivo = String.format("%s_%s.csv","RARASCLM",dateFormatArchivo.format(fecha));
			
			response.setContentType("text/csv");
			response.setHeader("Content-Disposition","attachment; filename=\""+ nombreArchivo + "\"");
			
			List<VPacienteCaso> pacienteCasos = servicioDescargas.getDatosExplotacion();
			
			PrintWriter out = response.getWriter();
			
			CsvPreference csvPreference = new CsvPreference.Builder('"', ';', "\n").build();
			
			CsvBeanWriter csvWriter = new CsvBeanWriter(out,csvPreference);
			
			for(VPacienteCaso pc : pacienteCasos) {
				csvWriter.write(pc,HeadersVPacienteCaso,getProcessorsVPacienteCaso());
			}
			
			
		} catch (ServiceRarasCLMException | IOException ex) {
			log.error(ex.getMessage());
		}
	}
	
}