package es.jclm.cs.rarasclm.entities;
// Generated 14-jun-2016 13:22:23 by Hibernate Tools 4.3.1.Final

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
 * EnfermedadRaraHasEnfermedadRaraOrphanet generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_has_enfermedad_rara_orphanet", catalog = "rarasclm")
public class EnfermedadRaraHasEnfermedadRaraOrphanet implements java.io.Serializable {

	private EnfermedadRaraHasEnfermedadRaraOrphanetId id;
	private EnfermedadRara enfermedadRara;
	private EnfermedadRaraOrphanet enfermedadRaraOrphanet;
	private Integer numPrioridad;

	public EnfermedadRaraHasEnfermedadRaraOrphanet() {
	}

	public EnfermedadRaraHasEnfermedadRaraOrphanet(EnfermedadRaraHasEnfermedadRaraOrphanetId id,
			EnfermedadRara enfermedadRara, EnfermedadRaraOrphanet enfermedadRaraOrphanet) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraOrphanet = enfermedadRaraOrphanet;
	}

	public EnfermedadRaraHasEnfermedadRaraOrphanet(EnfermedadRaraHasEnfermedadRaraOrphanetId id,
			EnfermedadRara enfermedadRara, EnfermedadRaraOrphanet enfermedadRaraOrphanet, Integer numPrioridad) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraOrphanet = enfermedadRaraOrphanet;
		this.numPrioridad = numPrioridad;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "enfermedadRaraId", column = @Column(name = "enfermedad_rara_id", nullable = false, length = 10)),
			@AttributeOverride(name = "orphanetId", column = @Column(name = "orphanet_id", nullable = false, length = 12)) })
	public EnfermedadRaraHasEnfermedadRaraOrphanetId getId() {
		return this.id;
	}

	public void setId(EnfermedadRaraHasEnfermedadRaraOrphanetId id) {
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
	@JoinColumn(name = "orphanet_id", nullable = false, insertable = false, updatable = false)
	public EnfermedadRaraOrphanet getEnfermedadRaraOrphanet() {
		return this.enfermedadRaraOrphanet;
	}

	public void setEnfermedadRaraOrphanet(EnfermedadRaraOrphanet enfermedadRaraOrphanet) {
		this.enfermedadRaraOrphanet = enfermedadRaraOrphanet;
	}

	@Column(name = "num_prioridad")
	public Integer getNumPrioridad() {
		return this.numPrioridad;
	}

	public void setNumPrioridad(Integer numPrioridad) {
		this.numPrioridad = numPrioridad;
	}

}
