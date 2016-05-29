package es.jclm.cs.rarasclm.entities;
// Generated 28-may-2016 19:43:36 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EnfermedadRaraHasEnfermedadRaraOrphanetId generated by hbm2java
 */
@Embeddable
public class EnfermedadRaraHasEnfermedadRaraOrphanetId implements java.io.Serializable {

	private String enfermedadRaraId;
	private String orphanetId;

	public EnfermedadRaraHasEnfermedadRaraOrphanetId() {
	}

	public EnfermedadRaraHasEnfermedadRaraOrphanetId(String enfermedadRaraId, String orphanetId) {
		this.enfermedadRaraId = enfermedadRaraId;
		this.orphanetId = orphanetId;
	}

	@Column(name = "enfermedad_rara_id", nullable = false, length = 10)
	public String getEnfermedadRaraId() {
		return this.enfermedadRaraId;
	}

	public void setEnfermedadRaraId(String enfermedadRaraId) {
		this.enfermedadRaraId = enfermedadRaraId;
	}

	@Column(name = "orphanet_id", nullable = false, length = 12)
	public String getOrphanetId() {
		return this.orphanetId;
	}

	public void setOrphanetId(String orphanetId) {
		this.orphanetId = orphanetId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EnfermedadRaraHasEnfermedadRaraOrphanetId))
			return false;
		EnfermedadRaraHasEnfermedadRaraOrphanetId castOther = (EnfermedadRaraHasEnfermedadRaraOrphanetId) other;

		return ((this.getEnfermedadRaraId() == castOther.getEnfermedadRaraId())
				|| (this.getEnfermedadRaraId() != null && castOther.getEnfermedadRaraId() != null
						&& this.getEnfermedadRaraId().equals(castOther.getEnfermedadRaraId())))
				&& ((this.getOrphanetId() == castOther.getOrphanetId())
						|| (this.getOrphanetId() != null && castOther.getOrphanetId() != null
								&& this.getOrphanetId().equals(castOther.getOrphanetId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getEnfermedadRaraId() == null ? 0 : this.getEnfermedadRaraId().hashCode());
		result = 37 * result + (getOrphanetId() == null ? 0 : this.getOrphanetId().hashCode());
		return result;
	}

}
