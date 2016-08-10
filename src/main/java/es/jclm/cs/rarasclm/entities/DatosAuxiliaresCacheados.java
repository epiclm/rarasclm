/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


// TODO: Auto-generated Javadoc
/**
 * The Class DatosAuxiliaresCacheados.
 */
/**
 * @author rrog14
 *
 */
public class DatosAuxiliaresCacheados {
	
	/** The log. */
	static Log log = LogFactory.getLog(DatosAuxiliaresCacheados.class.getName());

	/** The cie9mcs. */
	private List<EnfermedadCie9mc> cie9mcs;
	
	/** The cie10s. */
	private List<EnfermedadCie10> cie10s;
	
	/** The raras clm. */
	private List<EnfermedadRara> rarasClm;
	
	/** Municipios. */
	private List<Municipio> municipios;
	
	/**Map de Municipios */
	private Map<String,List<Municipio>> municipiosMapProvincia;
	
	/** Provincias. */
	private List<Provincia> provincias;
	
	/** Provincias. */
	private List<Provincia> provinciasCLM;
	
	/** CCAA */
	private List<Ccaa> ccaas;
	
	private Map<Integer,String> basesDiagnosticas;
	
	private List<Hospital> hospitales;
	
	
	public DatosAuxiliaresCacheados() {
		log.info("Se instancia cache de datos y tablas auxiliares");
		
		///////////////////////
		//Base del Diagnóstico
		///////////////////////
		
		
		//1= Existencia de “evidencia” objetiva de enfermedad (pruebas genéticas, bioquímicas, de imagen, etc.) o forma parte de un registro estandarizado.
		
		//2= Verificación (validación) del diagnóstico en HC. No se incluirán en esta categoría aquellos casos obtenidos directamente de HC de atención primaria 
		//u hospital en una carga masiva de datos si no han sido revisados (en este caso se codificarían como 6).
		
		//3= Primera vez que consta en CMBD como C1. 
		
		//4= Primera vez que consta en CMBD como C2 o sucesivos.
		
		//5= Otras fuentes de información que no incluyan en sí mismas la validación del diagnóstico (renales, mortalidad).
		
		//6 = Diagnóstico en la HC de atención primaria u hospital obtenido por carga masiva de datos, no revisado (pendiente de validación).
		
		//7 = Se ha validado pero no se ha alcanzado una determinación final (caso dudoso, no se puede confirmar ni refutar).
		
		//8 = Tras la validación se está seguro de que no es una enfermedad rara.
		
		
		basesDiagnosticas = new TreeMap<Integer,String>();
		
		basesDiagnosticas.put(1, "1 Existencia de \"Evidencia\" Objetiva");
		basesDiagnosticas.put(2, "2 Validación del diagnóstico en HC");
		basesDiagnosticas.put(3, "3 Primera vez que consta en CMBD como C1");
		basesDiagnosticas.put(4, "4 Primera vez que consta en CMBD como C2 o sucesivos");
		basesDiagnosticas.put(5, "5 Otras fuentes de información sin validación del diagnóstico");
		basesDiagnosticas.put(6, "6 Diagnóstico en la HC por carga masiva de datos");
		basesDiagnosticas.put(7, "7 Se ha validado pero no se ha alcanzado una determinación final");
		basesDiagnosticas.put(8, "8 Tras la validación se está seguro de que no es una enfermedad rara");
		basesDiagnosticas.put(9, "9 DESCONOCIDO");
		
		log.info("Se crean datos auxiliares menores (Sin tabla en base de datos) : Bases del diagnóstico.");
		
		
	}

	/**
	 * Gets the cie9mcs.
	 *
	 * @return the cie9mcs
	 */
	public List<EnfermedadCie9mc> getCie9mcs() {
		return cie9mcs;
	}

	/**
	 * Gets the cie10s.
	 *
	 * @return the cie10s
	 */
	public List<EnfermedadCie10> getCie10s() {
		return cie10s;
	}

	/**
	 * Gets the enf raras.
	 *
	 * @return the enf raras
	 */
	public List<EnfermedadRara> getEnfRaras() {
		return rarasClm;
	}

	/**
	 * Sets the cie9mcs.
	 *
	 * @param cie9mcs
	 *            the new cie9mcs
	 */
	public void setCie9mcs(List<EnfermedadCie9mc> cie9mcs) {
		this.cie9mcs = cie9mcs;
	}

	/**
	 * Sets the cie10s.
	 *
	 * @param cie10s
	 *            the new cie10s
	 */
	public void setCie10s(List<EnfermedadCie10> cie10s) {
		this.cie10s = cie10s;
	}

	/**
	 * Sets the enf raras.
	 *
	 * @param raras
	 *            the new enf raras
	 */
	public void setEnfRaras(List<EnfermedadRara> raras) {
		this.rarasClm = raras;
	}

	/**
	 * Gets the cie9mcs by id.
	 *
	 * @param cie9
	 *            the cie9
	 * @return the cie9mcs by id
	 */
	public EnfermedadCie9mc getCie9mcsById(String cie9) {
		for (EnfermedadCie9mc enfermedadRaraCie9mc : cie9mcs) {
			if (enfermedadRaraCie9mc.getCie9Id().equalsIgnoreCase(cie9))
				return enfermedadRaraCie9mc;
		}
		return null;
	}

