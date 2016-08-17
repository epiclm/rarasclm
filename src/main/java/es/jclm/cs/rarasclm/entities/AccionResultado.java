package es.jclm.cs.rarasclm.entities;

public class AccionResultado {
	
	private String mensaje;
	private boolean error;
	private boolean success;
	private boolean warning;
	private String id;

	public final static String RESPUESTA_ERROR = "error";
	public final static String RESPUESTA_WARNING = "warning";
	public final static String RESPUESTA_OK= "ok";
	
	public AccionResultado() {
		this.error= false;
		this.success = false;
		this.warning = false;
	}


	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
		this.success = !error;
		this.warning = !error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
		this.error = !success;
	}

	public boolean isWarning() {
		return warning;
	}

	public void setWarning(boolean warning) {
		this.warning = warning;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	
	public String getRespuesta() {
		
		if(error)
			return RESPUESTA_ERROR;
		if(warning)
			return RESPUESTA_WARNING;
		if(success)
			return RESPUESTA_OK;
		
		return "";
	}



}
