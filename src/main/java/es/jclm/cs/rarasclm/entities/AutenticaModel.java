package es.jclm.cs.rarasclm.entities;

public class AutenticaModel {

	private boolean error;
	private String mensaje;

	public boolean isError() {
		return error;
	}
	
	public void setError(boolean error) {
		this.error = error;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}