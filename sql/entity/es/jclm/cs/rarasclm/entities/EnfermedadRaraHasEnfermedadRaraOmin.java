package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EnfermedadRaraHasEnfermedadRaraOmin generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_has_enfermedad_rara_omin", catalog = "RARASCLM")
public class EnfermedadRaraHasEnfermedadRaraOmin implements java.io.Serializable {

	private EnfermedadRaraHasEnfermedadRaraOminId id;
	private EnfermedadRara enfermedadRara;
	private EnfermedadRaraOmin enfermedadRaraOmin;
	private Integer numPrioridad;

	public EnfermedadRaraHasEnfermedadRaraOmin() {
	}

	public EnfermedadRaraHasEnfermedadRaraOmin(EnfermedadRaraHasEnfermedadRaraOminId id, EnfermedadRara enfermedadRara,
			EnfermedadRaraOmin enfermedadRaraOmin) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraOmin = enfermedadRaraOmin;
	}

	public EnfermedadRaraHasEnfermedadRaraOmin(EnfermedadRaraHasEnfermedadRaraOminId id, EnfermedadRara enfermedadRara,
			EnfermedadRaraOmin enfermedadRaraOmin, Integer numPrioridad) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraOmin = enfermedadRaraOmin;
		this.numPrioridad = numPrioridad;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "enfermedadRaraId", column = @Column(name = "enfermedad_rara_id", nullable = false, length = 10)),
			@AttributeOverride(name = "ominId", column = @Column(name = "omin_id", nullable = false, length = 10)) })
	public EnfermedadRaraHasEnfermedadRaraOminId getId() {
		return this.id;
	}

	public void setId(EnfermedadRaraHasEnfermedadRaraOminId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "enfermedad_rara_id", nullable = false, insertable = false, updatable = false)
	public EnfermedadRara getEnfermedadRara() {
		return this.enfermedadRara;
	}

	public void setEnfermedadRara(EnfermedadRara enfermedadRara) {
		this.enfermedadRara = enfermedadRara;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "omin_id", nullable = false, insertable = false, updatable = false)
	public EnfermedadRaraOmin getEnfermedadRaraOmin() {
		return this.enfermedadRaraOmin;
	}

	public void setEnfermedadRaraOmin(EnfermedadRaraOmin enfermedadRaraOmin) {
		this.enfermedadRaraOmin = enfermedadRaraOmin;
	}

	@Column(name = "num_prioridad")
	public Integer getNumPrioridad() {
		return this.numPrioridad;
	}

	public void setNumPrioridad(Integer numPrioridad) {
		this.numPrioridad = numPrioridad;
	}

}
