package es.jclm.cs.rarasclm.entities;
// Generated 18-jun-2016 4:04:46 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Paciente generated by hbm2java
 */
@Entity
@Table(name = "paciente", catalog = "RARASCLM")
public class Paciente implements java.io.Serializable {

	private int idPaciente;
	private String idpacnac;
	private String cip;
	private String dni;
	private String numeroSegSocial;
	private String nombre;
	private String apellido01;
	private String apellido02;
	private Date fechaNacimiento;
	private Character sexo;
	private String provinciaNacimiento;
	private String municipioNacimiento;
	private String paisNacimiento;
	private String domTipoVia;
	private String domNombreVia;
	private String domNumero;
	private String domPisopuerta;
	private String domOtros;
	private String domCp;
	private String provinciaResidencia;
	private String municipioResidencia;
	private Byte fallecido;
	private Date fallecidoFechaComprobacion;
	private String fallecidoEtiquetaComprobacion;
	private String fallecidoNumbol;
	private String email;
	private String telefono;
	private Set<HistoriasClinicas> historiasClinicases = new HashSet<HistoriasClinicas>(0);
	private Set<Caso> casos = new HashSet<Caso>(0);

	public Paciente() {
	}

	public Paciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Paciente(int idPaciente, String idpacnac, String cip, String dni, String numeroSegSocial, String nombre,
			String apellido01, String apellido02, Date fechaNacimiento, Character sexo, String provinciaNacimiento,
			String municipioNacimiento, String paisNacimiento, String domTipoVia, String domNombreVia, String domNumero,
			String domPisopuerta, String domOtros, String domCp, String provinciaResidencia, String municipioResidencia,
			Byte fallecido, Date fallecidoFechaComprobacion, String fallecidoEtiquetaComprobacion,
			String fallecidoNumbol, String email, String telefono, Set<HistoriasClinicas> historiasClinicases,
			Set<Caso> casos) {
		this.idPaciente = idPaciente;
		this.idpacnac = idpacnac;
		this.cip = cip;
		this.dni = dni;
		this.numeroSegSocial = numeroSegSocial;
		this.nombre = nombre;
		this.apellido01 = apellido01;
		this.apellido02 = apellido02;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.provinciaNacimiento = provinciaNacimiento;
		this.municipioNacimiento = municipioNacimiento;
		this.paisNacimiento = paisNacimiento;
		this.domTipoVia = domTipoVia;
		this.domNombreVia = domNombreVia;
		this.domNumero = domNumero;
		this.domPisopuerta = domPisopuerta;
		this.domOtros = domOtros;
		this.domCp = domCp;
		this.provinciaResidencia = provinciaResidencia;
		this.municipioResidencia = municipioResidencia;
		this.fallecido = fallecido;
		this.fallecidoFechaComprobacion = fallecidoFechaComprobacion;
		this.fallecidoEtiquetaComprobacion = fallecidoEtiquetaComprobacion;
		this.fallecidoNumbol = fallecidoNumbol;
		this.email = email;
		this.telefono = telefono;
		this.historiasClinicases = historiasClinicases;
		this.casos = casos;
	}

	@Id

	@Column(name = "id_paciente", unique = true, nullable = false)
	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Column(name = "idpacnac", length = 10)
	public String getIdpacnac() {
		return this.idpacnac;
	}

	public void setIdpacnac(String idpacnac) {
		this.idpacnac = idpacnac;
	}

	@Column(name = "cip", length = 16)
	public String getCip() {
		return this.cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	@Column(name = "dni", length = 12)
	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "numero_seg_social", length = 25)
	public String getNumeroSegSocial() {
		return this.numeroSegSocial;
	}

	public void setNumeroSegSocial(String numeroSegSocial) {
		this.numeroSegSocial = numeroSegSocial;
	}

	@Column(name = "nombre", length = 60)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido_01", length = 60)
	public String getApellido01() {
		return this.apellido01;
	}

	public void setApellido01(String apellido01) {
		this.apellido01 = apellido01;
	}

	@Column(name = "apellido_02", length = 60)
	public String getApellido02() {
		return this.apellido02;
	}

