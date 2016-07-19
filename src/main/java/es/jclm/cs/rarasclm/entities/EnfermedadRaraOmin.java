package es.jclm.cs.rarasclm.entities;// default package
// Generated 26-nov-2015 14:41:38 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * EnfermedadRaraOmin generated by hbm2java
 */
@Entity
@Table(name = "enfermedad_rara_omin", catalog = "rarasclm")
public class EnfermedadRaraOmin implements java.io.Serializable {

    private String ominId;
    private String literal;
    private String url;
    private Set<EnfermedadRaraHasEnfermedadRaraOmin> enfermedadRaraHasEnfermedadRaraOmins = new HashSet<EnfermedadRaraHasEnfermedadRaraOmin>(
	    0);

    public EnfermedadRaraOmin() {
    }

    public EnfermedadRaraOmin(String ominId) {
	this.ominId = ominId;
    }

    public EnfermedadRaraOmin(String ominId, String literal, String url,
	    Set<EnfermedadRaraHasEnfermedadRaraOmin> enfermedadRaraHasEnfermedadRaraOmins) {
	this.ominId = ominId;
	this.literal = literal;
	this.url = url;
	this.enfermedadRaraHasEnfermedadRaraOmins = enfermedadRaraHasEnfermedadRaraOmins;
    }

    @Id

    @Column(name = "omin_id", unique = true, nullable = false, length = 10)
    public String getOminId() {
	return this.ominId;
    }

    public void setOminId(String ominId) {
	this.ominId = ominId;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "enfermedadRaraOmin")
    public Set<EnfermedadRaraHasEnfermedadRaraOmin> getEnfermedadRaraHasEnfermedadRaraOmins() {
	return this.enfermedadRaraHasEnfermedadRaraOmins;
    }

    public void setEnfermedadRaraHasEnfermedadRaraOmins(
	    Set<EnfermedadRaraHasEnfermedadRaraOmin> enfermedadRaraHasEnfermedadRaraOmins) {
	this.enfermedadRaraHasEnfermedadRaraOmins = enfermedadRaraHasEnfermedadRaraOmins;
    }

}
