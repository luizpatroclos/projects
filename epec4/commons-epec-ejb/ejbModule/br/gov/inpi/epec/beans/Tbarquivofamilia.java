/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBARQUIVOFAMILIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbarquivofamilia.findAll", query = "SELECT t FROM Tbarquivofamilia t"),
    @NamedQuery(name = "Tbarquivofamilia.findByIdArquivofamilia", query = "SELECT t FROM Tbarquivofamilia t WHERE t.idArquivofamilia = :idArquivofamilia"),
    @NamedQuery(name = "Tbarquivofamilia.findByDDatahora", query = "SELECT t FROM Tbarquivofamilia t WHERE t.dDatahora = :dDatahora")})
public class Tbarquivofamilia implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ARQUIVOFAMILIA", nullable = false)
    private Long idArquivofamilia;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "TX_NOMEARQUIVO", nullable = false, length = 65535)
    private String txNomearquivo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "D_DATAHORA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dDatahora;
    
    @Lob
    @Size(max = 2147483647)
    @Column(name = "TX_COMENTARIO", length = 2147483647)
    private String txComentario;
    
    @JoinColumn(name = "ID_FAMILIA_EC", referencedColumnName = "ID_FAMILIA_EC", nullable = false)
    @ManyToOne(optional = false)
    private Tbfamiliaec idFamiliaEc;
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO", nullable = false)
    @ManyToOne(optional = false)
    private Tbcadusuario idUsuario;
    
    @JoinColumn(name = "ID_CADTIPOARQUIVO", referencedColumnName = "ID_CADTIPOARQUIVO", nullable = false)
    @ManyToOne(optional = false)
    private Tbcadtipoarquivo idCadtipoarquivo;

    public Tbarquivofamilia() {
    }

    public Tbarquivofamilia(Long idArquivofamilia) {
        this.idArquivofamilia = idArquivofamilia;
    }

    public Tbarquivofamilia(Long idArquivofamilia, String txNomearquivo, Date dDatahora) {
        this.idArquivofamilia = idArquivofamilia;
        this.txNomearquivo = txNomearquivo;
        this.dDatahora = dDatahora;
    }

    public Long getIdArquivofamilia() {
        return idArquivofamilia;
    }

    public void setIdArquivofamilia(Long idArquivofamilia) {
        this.idArquivofamilia = idArquivofamilia;
    }

    public String getTxNomearquivo() {
        return txNomearquivo;
    }

    public void setTxNomearquivo(String txNomearquivo) {
        this.txNomearquivo = txNomearquivo;
    }

    public Date getDDatahora() {
        return dDatahora;
    }

    public void setDDatahora(Date dDatahora) {
        this.dDatahora = dDatahora;
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

    public Tbcadtipoarquivo getIdCadtipoarquivo() {
        return idCadtipoarquivo;
    }

    public void setIdCadtipoarquivo(Tbcadtipoarquivo idCadtipoarquivo) {
        this.idCadtipoarquivo = idCadtipoarquivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArquivofamilia != null ? idArquivofamilia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbarquivofamilia)) {
            return false;
        }
        Tbarquivofamilia other = (Tbarquivofamilia) object;
        if ((this.idArquivofamilia == null && other.idArquivofamilia != null) || (this.idArquivofamilia != null && !this.idArquivofamilia.equals(other.idArquivofamilia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbarquivofamilia[ idArquivofamilia=" + idArquivofamilia + " ]";
    }
    
}
