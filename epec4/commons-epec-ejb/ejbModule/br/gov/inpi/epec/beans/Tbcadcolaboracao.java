/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCADCOLABORACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadcolaboracao.findAll", query = "SELECT t FROM Tbcadcolaboracao t order BY t.idColaboracao DESC"),
    @NamedQuery(name = "Tbcadcolaboracao.findByIdColaboracao", query = "SELECT t FROM Tbcadcolaboracao t WHERE t.idColaboracao = :idColaboracao"),
    @NamedQuery(name = "Tbcadcolaboracao.findByIdAtivo", query = "SELECT t FROM Tbcadcolaboracao t WHERE t.idAtivo = :idAtivo")})
public class Tbcadcolaboracao implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COLABORACAO")
    private Long idColaboracao;
    
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "STR_COLABORACAO", nullable = false, length = 65535)
    private String strColaboracao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ATIVO", nullable = false)
    private short idAtivo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TX_DESCRICAO", nullable = false, length = 65535)
    private String txDescricao;
    
    @ManyToMany(mappedBy = "tbcadcolaboracaoList")
    private List<Tbcadentidade> tbcadentidadeList;
    
    @ManyToMany(mappedBy = "tbcadcolaboracaoList")
    private List<Tbrelatorioec> tbrelatorioecList;

    @Transient
    private String textoAtivo;
    
    
    @Transient
    private String imagemLogado;


    @Transient
    private Status status;
    
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getImagemLogado() {
		
		if(idAtivo == 1){
			return "images/online.png";
		}else{
			return "images/offline.png";
		}

	}

	public void setImagemLogado(String imagemLogado) {
		this.imagemLogado = imagemLogado;
	}
    
    
    public String getTextoAtivo() {
  		return textoAtivo;
  	}

  	public void setTextoAtivo(String textoAtivo) {
  		this.textoAtivo = textoAtivo;
  	}
  	
  
    @Transient
    private String textoEntidades;
    
    public String getTextoEntidades() {
		return textoEntidades;
	}

	public void setTextoEntidades(String textoEntidades) {
		this.textoEntidades = textoEntidades;
	}
    	
    
    @Transient
    private List<Tbcadentidade> entidades;
    
    public List<Tbcadentidade> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<Tbcadentidade> entidades) {
		this.entidades = entidades;
	}
    
    @Transient
    private List<String> entidadesNome;

    public List<String> getEntidadesNome() {
		return entidadesNome;
	}

	public void setEntidadesNome(List<String> entidadesNome) {
		this.entidadesNome = entidadesNome;
	}

    
    public Tbcadcolaboracao() {
    }

    public Tbcadcolaboracao(Long idColaboracao) {
        this.idColaboracao = idColaboracao;
    }

    public Tbcadcolaboracao(Long idColaboracao, String strColaboracao, short idAtivo, String txDescricao) {
        this.idColaboracao = idColaboracao;
        this.strColaboracao = strColaboracao;
        this.idAtivo = idAtivo;
        this.txDescricao = txDescricao;
    }

    public Long getIdColaboracao() {
        return idColaboracao;
    }

    public void setIdColaboracao(Long idColaboracao) {
        this.idColaboracao = idColaboracao;
    }

    public String getStrColaboracao() {
        return strColaboracao;
    }

    public void setStrColaboracao(String strColaboracao) {
        this.strColaboracao = strColaboracao;
    }

    public short getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(short idAtivo) {
        this.idAtivo = idAtivo;
    }
    
    public String getTxDescricao() {
        return txDescricao;
    }

    public void setTxDescricao(String txDescricao) {
        this.txDescricao = txDescricao;
    }

    @XmlTransient
    public List<Tbcadentidade> getTbcadentidadeList() {
        return tbcadentidadeList;
    }

    public void setTbcadentidadeList(List<Tbcadentidade> tbcadentidadeList) {
        this.tbcadentidadeList = tbcadentidadeList;
    }

    @XmlTransient
    public List<Tbrelatorioec> getTbrelatorioecList() {
        return tbrelatorioecList;
    }

    public void setTbrelatorioecList(List<Tbrelatorioec> tbrelatorioecList) {
        this.tbrelatorioecList = tbrelatorioecList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColaboracao != null ? idColaboracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcadcolaboracao)) {
            return false;
        }
        Tbcadcolaboracao other = (Tbcadcolaboracao) object;
        if ((this.idColaboracao == null && other.idColaboracao != null) || (this.idColaboracao != null && !this.idColaboracao.equals(other.idColaboracao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcadcolaboracao[ idColaboracao=" + idColaboracao + " ]";
    }
    
}
