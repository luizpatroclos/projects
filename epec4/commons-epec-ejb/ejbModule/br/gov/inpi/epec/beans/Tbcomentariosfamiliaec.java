/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCOMENTARIOSFAMILIAEC")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Tbcomentariosfamiliaec.findAll", query = "SELECT t FROM Tbcomentariosfamiliaec t"),
		@NamedQuery(name = "Tbcomentariosfamiliaec.countAll", query = "SELECT COUNT(t) FROM Tbcomentariosfamiliaec t"),
		@NamedQuery(name = "Tbcomentariosfamiliaec.countAllByIdPatenteEc", query = "SELECT COUNT(t) FROM Tbcomentariosfamiliaec t WHERE t.idUsuario.idEntidadeEc.idEntidadeEc = :idEntidadeEc"),
		@NamedQuery(name = "Tbcomentariosfamiliaec.findByIdComentariosfamiliaEc", query = "SELECT t FROM Tbcomentariosfamiliaec t WHERE t.idComentariosfamiliaEc.idFamiliaEc = :idComentariosfamiliaEc order BY t.dtRegistro DESC"),
		@NamedQuery(name = "Tbcomentariosfamiliaec.findByDtRegistro", query = "SELECT t FROM Tbcomentariosfamiliaec t WHERE t.dtRegistro = :dtRegistro") })
public class Tbcomentariosfamiliaec implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COMENTARIOSFAMILIA_EC", nullable = false)
	private Long idComentariosfamiliaEc;

	@Basic(optional = false)
	@NotNull
	@Column(name = "DT_REGISTRO", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistro;

	@Basic(optional = false)
	@NotNull
	@Lob
	@Size(min = 1, max = 65535)
	@Column(name = "TX_COMENTARIO", nullable = false, length = 65535)
	private String txComentario;

	@JoinColumn(name = "ID_FAMILIA_EC", referencedColumnName = "ID_FAMILIA_EC", nullable = false)
	@ManyToOne(optional = false)
	private Tbfamiliaec idFamiliaEc;

	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
	@ManyToOne(optional = false)
	private Tbcadusuario idUsuario;

	public Tbcomentariosfamiliaec() {
	}

	public Tbcomentariosfamiliaec(Long idComentariosfamiliaEc) {
		this.idComentariosfamiliaEc = idComentariosfamiliaEc;
	}

	public Tbcomentariosfamiliaec(Long idComentariosfamiliaEc, Date dtRegistro, String txComentario) {
		this.idComentariosfamiliaEc = idComentariosfamiliaEc;
		this.dtRegistro = dtRegistro;
		this.txComentario = txComentario;
	}

	public Long getIdComentariosfamiliaEc() {
		return idComentariosfamiliaEc;
	}

	public void setIdComentariosfamiliaEc(Long idComentariosfamiliaEc) {
		this.idComentariosfamiliaEc = idComentariosfamiliaEc;
	}

	public Date getDtRegistro() {
		return dtRegistro;
	}

	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
	}

	public String getTxComentario() {
		return txComentario;
	}

	public void setTxComentario(String txComentario) {
		this.txComentario = txComentario;
	}

	public Tbfamiliaec getIdFamiliaEc() {
		return idFamiliaEc;
	}

	public void setIdFamiliaEc(Tbfamiliaec idFamiliaEc) {
		this.idFamiliaEc = idFamiliaEc;
	}

	public Tbcadusuario getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Tbcadusuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idComentariosfamiliaEc != null ? idComentariosfamiliaEc.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Tbcomentariosfamiliaec)) {
			return false;
		}
		Tbcomentariosfamiliaec other = (Tbcomentariosfamiliaec) object;
		if ((this.idComentariosfamiliaEc == null && other.idComentariosfamiliaEc != null) || (this.idComentariosfamiliaEc != null && !this.idComentariosfamiliaEc.equals(other.idComentariosfamiliaEc))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.gov.inpi.epec.beans.Tbcomentariosfamiliaec[ idComentariosfamiliaEc=" + idComentariosfamiliaEc + " ]";
	}

}