	/**
	 * Gets the cie10s by id.
	 *
	 * @param cie10
	 *            the cie10
	 * @return the cie10s by id
	 */
	public EnfermedadCie10 getCie10sById(String cie10) {
		for (EnfermedadCie10 enfermedadRaraCie10 : cie10s) {
			if (enfermedadRaraCie10.getCie10Id().equalsIgnoreCase(cie10))
				return enfermedadRaraCie10;
		}
		return null;
	}

	/**
	 * Gets the enf rara by id.
	 *
	 * @param cod
	 *            the cod
	 * @return the enf rara by id
	 */
	public EnfermedadRara getEnfRaraById(String cod) {
		for (EnfermedadRara enfRara : rarasClm) {
			if (enfRara.getEnfermedadRaraId().equalsIgnoreCase(cod))
				return enfRara;
		}
		return null;
	}

	public List<EnfermedadRara> getRarasClm() {
		return rarasClm;
	}

	public void setRarasClm(List<EnfermedadRara> rarasClm) {
		this.rarasClm = rarasClm;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public List<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

	public List<Ccaa> getCcaas() {
		return ccaas;
	}

	public void setCcaas(List<Ccaa> ccaas) {
		this.ccaas = ccaas;
	}

	public Map<String, List<Municipio>> getMunicipiosMapProvincia() {
		return municipiosMapProvincia;
	}

	public void setMunicipiosMapProvincia(Map<String, List<Municipio>> municipiosMapProvincia) {
		this.municipiosMapProvincia = municipiosMapProvincia;
	}

	public List<Provincia> getProvinciasCLM() {
		return provinciasCLM;
	}

	public void setProvinciasCLM(List<Provincia> provinciasCLM) {
		this.provinciasCLM = provinciasCLM;
	}
	
	public String getProvinciaDeno(String provincia) {
		String idProvincia="99";
		if(provincia!=null && provincia.length()==2) {
			idProvincia=provincia;
		}
			for(Provincia p : this.provincias) {
				if(p.getProvincia().equalsIgnoreCase(idProvincia))
					return p.getDeno();
			}
		return "";
	}
	
	public String getMunipioDeno(String municipio) {
		String idMunicipio="99999";
		if(municipio!=null && municipio.trim().length()==5) {
			idMunicipio=municipio;
		}
		String provincia = idMunicipio.substring(0, 2);
		for(Provincia p : this.provincias) {
			if(p.getProvincia().equalsIgnoreCase(provincia))
				for(Municipio m : this.getMunicipios()) {
					if(m.getMunicipio().equalsIgnoreCase(idMunicipio))
						return m.getDeno();
				}
		}
		return "";
	}
	
	public String getMunipioProvinciaDeno(String municipio) {
		String idMunicipio="99999";
		if(municipio!=null && municipio.trim().length()==5) {
			idMunicipio=municipio;
		}
		String provincia = idMunicipio.substring(0, 2);
		for(Provincia p : this.provincias) {
			if(p.getProvincia().equalsIgnoreCase(provincia))
				for(Municipio m : this.getMunicipios()) {
					if(m.getMunicipio().equalsIgnoreCase(idMunicipio))
						return m.getDeno() + " (" + p.getDeno() + ")"; 
				}
		}
		return "";
	}
	
	
	public String getSexoLiteral(int codSexo) {
		switch(codSexo) {
			case 1:
				return "VARÓN";
			case 6:
				return "MUJER";
			case 8:
				return "INDETERMINADO";
			case 9:
				return "DESCONOCIDO";
			default:
				return "";
		}
	}
	
	
	public int getEdad(Date fecNac) {
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaNac = new GregorianCalendar();
		fechaNac.setTime(fecNac);
		int fechaActualDia  = fechaActual.get(Calendar.DATE);
		int fechaActualMes  = fechaActual.get(Calendar.MONTH);
		int fechaActualAnio = fechaActual.get(Calendar.YEAR);
		int fechaNacimientoDia  = fechaNac.get(Calendar.DATE);
		int fechaNacimientoMes  = fechaNac.get(Calendar.MONTH);
		int fechaNacimientoAnio = fechaNac.get(Calendar.YEAR);
		int edad= fechaActualAnio - fechaNacimientoAnio - 1;
		if(fechaActualMes > fechaNacimientoMes) {
			edad++; 
			} else if(fechaActualMes==fechaNacimientoMes) {
				if(fechaActualDia>=fechaNacimientoDia) {
					edad++;
				}
			}	
		return edad;
	}
	
	public String getBaseDiagnosticoLiteral(int codBaseDiagnostico) {
		String ret = this.getBasesDiagnosticas().get(codBaseDiagnostico);
		return ret;
	}

	public Map<Integer, String> getBasesDiagnosticas() {
		return basesDiagnosticas;
	}

	public void setBasesDiagnosticas(Map<Integer, String> basesDiagnosticas) {
		this.basesDiagnosticas = basesDiagnosticas;
	}

	public List<Hospital> getHospitales() {
		return hospitales;
	}

	public void setHospitales(List<Hospital> hospitales) {
		this.hospitales = hospitales;
	}
	
	
	

}