	public void setApellido02(String apellido02) {
		this.apellido02 = apellido02;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", length = 10)
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Column(name = "sexo", length = 1)
	public Character getSexo() {
		return this.sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	@Column(name = "provincia_nacimiento", length = 2)
	public String getProvinciaNacimiento() {
		return this.provinciaNacimiento;
	}

	public void setProvinciaNacimiento(String provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}

	@Column(name = "municipio_nacimiento", length = 5)
	public String getMunicipioNacimiento() {
		return this.municipioNacimiento;
	}

	public void setMunicipioNacimiento(String municipioNacimiento) {
		this.municipioNacimiento = municipioNacimiento;
	}

	@Column(name = "pais_nacimiento", length = 3)
	public String getPaisNacimiento() {
		return this.paisNacimiento;
	}

	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}

	@Column(name = "dom_tipo_via", length = 6)
	public String getDomTipoVia() {
		return this.domTipoVia;
	}

	public void setDomTipoVia(String domTipoVia) {
		this.domTipoVia = domTipoVia;
	}

	@Column(name = "dom_nombre_via", length = 60)
	public String getDomNombreVia() {
		return this.domNombreVia;
	}

	public void setDomNombreVia(String domNombreVia) {
		this.domNombreVia = domNombreVia;
	}

	@Column(name = "dom_numero", length = 4)
	public String getDomNumero() {
		return this.domNumero;
	}

	public void setDomNumero(String domNumero) {
		this.domNumero = domNumero;
	}

	@Column(name = "dom_pisopuerta", length = 4)
	public String getDomPisopuerta() {
		return this.domPisopuerta;
	}

	public void setDomPisopuerta(String domPisopuerta) {
		this.domPisopuerta = domPisopuerta;
	}

	@Column(name = "dom_otros", length = 60)
	public String getDomOtros() {
		return this.domOtros;
	}

	public void setDomOtros(String domOtros) {
		this.domOtros = domOtros;
	}

	@Column(name = "dom_cp", length = 5)
	public String getDomCp() {
		return this.domCp;
	}

	public void setDomCp(String domCp) {
		this.domCp = domCp;
	}

	@Column(name = "provincia_residencia", length = 2)
	public String getProvinciaResidencia() {
		return this.provinciaResidencia;
	}

	public void setProvinciaResidencia(String provinciaResidencia) {
		this.provinciaResidencia = provinciaResidencia;
	}

	@Column(name = "municipio_residencia", length = 5)
	public String getMunicipioResidencia() {
		return this.municipioResidencia;
	}

	public void setMunicipioResidencia(String municipioResidencia) {
		this.municipioResidencia = municipioResidencia;
	}

	@Column(name = "fallecido")
	public Byte getFallecido() {
		return this.fallecido;
	}

	public void setFallecido(Byte fallecido) {
		this.fallecido = fallecido;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fallecido_fecha_comprobacion", length = 19)
	public Date getFallecidoFechaComprobacion() {
		return this.fallecidoFechaComprobacion;
	}

	public void setFallecidoFechaComprobacion(Date fallecidoFechaComprobacion) {
		this.fallecidoFechaComprobacion = fallecidoFechaComprobacion;
	}

	@Column(name = "fallecido_etiqueta_comprobacion", length = 45)
	public String getFallecidoEtiquetaComprobacion() {
		return this.fallecidoEtiquetaComprobacion;
	}

	public void setFallecidoEtiquetaComprobacion(String fallecidoEtiquetaComprobacion) {
		this.fallecidoEtiquetaComprobacion = fallecidoEtiquetaComprobacion;
	}

	@Column(name = "fallecido_numbol", length = 10)
	public String getFallecidoNumbol() {
		return this.fallecidoNumbol;
	}

	public void setFallecidoNumbol(String fallecidoNumbol) {
		this.fallecidoNumbol = fallecidoNumbol;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "telefono", length = 12)
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente")
	public Set<HistoriasClinicas> getHistoriasClinicases() {
		return this.historiasClinicases;
	}

	public void setHistoriasClinicases(Set<HistoriasClinicas> historiasClinicases) {
		this.historiasClinicases = historiasClinicases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente")
	public Set<Caso> getCasos() {
		return this.casos;
	}

	public void setCasos(Set<Caso> casos) {
		this.casos = casos;
	}

}
