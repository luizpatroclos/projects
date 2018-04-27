/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tgouvea
 */
@Entity
@Table(name = "ptn_procurador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnProcurador.findAll", query = "SELECT p FROM PtnProcurador p"),
    @NamedQuery(name = "PtnProcurador.findByCodPedido", query = "SELECT p FROM PtnProcurador p WHERE p.ptnProcuradorPK.codPedido = :codPedido"),
    @NamedQuery(name = "PtnProcurador.findByCodProcurador", query = "SELECT p FROM PtnProcurador p WHERE p.ptnProcuradorPK.codProcurador = :codProcurador"),
    @NamedQuery(name = "PtnProcurador.findByDtInicio", query = "SELECT p FROM PtnProcurador p WHERE p.ptnProcuradorPK.dtInicio = :dtInicio"),
    @NamedQuery(name = "PtnProcurador.findByDtTermino", query = "SELECT p FROM PtnProcurador p WHERE p.dtTermino = :dtTermino")})
public class PtnProcurador implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PtnProcuradorPK ptnProcuradorPK;
    @Column(name = "dt_termino")
    @Temporal(TemporalType.DATE)
    private Date dtTermino;

    public PtnProcurador() {
    }

    public PtnProcurador(PtnProcuradorPK ptnProcuradorPK) {
        this.ptnProcuradorPK = ptnProcuradorPK;
    }

    public PtnProcurador(int codPedido, int codProcurador, Date dtInicio) {
        this.ptnProcuradorPK = new PtnProcuradorPK(codPedido, codProcurador, dtInicio);
    }

    public PtnProcuradorPK getPtnProcuradorPK() {
        return ptnProcuradorPK;
    }

    public void setPtnProcuradorPK(PtnProcuradorPK ptnProcuradorPK) {
        this.ptnProcuradorPK = ptnProcuradorPK;
    }

    public Date getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(Date dtTermino) {
        this.dtTermino = dtTermino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ptnProcuradorPK != null ? ptnProcuradorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtnProcurador)) {
            return false;
        }
        PtnProcurador other = (PtnProcurador) object;
        if ((this.ptnProcuradorPK == null && other.ptnProcuradorPK != null) || (this.ptnProcuradorPK != null && !this.ptnProcuradorPK.equals(other.ptnProcuradorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnProcurador[ ptnProcuradorPK=" + ptnProcuradorPK + " ]";
    }
    
}
