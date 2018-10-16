/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBCADENTIDADE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tbcadentidade.findAll", query = "SELECT t FROM Tbcadentidade t"),
    @NamedQuery(name = "Tbcadentidade.countAll", query = "SELECT COUNT(t) FROM Tbcadentidade t"),
    @NamedQuery(name = "Tbcadentidade.findByIdEntidadeEc", query = "SELECT t FROM Tbcadentidade t WHERE t.idEntidadeEc = :idEntidadeEc"),
    @NamedQuery(name = "Tbcadentidade.findByIdAtivo", query = "SELECT t FROM Tbcadentidade t WHERE t.idAtivo = :idAtivo"),
    @NamedQuery(name = "Tbcadentidade.findByBOficina", query = "SELECT t FROM Tbcadentidade t WHERE t.oficina = :oficina"),
    @NamedQuery(name = "Tbcadentidade.findBypais", query = "SELECT t FROM Tbcadentidade t WHERE t.idPais.idPais = :idPais ")})
public class Tbcadentidade implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENTIDADE_EC")
    private Long idEntidadeEc;
    
    @Lob
    @Column(name = "TX_ORGANIZACAO")
    private String txOrganizacao;
    

    @Column(name = "ID_ATIVO")
    private short idAtivo;
    
    @Column(name = "B_OFICINA")
    private boolean oficina;
   
    @Column(name = "TX_DESCRICAO")
    private String txDescricao;
    
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "ID_PAIS")
    @ManyToOne(optional = false)
    private Tbcadpais idPais;
    
     @OneToMany(mappedBy = "idEntidadeEc")
     private List<Tbnos> tbnosList;
     
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidadeEc")
     private List<Tbpatenteec> tbpatenteecList;
     
     @JoinTable(name = "TBCOLABORACAOENTIDADE", joinColumns = {
             @JoinColumn(name = "ID_ENTIDADE_EC", referencedColumnName = "ID_ENTIDADE_EC", nullable = false)}, inverseJoinColumns = {
             @JoinColumn(name = "ID_COLABORACAO", referencedColumnName = "ID_COLABORACAO", nullable = false)})
         @ManyToMany
         private List<Tbcadcolaboracao> tbcadcolaboracaoList;
     
         @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEntidadeEc")
         private List<Tbcadusuario> tbcadusuarioList;
    
    @Transient
    private String nomePais;
    
    @Transient
    private String textoAtivo;
    
    @Transient
    private String imagemLogado;
    
    @Transient
    private String escritorioPatente;
    
    @Transient
    private String boescritorioPatente;
    
    @Transient
    private String imagem;
        

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
	
	public String getNomePais() {
		return nomePais;
	}

	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

    public Tbcadentidade() {
    }

    public Tbcadentidade(Long idEntidadeEc) {
        this.idEntidadeEc = idEntidadeEc;
    }

    public Tbcadentidade(Long idEntidadeEc, String txOrganizacao, short idAtivo, boolean oficina, String txDescricao) {
        this.idEntidadeEc = idEntidadeEc;
        this.txOrganizacao = txOrganizacao;
        this.idAtivo = idAtivo;
        this.oficina = oficina;
        this.txDescricao = txDescricao;
    }

    public Long getIdEntidadeEc() {
        return idEntidadeEc;
    }

    public void setIdEntidadeEc(Long idEntidadeEc) {
        this.idEntidadeEc = idEntidadeEc;
    }

    public String getTxOrganizacao() {
        return txOrganizacao;
    }

    public void setTxOrganizacao(String txOrganizacao) {
        this.txOrganizacao = txOrganizacao;
    }

    public short getIdAtivo() {
        return idAtivo;
    }

    public void setIdAtivo(short idAtivo) {
        this.idAtivo = idAtivo;
    }

    public boolean getOficina() {
        return oficina;
    }

    public void setOficina(boolean oficina) {
        this.oficina = oficina;
    }
    
    public String getTxDescricao() {
        return txDescricao;
    }

    public void setTxDescricao(String txDescricao) {
        this.txDescricao = txDescricao;
    }
    
    @XmlTransient
    public List<Tbcadcolaboracao> getTbcadcolaboracaoList() {
        return tbcadcolaboracaoList;
    }

    public void setTbcadcolaboracaoList(List<Tbcadcolaboracao> tbcadcolaboracaoList) {
        this.tbcadcolaboracaoList = tbcadcolaboracaoList;
    }
    
    @XmlTransient
    public List<Tbcadusuario> getTbcadusuarioList() {
        return tbcadusuarioList;
    }

    public void setTbcadusuarioList(List<Tbcadusuario> tbcadusuarioList) {
        this.tbcadusuarioList = tbcadusuarioList;
    }

    public Tbcadpais getIdPais() {
        return idPais;
    }

    public void setIdPais(Tbcadpais idPais) {
        this.idPais = idPais;
    }
    
    @XmlTransient
    public List<Tbpatenteec> getTbpatenteecList() {
        return tbpatenteecList;
    }

    public void setTbpatenteecList(List<Tbpatenteec> tbpatenteecList) {
        this.tbpatenteecList = tbpatenteecList;
    }
 
    @XmlTransient
    public List<Tbnos> getTbnosList() {
        return tbnosList;
    }

    public void setTbnosList(List<Tbnos> tbnosList) {
        this.tbnosList = tbnosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntidadeEc != null ? idEntidadeEc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbcadentidade)) {
            return false;
        }
        Tbcadentidade other = (Tbcadentidade) object;
        if ((this.idEntidadeEc == null && other.idEntidadeEc != null) || (this.idEntidadeEc != null && !this.idEntidadeEc.equals(other.idEntidadeEc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbcadentidade[ idEntidadeEc=" + idEntidadeEc + " ]";
    }



	public String getEscritorioPatente() {
		if(getOficina()){
			return "Sim";
		}else{
			return "Não";
		}

	}

	public void setEscritorioPatente(String escritorioPatente) {
		this.escritorioPatente = escritorioPatente;
	}

	public String getBoescritorioPatente() {
		
		if(boescritorioPatente == null){
			if(getOficina()){
				return "1";
			}else{
				return "0";
			}
		}
		return boescritorioPatente;

	}

	public void setBoescritorioPatente(String boescritorioPatente) {
		this.boescritorioPatente = boescritorioPatente;
	}

	public String getImagem() {
		
	if(paisTemBandeira(idPais.getStrCodPais())){
		return "/pages/images/_bandeirasP/" + idPais.getStrCodPais() + ".png";
	}
	return null;
		
	
	}
    
	private boolean paisTemBandeira( String pais){
		
		if(pais.equals("AR")|| pais.equals("AU") || pais.equals("BR") ||pais.equals("CA") || pais.equals("CL") || pais.equals("CO")
				|| pais.equals("DE") || pais.equals("EC") || pais.equals("EP") || pais.equals("ES") || pais.equals("GB") 
				|| pais.equals("JP") || pais.equals("PE") || pais.equals("PY") || pais.equals("SE") || pais.equals("SR")
				|| pais.equals("US") || pais.equals("UY")){
			return true;
		}
		
		return false;
	}
	
}
