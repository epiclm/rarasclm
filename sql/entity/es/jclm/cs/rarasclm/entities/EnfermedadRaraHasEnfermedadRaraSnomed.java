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
 * EnfermedadRaraHasEnfermedadRaraSnomed generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_has_enfermedad_rara_snomed", catalog = "RARASCLM")
public class EnfermedadRaraHasEnfermedadRaraSnomed implements java.io.Serializable {

	private EnfermedadRaraHasEnfermedadRaraSnomedId id;
	private EnfermedadRara enfermedadRara;
	private EnfermedadRaraSnomed enfermedadRaraSnomed;
	private Integer numPrioridad;

	public EnfermedadRaraHasEnfermedadRaraSnomed() {
	}

	public EnfermedadRaraHasEnfermedadRaraSnomed(EnfermedadRaraHasEnfermedadRaraSnomedId id,
			EnfermedadRara enfermedadRara, EnfermedadRaraSnomed enfermedadRaraSnomed) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraSnomed = enfermedadRaraSnomed;
	}

	public EnfermedadRaraHasEnfermedadRaraSnomed(EnfermedadRaraHasEnfermedadRaraSnomedId id,
			EnfermedadRara enfermedadRara, EnfermedadRaraSnomed enfermedadRaraSnomed, Integer numPrioridad) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraSnomed = enfermedadRaraSnomed;
		this.numPrioridad = numPrioridad;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "enfermedadRaraId", column = @Column(name = "enfermedad_rara_id", nullable = false, length = 10)),
			@AttributeOverride(name = "snomedId", column = @Column(name = "snomed_id", nullable = false)) })
	public EnfermedadRaraHasEnfermedadRaraSnomedId getId() {
		return this.id;
	}

	public void setId(EnfermedadRaraHasEnfermedadRaraSnomedId id) {
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
	@JoinColumn(name = "snomed_id", nullable = false, insertable = false, updatable = false)
	public EnfermedadRaraSnomed getEnfermedadRaraSnomed() {
		return this.enfermedadRaraSnomed;
	}

	public void setEnfermedadRaraSnomed(EnfermedadRaraSnomed enfermedadRaraSnomed) {
		this.enfermedadRaraSnomed = enfermedadRaraSnomed;
	}

	@Column(name = "num_prioridad")
	public Integer getNumPrioridad() {
		return this.numPrioridad;
	}

	public void setNumPrioridad(Integer numPrioridad) {
		this.numPrioridad = numPrioridad;
	}

}
