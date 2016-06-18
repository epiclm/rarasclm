package es.jclm.cs.rarasclm.entities;
// Generated 18-jun-2016 4:04:46 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EnfermedadRaraHasEnfermedadRaraCie10Id generated by hbm2java
 */
@Embeddable
public class EnfermedadRaraHasEnfermedadRaraCie10Id implements java.io.Serializable {

	private String enfermedadRaraId;
	private String cie10Id;

	public EnfermedadRaraHasEnfermedadRaraCie10Id() {
	}

	public EnfermedadRaraHasEnfermedadRaraCie10Id(String enfermedadRaraId, String cie10Id) {
		this.enfermedadRaraId = enfermedadRaraId;
		this.cie10Id = cie10Id;
	}

	@Column(name = "enfermedad_rara_id", nullable = false, length = 10)
	public String getEnfermedadRaraId() {
		return this.enfermedadRaraId;
	}

	public void setEnfermedadRaraId(String enfermedadRaraId) {
		this.enfermedadRaraId = enfermedadRaraId;
	}

	@Column(name = "cie10_id", nullable = false, length = 5)
	public String getCie10Id() {
		return this.cie10Id;
	}

	public void setCie10Id(String cie10Id) {
		this.cie10Id = cie10Id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EnfermedadRaraHasEnfermedadRaraCie10Id))
			return false;
		EnfermedadRaraHasEnfermedadRaraCie10Id castOther = (EnfermedadRaraHasEnfermedadRaraCie10Id) other;

		return ((this.getEnfermedadRaraId() == castOther.getEnfermedadRaraId())
				|| (this.getEnfermedadRaraId() != null && castOther.getEnfermedadRaraId() != null
						&& this.getEnfermedadRaraId().equals(castOther.getEnfermedadRaraId())))
				&& ((this.getCie10Id() == castOther.getCie10Id()) || (this.getCie10Id() != null
						&& castOther.getCie10Id() != null && this.getCie10Id().equals(castOther.getCie10Id())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getEnfermedadRaraId() == null ? 0 : this.getEnfermedadRaraId().hashCode());
		result = 37 * result + (getCie10Id() == null ? 0 : this.getCie10Id().hashCode());
		return result;
	}

}
