package es.jclm.cs.rarasclm.entities;

import java.util.List;

public class BusquedaModelView {

	private List<Paciente> pacientes;
	private List<Caso> casos;

	private String  nombre;
	private String  apellido01;
	private String  apellido02;
	private String  cip;
	private char 	sexo='9';
	private String  provincia="99";
	private String  municipio="99999";
	private String 	anioNacimiento;
	private String  enfermedadRaraCLM;
	private String  cie9MC;
	private String  cie10;
	private String  baseDiagnostico;
	private String  fuenteInformacion;
	private int  seccion;
	
	private long numResultados = -1;

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

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getProvincia() {
		return provincia;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEnfermedadRaraCLM() {
		return enfermedadRaraCLM;
	}

	public void setEnfermedadRaraCLM(String enfermedadRaraCLM) {
		this.enfermedadRaraCLM = enfermedadRaraCLM;
	}

	public String getCie9MC() {
		return cie9MC;
	}

	public void setCie9MC(String cie9mc) {
		cie9MC = cie9mc;
	}

	public String getCie10() {
		return cie10;
	}

	public void setCie10(String cie10) {
		this.cie10 = cie10;
	}

	public String getBaseDiagnostico() {
		return baseDiagnostico;
	}

	public void setBaseDiagnostico(String baseDiagnostica) {
		this.baseDiagnostico = baseDiagnostica;
	}

	public String getFuenteInformacion() {
		return fuenteInformacion;
	}

	public void setFuenteInformacion(String fuenteInformacion) {
		this.fuenteInformacion = fuenteInformacion;
	}

	public String getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(String anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	public int getSeccion() {
		return seccion;
	}

	public void setSeccion(int seccion) {
		this.seccion = seccion;
	}

	public long getNumResultados() {
		return numResultados;
	}

	public void setNumResultados(long numResultados) {
		this.numResultados = numResultados;
	}
	
}
