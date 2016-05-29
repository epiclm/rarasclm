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
 * EnfermedadRaraHasEnfermedadRaraCie9mc generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_has_enfermedad_rara_cie9mc", catalog = "RARASCLM")
public class EnfermedadRaraHasEnfermedadRaraCie9mc implements java.io.Serializable {

	private EnfermedadRaraHasEnfermedadRaraCie9mcId id;
	private EnfermedadRara enfermedadRara;
	private EnfermedadRaraCie9mc enfermedadRaraCie9mc;
	private Integer numPrioridad;
	private String notas;

	public EnfermedadRaraHasEnfermedadRaraCie9mc() {
	}

	public EnfermedadRaraHasEnfermedadRaraCie9mc(EnfermedadRaraHasEnfermedadRaraCie9mcId id,
			EnfermedadRara enfermedadRara, EnfermedadRaraCie9mc enfermedadRaraCie9mc) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraCie9mc = enfermedadRaraCie9mc;
	}

	public EnfermedadRaraHasEnfermedadRaraCie9mc(EnfermedadRaraHasEnfermedadRaraCie9mcId id,
			EnfermedadRara enfermedadRara, EnfermedadRaraCie9mc enfermedadRaraCie9mc, Integer numPrioridad,
			String notas) {
		this.id = id;
		this.enfermedadRara = enfermedadRara;
		this.enfermedadRaraCie9mc = enfermedadRaraCie9mc;
		this.numPrioridad = numPrioridad;
		this.notas = notas;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "enfermedadRaraId", column = @Column(name = "enfermedad_rara_id", nullable = false, length = 10)),
			@AttributeOverride(name = "cie9Id", column = @Column(name = "cie9_id", nullable = false, length = 6)) })
	public EnfermedadRaraHasEnfermedadRaraCie9mcId getId() {
		return this.id;
	}

	public void setId(EnfermedadRaraHasEnfermedadRaraCie9mcId id) {
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
	@JoinColumn(name = "cie9_id", nullable = false, insertable = false, updatable = false)
	public EnfermedadRaraCie9mc getEnfermedadRaraCie9mc() {
		return this.enfermedadRaraCie9mc;
	}

	public void setEnfermedadRaraCie9mc(EnfermedadRaraCie9mc enfermedadRaraCie9mc) {
		this.enfermedadRaraCie9mc = enfermedadRaraCie9mc;
	}

	@Column(name = "num_prioridad")
	public Integer getNumPrioridad() {
		return this.numPrioridad;
	}

	public void setNumPrioridad(Integer numPrioridad) {
		this.numPrioridad = numPrioridad;
	}

	@Column(name = "notas")
	public String getNotas() {
		return this.notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

}
