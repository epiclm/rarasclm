/*
 * @autor Ricardo Ortega [rortega@externas.jccm.es]
 *  
 */
package es.jclm.cs.rarasclm.entities;

// TODO: Auto-generated Javadoc
/**
 * The Class RouteParameters.
 */
public class RouteParameters {
	
	private static String VISIBILIDAD_ADMINISTRADOR = "admin";
	private static String VISIBILIDAD_USUARIO = "usuario";
	
	//** Introduzco la visibilidad para distiguir entre la parte general y la de administración"
	private String visibilidad;
	
	/** The modulo. */
	private String modulo;
	
	/** The formulario. */
	private String entidad;
	
	/** The accion. */
	private String accion;
	
	/** The id. */
	private String id;
	
	/** The id2. */
	private String id2;
	
	/** The base url. */
	private String baseUrl;
	


	
	/**
	 * Gets the base url.
	 *
	 * @return the base url
	 */
	public String getBaseUrl() {
		return baseUrl;
	}
	
	/**
	 * Sets the base url.
	 *
	 * @param baseUrl
	 *            the new base url
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	/**
	 * Gets the modulo.
	 *
	 * @return the modulo
	 */
	public String getModulo() {
		return modulo;
	}
	
	/**
	 * Sets the modulo.
	 *
	 * @param modulo
	 *            the new modulo
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
	/**
	 * Gets the formulario.
	 *
	 * @return the entidad
	 */
	public String getEntidad() {
		return entidad;
	}
	
	/**
	 * Sets the formulario.
	 *
	 * @param formulario
	 *            the new entidad
	 */
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Sets the accion.
	 *
	 * @param accion
	 *            the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}

	public boolean isAdmin() {
		if(this.visibilidad.equals(VISIBILIDAD_ADMINISTRADOR))
			return true;
		else
			return false;
	}
}
