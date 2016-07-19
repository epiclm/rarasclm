package es.jclm.cs.rarasclm.entities;
// Generated 05-jun-2016 17:26:24 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "rarasclm")
public class Users implements java.io.Serializable {

	private String username;
	private String password;
	private byte enabled;
	private String nombre;
	private String apellido01;
	private String apellido02;
	private Integer seccion;
	private Date ultimoAcceso;
	private Integer numIntentos;
	private String puesto;
	private Set<Roles> roleses = new HashSet<Roles>(0);

	public Users() {
	}

	public Users(String username, String password, byte enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public Users(String username, String password, byte enabled, String nombre, String apellido01, String apellido02,
			Integer seccion, Date ultimoAcceso, Integer numIntentos, String puesto, Set<Roles> roleses) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.nombre = nombre;
		this.apellido01 = apellido01;
		this.apellido02 = apellido02;
		this.seccion = seccion;
		this.ultimoAcceso = ultimoAcceso;
		this.numIntentos = numIntentos;
		this.puesto = puesto;
		this.roleses = roleses;
	}

	@Id

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	@Column(name = "nombre", length = 55)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "apellido_01", length = 55)
	public String getApellido01() {
		return this.apellido01;
	}

	public void setApellido01(String apellido01) {
		this.apellido01 = apellido01;
	}

	@Column(name = "apellido_02", length = 55)
	public String getApellido02() {
		return this.apellido02;
	}

	public void setApellido02(String apellido02) {
		this.apellido02 = apellido02;
	}

	@Column(name = "seccion")
	public Integer getSeccion() {
		return this.seccion;
	}

	public void setSeccion(Integer seccion) {
		this.seccion = seccion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ultimo_acceso", length = 19)
	public Date getUltimoAcceso() {
		return this.ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	@Column(name = "num_intentos")
	public Integer getNumIntentos() {
		return this.numIntentos;
	}

	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}

	@Column(name = "puesto", length = 100)
	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", catalog = "rarasclm", joinColumns = {
			@JoinColumn(name = "username", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_rol", nullable = false, updatable = false) })
	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}
