package es.jclm.cs.rarasclm.entities;
// Generated 15-jun-2016 6:48:58 by Hibernate Tools 4.3.1.Final

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
 * EnfermedadRaraHasEnfermedadRaraCie10 generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_has_enfermedad_rara_cie10", catalog = "rarasclm")
public class EnfermedadRaraHasEnfermedadRaraCie10 implements java.io.Serializable {

	private EnfermedadRaraHasEnfermedadRaraCie10Id id;
	private EnfermedadRara enfermedadRara;
	private EnfermedadRaraCie10 enfermedadRaraCie10;
	private Integer numPrioridad;
	private String notas;

	public EnfermedadRaraHasEnfermedadRaraCie10() {
	}

	public EnfermedadRaraHasEnfermedadRaraCie10(EnfermedadRaraHasEnfermedadRaraCie10Id id,
			EnfermedadRara enfermedadRara, EnfermedadRaraCie10 enfermedadRaraCie10) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraCie10 = enfermedadRaraCie10;
	}

	public EnfermedadRaraHasEnfermedadRaraCie10(EnfermedadRaraHasEnfermedadRaraCie10Id id,
			EnfermedadRara enfermedadRara, EnfermedadRaraCie10 enfermedadRaraCie10, Integer numPrioridad,
			String notas) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraCie10 = enfermedadRaraCie10;
		this.numPrioridad = numPrioridad;
		this.notas = notas;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "enfermedadRaraId", column = @Column(name = "enfermedad_rara_id", nullable = false, length = 10)),
			@AttributeOverride(name = "cie10Id", column = @Column(name = "cie10_id", nullable = false, length = 5)) })
	public EnfermedadRaraHasEnfermedadRaraCie10Id getId() {
		return this.id;
	}

	public void setId(EnfermedadRaraHasEnfermedadRaraCie10Id id) {
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
	@JoinColumn(name = "cie10_id", nullable = false, insertable = false, updatable = false)
	public EnfermedadRaraCie10 getEnfermedadRaraCie10() {
		return this.enfermedadRaraCie10;
	}

	public void setEnfermedadRaraCie10(EnfermedadRaraCie10 enfermedadRaraCie10) {
		this.enfermedadRaraCie10 = enfermedadRaraCie10;
	}

	@Column(name = "num_prioridad")
	public Integer getNumPrioridad() {
		return this.numPrioridad;
	}

	public void setNumPrioridad(Integer numPrioridad) {
		this.numPrioridad = numPrioridad;
	}

	@Column(name = "notas", length = 2048)
	public String getNotas() {
		return this.notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

}
