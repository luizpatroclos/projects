/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_resumo_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnResumoPedido.findAll", query = "SELECT p FROM PtnResumoPedido p"),
    @NamedQuery(name = "PtnResumoPedido.findByCodPedido", query = "SELECT p FROM PtnResumoPedido p WHERE p.codPedido = :codPedido"),
    @NamedQuery(name = "PtnResumoPedido.findByNumPedido", query = "SELECT p FROM PtnResumoPedido p WHERE p.numPedido = :numPedido"),
    @NamedQuery(name = "PtnResumoPedido.findByLastUpdate", query = "SELECT p FROM PtnResumoPedido p WHERE p.lastUpdate = :lastUpdate")})
public class PtnResumoPedido implements Serializable {
   
	private static final long serialVersionUID = 1L;
   
	@Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pedido")
    private Long codPedido;
    
    @Lob
    @Size(max = 56)
    @Column(name = "resumo")
    private String resumo;
    
    @Size(max = 9)
    @Column(name = "num_pedido")
    private String numPedido;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    
    @Lob
    @Size(max = 56)
    @Column(name = "tx_relator_descrit")
    private String txRelatorDescrit;

    public PtnResumoPedido() {
    }

    public PtnResumoPedido(Long codPedido) {
        this.codPedido = codPedido;
    }

    public PtnResumoPedido(Long codPedido, Date lastUpdate) {
        this.codPedido = codPedido;
        this.lastUpdate = lastUpdate;
    }

   

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getTxRelatorDescrit() {
        return txRelatorDescrit;
    }

    public void setTxRelatorDescrit(String txRelatorDescrit) {
        this.txRelatorDescrit = txRelatorDescrit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPedido != null ? codPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnResumoPedido)) {
            return false;
        }
        PtnResumoPedido other = (PtnResumoPedido) object;
        if ((this.codPedido == null && other.codPedido != null) || (this.codPedido != null && !this.codPedido.equals(other.codPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnResumoPedido[ codPedido=" + codPedido + " ]";
    }

	public Long getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Long codPedido) {
		this.codPedido = codPedido;
	}
    
}
