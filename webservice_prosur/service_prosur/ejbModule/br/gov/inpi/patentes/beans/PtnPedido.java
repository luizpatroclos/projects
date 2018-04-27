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
@Table(name = "ptn_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnPedido.findAll", query = "SELECT p FROM PtnPedido p"),
    @NamedQuery(name = "PtnPedido.findByCodPedido", query = "SELECT p FROM PtnPedido p WHERE p.codPedido = :codPedido"),
    @NamedQuery(name = "PtnPedido.findByCodSituacao", query = "SELECT p FROM PtnPedido p WHERE p.codSituacao = :codSituacao"),
    @NamedQuery(name = "PtnPedido.findByNumPedido", query = "SELECT p FROM PtnPedido p WHERE p.numPedido = :numPedido"),
    @NamedQuery(name = "PtnPedido.findByDtDeposito", query = "SELECT p FROM PtnPedido p WHERE p.dtDeposito = :dtDeposito"),
    @NamedQuery(name = "PtnPedido.findByCodNatureza", query = "SELECT p FROM PtnPedido p WHERE p.codNatureza = :codNatureza"),
    @NamedQuery(name = "PtnPedido.findByCdTipoPedido", query = "SELECT p FROM PtnPedido p WHERE p.cdTipoPedido = :cdTipoPedido"),
    @NamedQuery(name = "PtnPedido.findByDtFaseNacional", query = "SELECT p FROM PtnPedido p WHERE p.dtFaseNacional = :dtFaseNacional")})
public class PtnPedido implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_pedido")
    private Integer codPedido;
   
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "cod_situacao")
    private String codSituacao;
    
    @Size(max = 12)
    @Column(name = "num_pedido")
    private String numPedido;
    
    @Column(name = "dt_deposito")
    @Temporal(TemporalType.DATE)
    private Date dtDeposito;
    
    @Size(max = 2)
    @Column(name = "cod_natureza")
    private String codNatureza;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "cd_tipo_pedido")
    private Character cdTipoPedido;
    
    @Lob
    @Size(max = 56)
    @Column(name = "titulo")
    private String titulo;
   
    @Column(name = "dt_fase_nacional")
    @Temporal(TemporalType.DATE)
    private Date dtFaseNacional;

    public PtnPedido() {
    }

    public PtnPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public PtnPedido(Integer codPedido, String codSituacao, Character cdTipoPedido) {
        this.codPedido = codPedido;
        this.codSituacao = codSituacao;
        this.cdTipoPedido = cdTipoPedido;
    }

    public Integer getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(Integer codPedido) {
        this.codPedido = codPedido;
    }

    public String getCodSituacao() {
        return codSituacao;
    }

    public void setCodSituacao(String codSituacao) {
        this.codSituacao = codSituacao;
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public Date getDtDeposito() {
        return dtDeposito;
    }

    public void setDtDeposito(Date dtDeposito) {
        this.dtDeposito = dtDeposito;
    }

    public String getCodNatureza() {
        return codNatureza;
    }

    public void setCodNatureza(String codNatureza) {
        this.codNatureza = codNatureza;
    }

    public Character getCdTipoPedido() {
        return cdTipoPedido;
    }

    public void setCdTipoPedido(Character cdTipoPedido) {
        this.cdTipoPedido = cdTipoPedido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDtFaseNacional() {
        return dtFaseNacional;
    }

    public void setDtFaseNacional(Date dtFaseNacional) {
        this.dtFaseNacional = dtFaseNacional;
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
        if (!(object instanceof PtnPedido)) {
            return false;
        }
        PtnPedido other = (PtnPedido) object;
        if ((this.codPedido == null && other.codPedido != null) || (this.codPedido != null && !this.codPedido.equals(other.codPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.patente.model.PtnPedido[ codPedido=" + codPedido + " ]";
    }
    
}
