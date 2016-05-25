package es.jclm.cs.rarasclm.entities;

public class AccionRespuesta {
	
	private String mensaje;
	private boolean error;
	private boolean success;
	private boolean warning;
	private String id;

	
	public AccionRespuesta() {
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



}
