package es.jclm.cs.rarasclm.entities;
// Generated 13-ago-2016 9:31:52 by Hibernate Tools 4.3.4.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CasoRevisionUsuario generated by hbm2java
 */
@Entity
@Table(name = "caso_revision_usuario")
public class CasoRevisionUsuario implements java.io.Serializable {

	private CasoRevisionUsuarioId id;
	private Caso caso;
	private UserRarasClm userRarasClm;
	private Boolean revisado;
	private Date fechaRevision;
	private Date fechaCreacion;

	public CasoRevisionUsuario() {
	}

	public CasoRevisionUsuario(CasoRevisionUsuarioId id, Caso caso, UserRarasClm userRarasClm) {
		this.id = id;
		this.caso = caso;
		this.userRarasClm = userRarasClm;
	}

	public CasoRevisionUsuario(CasoRevisionUsuarioId id, Caso caso, UserRarasClm userRarasClm, Boolean revisado,
			Date fechaRevision, Date fechaCreacion) {
		this.id = id;
		this.caso = caso;
		this.userRarasClm = userRarasClm;
		this.revisado = revisado;
		this.fechaRevision = fechaRevision;
		this.fechaCreacion = fechaCreacion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "usuario", column = @Column(name = "usuario", nullable = false, length = 45)),
			@AttributeOverride(name = "caso", column = @Column(name = "caso", nullable = false, length = 10)),
			@AttributeOverride(name = "numRev", column = @Column(name = "num_rev", nullable = false)) })
	public CasoRevisionUsuarioId getId() {
		return this.id;
	}

	public void setId(CasoRevisionUsuarioId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caso", nullable = false, insertable = false, updatable = false)
	public Caso getCaso() {
		return this.caso;
	}

	public void setCaso(Caso caso) {
		this.caso = caso;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario", nullable = false, insertable = false, updatable = false)
	public UserRarasClm getUserRarasClm() {
		return this.userRarasClm;
	}

	public void setUserRarasClm(UserRarasClm userRarasClm) {
		this.userRarasClm = userRarasClm;
	}

	@Column(name = "revisado")
	public Boolean getRevisado() {
		return this.revisado;
	}

	public void setRevisado(Boolean revisado) {
		this.revisado = revisado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_revision", length = 19)
	public Date getFechaRevision() {
		return this.fechaRevision;
	}

	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", length = 19)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
