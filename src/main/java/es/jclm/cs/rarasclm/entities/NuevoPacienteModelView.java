package es.jclm.cs.rarasclm.entities;

import java.util.Date;
import java.util.List;

public class NuevoPacienteModelView {
	private String apellido01;
	private String apellido02;
	private String nombre;
	private Date fechaNacimiento;
	private String cip;
	private char sexo;
	private List<Paciente> pacientes;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido01() {
		return apellido01;
	}
	public void setApellido01(String apellido01) {
		this.apellido01 = apellido01;
	}
	public String getApellido02() {
		return apellido02;
	}
	public void setApellido02(String apellido02) {
		this.apellido02 = apellido02;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCip() {
		return cip;
	}
	public void setCip(String cip) {
		this.cip = cip;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	

}
