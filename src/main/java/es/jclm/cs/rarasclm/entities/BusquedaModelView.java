package es.jclm.cs.rarasclm.entities;

import java.util.Date;
import java.util.List;

public class BusquedaModelView {

	private List<Paciente> pacientes;
	private List<Caso> casos;

	private String busquedaNombre;
	private String busquedaApellido1;
	private String busquedaApellido2;
	private String busquedaCIP;
	private String busquedaProvincia;
	private String busquedaMunicipio;
	private Date busquedaFechaNacimiento;
	private String busquedaEnfermedadRaraCLM;
	private String busquedaCie9MC;
	private String busquedaCie10;
	private String busquedaOmin;
	private String busquedaOrphanet;
	private String busquedaBaseDiagnostica;
	private String busquedaFuenteInformacion;

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Caso> getCasos() {
		return casos;
	}

	public void setCasos(List<Caso> casos) {
		this.casos = casos;
	}

	public String getBusquedaNombre() {
		return busquedaNombre;
	}

	public void setBusquedaNombre(String busquedaNombre) {
		this.busquedaNombre = busquedaNombre;
	}

	public String getBusquedaApellido1() {
		return busquedaApellido1;
	}

	public void setBusquedaApellido1(String busquedaApellido1) {
		this.busquedaApellido1 = busquedaApellido1;
	}

	public String getBusquedaApellido2() {
		return busquedaApellido2;
	}

	public void setBusquedaApellido2(String busquedaApellido2) {
		this.busquedaApellido2 = busquedaApellido2;
	}

	public String getBusquedaCIP() {
		return busquedaCIP;
	}

	public void setBusquedaCIP(String busquedaCip) {
		this.busquedaCIP = busquedaCip;
	}

	public String getBusquedaProvincia() {
		return busquedaProvincia;
	}

	public void setBusquedaProvincia(String provincia) {
		this.busquedaProvincia = provincia;
	}

	public String getBusquedaMunicipio() {
		return busquedaMunicipio;
	}

	public void setBusquedaMunicipio(String municipio) {
		this.busquedaMunicipio = municipio;
	}

	public Date getBusquedaFechaNacimiento() {
		return busquedaFechaNacimiento;
	}

	public void setBusquedaFechaNacimiento(Date busquedaFechaNacimiento) {
		this.busquedaFechaNacimiento = busquedaFechaNacimiento;
	}

	public String getBusquedaEnfermedadRaraCLM() {
		return busquedaEnfermedadRaraCLM;
	}

	public void setBusquedaEnfermedadRaraCLM(String busquedaEnfermedadRaraCLM) {
		this.busquedaEnfermedadRaraCLM = busquedaEnfermedadRaraCLM;
	}

	public String getBusquedaCie9MC() {
		return busquedaCie9MC;
	}

	public void setBusquedaCie9MC(String busquedaCie9MC) {
		this.busquedaCie9MC = busquedaCie9MC;
	}

	public String getBusquedaCie10() {
		return busquedaCie10;
	}

	public void setBusquedaCie10(String busquedaCie10) {
		this.busquedaCie10 = busquedaCie10;
	}

	public String getBusquedaOmin() {
		return busquedaOmin;
	}

	public void setBusquedaOmin(String busquedaOmin) {
		this.busquedaOmin = busquedaOmin;
	}

	public String getBusquedaOrphanet() {
		return busquedaOrphanet;
	}

	public void setBusquedaOrphanet(String busquedaOrphanet) {
		this.busquedaOrphanet = busquedaOrphanet;
	}

	public String getBusquedaBaseDiagnostica() {
		return busquedaBaseDiagnostica;
	}

	public void setBusquedaBaseDiagnostica(String busquedaBaseDiagnostica) {
		this.busquedaBaseDiagnostica = busquedaBaseDiagnostica;
	}

	public String getBusquedaFuenteInformacion() {
		return busquedaFuenteInformacion;
	}

	public void setBusquedaFuenteInformacion(String busquedaFuenteInformacion) {
		this.busquedaFuenteInformacion = busquedaFuenteInformacion;
	}
	
	

}
