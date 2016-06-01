package es.jclm.cs.rarasclm.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

//Modelo de la vista Pacientes
//Este modelo se usa para la vista princiapal de pacientes.
//Consiste en un formulario de búsquedas de pacientes.
//El resultado de la búsqueda es una lista de pacientes que
//también se muestra en la misma vista. 
//Este modelo se envía al controlador y este lo devuelve
//con la lista de pacientes

public class PacientesModelView extends BaseGrillaModelView<Paciente>{
	
	private List<Paciente> pacientes;
	private String busquedaNombre;
	private String busquedaApellido1;
	private String busquedaApellido2;
	private int busquedaNumeroPaciente;
	private String busquedaCIP;
	private String busquedaProvincia;
	private String busquedaMunicipio;
	private Date busquedaFechaNacimiento;
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
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
	public int getBusquedaNumeroPaciente() {
		return busquedaNumeroPaciente;
	}
	public void setBusquedaNumeroPaciente(int busquedaNumeroPaciente) {
		this.busquedaNumeroPaciente = busquedaNumeroPaciente;
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
	
	
	
}
