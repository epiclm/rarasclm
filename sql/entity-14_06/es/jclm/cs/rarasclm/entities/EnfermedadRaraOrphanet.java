package es.jclm.cs.rarasclm.entities;
// Generated 14-jun-2016 13:22:23 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EnfermedadRaraOrphanet generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_orphanet", catalog = "rarasclm")
public class EnfermedadRaraOrphanet implements java.io.Serializable {

	private String orphanetId;
	private String literal;
	private String url;
	private Set<EnfermedadRaraHasEnfermedadRaraOrphanet> enfermedadRaraHasEnfermedadRaraOrphanets = new HashSet<EnfermedadRaraHasEnfermedadRaraOrphanet>(
			0);

	public EnfermedadRaraOrphanet() {
	}

	public EnfermedadRaraOrphanet(String orphanetId) {
		this.orphanetId = orphanetId;
	}

	public EnfermedadRaraOrphanet(String orphanetId, String literal, String url,
			Set<EnfermedadRaraHasEnfermedadRaraOrphanet> enfermedadRaraHasEnfermedadRaraOrphanets) {
		this.orphanetId = orphanetId;
		this.literal = literal;
		this.url = url;
		this.enfermedadRaraHasEnfermedadRaraOrphanets = enfermedadRaraHasEnfermedadRaraOrphanets;
	}

	@Id

	@Column(name = "orphanet_id", unique = true, nullable = false, length = 12)
	public String getOrphanetId() {
		return this.orphanetId;
	}

	public void setOrphanetId(String orphanetId) {
		this.orphanetId = orphanetId;
	}

	@Column(name = "literal")
	public String getLiteral() {
		return this.literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enfermedadRaraOrphanet")
	public Set<EnfermedadRaraHasEnfermedadRaraOrphanet> getEnfermedadRaraHasEnfermedadRaraOrphanets() {
		return this.enfermedadRaraHasEnfermedadRaraOrphanets;
	}

	public void setEnfermedadRaraHasEnfermedadRaraOrphanets(
			Set<EnfermedadRaraHasEnfermedadRaraOrphanet> enfermedadRaraHasEnfermedadRaraOrphanets) {
		this.enfermedadRaraHasEnfermedadRaraOrphanets = enfermedadRaraHasEnfermedadRaraOrphanets;
	}

}
