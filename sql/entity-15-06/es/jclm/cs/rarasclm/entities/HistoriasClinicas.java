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
 * HistoriasClinicas generated by hbm2java
 */
@Entity
@Table(name = "historias_clinicas", catalog = "rarasclm")
public class HistoriasClinicas implements java.io.Serializable {

	private HistoriasClinicasId id;
	private Hospital hospital;
	private Paciente paciente;
	private String idUnicoHc;

	public HistoriasClinicas() {
	}

	public HistoriasClinicas(HistoriasClinicasId id, Hospital hospital, Paciente paciente) {
		this.id = id;
		this.hospital = hospital;
		this.paciente = paciente;
	}

	public HistoriasClinicas(HistoriasClinicasId id, Hospital hospital, Paciente paciente, String idUnicoHc) {
		this.id = id;
		this.hospital = hospital;
		this.paciente = paciente;
		this.idUnicoHc = idUnicoHc;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "paciente", column = @Column(name = "paciente", nullable = false)),
			@AttributeOverride(name = "hospital", column = @Column(name = "hospital", nullable = false, length = 6)),
			@AttributeOverride(name = "nhc", column = @Column(name = "nhc", nullable = false, length = 12)) })
	public HistoriasClinicasId getId() {
		return this.id;
	}

	public void setId(HistoriasClinicasId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hospital", nullable = false, insertable = false, updatable = false)
	public Hospital getHospital() {
		return this.hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente", nullable = false, insertable = false, updatable = false)
	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Column(name = "id_unico_hc", length = 18)
	public String getIdUnicoHc() {
		return this.idUnicoHc;
	}

	public void setIdUnicoHc(String idUnicoHc) {
		this.idUnicoHc = idUnicoHc;
	}

}
