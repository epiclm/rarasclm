package es.jclm.cs.rarasclm.entities;
// Generated 18-jun-2016 4:04:46 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PacienteHistoriaId generated by hbm2java
 */
@Embeddable
public class PacienteHistoriaId implements java.io.Serializable {

	private int idPaciente;
	private int idVersion;

	public PacienteHistoriaId() {
	}

	public PacienteHistoriaId(int idPaciente, int idVersion) {
		this.idPaciente = idPaciente;
		this.idVersion = idVersion;
	}

	@Column(name = "id_paciente", nullable = false)
	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Column(name = "id_version", nullable = false)
	public int getIdVersion() {
		return this.idVersion;
	}

	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PacienteHistoriaId))
			return false;
		PacienteHistoriaId castOther = (PacienteHistoriaId) other;

		return (this.getIdPaciente() == castOther.getIdPaciente()) && (this.getIdVersion() == castOther.getIdVersion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPaciente();
		result = 37 * result + this.getIdVersion();
		return result;
	}

}
