/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.epec.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "TBRELATORIOEC")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Tbrelatorioec.findAll", query = "SELECT t FROM Tbrelatorioec t"),
	@NamedQuery(name = "Tbrelatorioec.countAll", query = "SELECT COUNT(t) FROM Tbrelatorioec t"),
	@NamedQuery(name = "Tbrelatorioec.countAllByIdPatenteEc", query = "SELECT COUNT(t) FROM Tbrelatorioec t WHERE t.idUsuario.idEntidadeEc.idEntidadeEc = :idEntidadeEc"),
	@NamedQuery(name = "Tbrelatorioec.findByIdRelatorioEc", query = "SELECT t FROM Tbrelatorioec t WHERE t.idRelatorioEc = :idRelatorioEc"),
	@NamedQuery(name = "Tbrelatorioec.findByBPublico", query = "SELECT t FROM Tbrelatorioec t WHERE t.bPublico = :bPublico"),
	@NamedQuery(name = "Tbrelatorioec.findByIdFamiliaEc", query = "SELECT t FROM Tbrelatorioec t WHERE t.idFamiliaEc.idFamiliaEc = :idFamiliaEc ORDER BY t.dtStatus DESC"),
	@NamedQuery(name = "Tbrelatorioec.findByDtStatus", query = "SELECT t FROM Tbrelatorioec t WHERE t.dtStatus = :dtStatus"),
	@NamedQuery(name = "Tbrelatorioec.findByStrRelatorio", query = "SELECT t FROM Tbrelatorioec t WHERE t.strRelatorio = :strRelatorio") })
public class Tbrelatorioec implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RELATORIO_EC")
    private Long idRelatorioEc;
    

    @Column(name = "B_PUBLICO")
    private boolean bPublico;

    public boolean isbPublico() {
		return bPublico;
	}

	public void setbPublico(boolean bPublico) {
		this.bPublico = bPublico;
	}

	@Column(name = "TX_RESUMO")
    private String txResumo;
	
	
    @Column(name = "TX_LINK")
    private String txLink;
    

    @Column(name = "TX_CONCLUSAO")
    private String txConclusao;
    

    @Column(name = "DT_STATUS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtStatus;
    
  
    @Column(name = "STR_RELATORIO")
    private String strRelatorio;
    
    @ManyToMany(mappedBy = "tbrelatorioecList")
    private List<Tbpatenteec> tbpatenteecList;
    
 
    @JoinTable(name = "TBRELATORIOCOLABORACAO", joinColumns = {
            @JoinColumn(name = "ID_RELATORIO_EC", referencedColumnName = "ID_RELATORIO_EC")}, inverseJoinColumns = {
            @JoinColumn(name = "ID_COLABORACAO", referencedColumnName = "ID_COLABORACAO")})
        @ManyToMany
        private List<Tbcadcolaboracao> tbcadcolaboracaoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRelatorioEc")
    private List<Tbantpatentariarelec> tbantpatentariarelecList;
   
	@Fetch(FetchMode.SELECT)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idRelatorioEc")
    private List<Tbcatrelatorioec> tbcatrelatorioecList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRelatorioEc")
    private List<Tbantnaopatentariarelec> tbantnaopatentariarelecList;
    
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Tbcadusuario idUsuario;
    
    @JoinColumn(name = "ID_STATUS", referencedColumnName = "ID_STATUS")
    @ManyToOne(optional = false)
    private Tbcadstatusrelatorio idStatus;
    
    @JoinColumn(name = "ID_FAMILIA_EC", referencedColumnName = "ID_FAMILIA_EC")
    @ManyToOne(optional = false)
    private Tbfamiliaec idFamiliaEc;
    
    @Transient
    private String imagem;
    
    @Transient
    private String categorias;
    
    @Transient
    private boolean naoFinalizado;
    
    @Transient
    private boolean bPrivado;
    
    @Transient
    private boolean relatorioEmConstrucao;
    
    @Transient
    private boolean relatorioEmRevisao;
    
    
	@Transient
	private String emColaboracao;

	public String getEmColaboracao() {
		return emColaboracao;
	}

	public void setEmColaboracao(String emColaboracao) {
		this.emColaboracao = emColaboracao;
	}
    
    public boolean isRelatorioEmRevisao() {
    	if(this.getIdStatus().getIdStatus() == 2L)
    	{
    		return true;
    	}
		return false;
	}

	public void setRelatorioEmRevisao(boolean relatorioEmRevisao) {
		this.relatorioEmRevisao = relatorioEmRevisao;
	}

	public boolean isRelatorioEmConstrucao() {
    	if(this.getIdStatus().getIdStatus() == 1L)
    	{
    		return true;
    	}
		return false;
	}

	public void setRelatorioEmConstrucao(boolean relatorioEmConstrucao) {
		this.relatorioEmConstrucao = relatorioEmConstrucao;
	}

	public boolean isbPrivado() {
		return !bPublico;
	}

	public void setbPrivado(boolean bPrivado) {
		this.bPrivado = bPrivado;
	}

	public boolean isNaoFinalizado() {
    	if(this.getIdStatus().getIdStatus() == 3L)
    	{
    		return false;
    	}
		return true;
	}

	public void setNaoFinalizado(boolean finalizado) {
		this.naoFinalizado = finalizado;
	}

	@Transient
    private Tbcadcategoria tbcadcategoria;

    public Tbcadcategoria getTbcadcategoria() {
		return tbcadcategoria;
	}

	public void setTbcadcategoria(Tbcadcategoria tbcadcategoria) {
		this.tbcadcategoria = tbcadcategoria;
	}

	public String getImagem() {
		if(strRelatorio.isEmpty()){
			return null;
		}
		return "images/_bandeirasP/" + strRelatorio.substring(0, 2) + ".png";
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Tbrelatorioec() {
    }

    public Tbrelatorioec(Long idRelatorioEc) {
        this.idRelatorioEc = idRelatorioEc;
    }

    public Tbrelatorioec(Long idRelatorioEc, boolean bPublico, Date dtStatus, String strRelatorio) {
        this.idRelatorioEc = idRelatorioEc;
        this.bPublico = bPublico;
        this.dtStatus = dtStatus;
        this.strRelatorio = strRelatorio;
    }

    public Long getIdRelatorioEc() {
        return idRelatorioEc;
    }

    public void setIdRelatorioEc(Long idRelatorioEc) {
        this.idRelatorioEc = idRelatorioEc;
    }

    public boolean getBPublico() {
        return bPublico;
    }

    public void setBPublico(boolean bPublico) {
        this.bPublico = bPublico;
    }

    public String getTxResumo() {
        return txResumo;
    }

    public void setTxResumo(String txResumo) {
        this.txResumo = txResumo;
    }

    public String getTxConclusao() {
        return txConclusao;
    }

    public void setTxConclusao(String txConclusao) {
        this.txConclusao = txConclusao;
    }

    public Date getDtStatus() {
        return dtStatus;
    }

    public void setDtStatus(Date dtStatus) {
        this.dtStatus = dtStatus;
    }

    public String getStrRelatorio() {
        return strRelatorio;
    }

    public void setStrRelatorio(String strRelatorio) {
        this.strRelatorio = strRelatorio;
    }
    
    public String getTxLink() {
        return txLink;
    }

    public void setTxLink(String txLink) {
        this.txLink = txLink;
    }

    @XmlTransient
    public List<Tbpatenteec> getTbpatenteecList() {
        return tbpatenteecList;
    }

    public void setTbpatenteecList(List<Tbpatenteec> tbpatenteecList) {
        this.tbpatenteecList = tbpatenteecList;
    }

    @XmlTransient
    public List<Tbcadcolaboracao> getTbcadcolaboracaoList() {
        return tbcadcolaboracaoList;
    }

    public void setTbcadcolaboracaoList(List<Tbcadcolaboracao> tbcadcolaboracaoList) {
        this.tbcadcolaboracaoList = tbcadcolaboracaoList;
    }

    @XmlTransient
    public List<Tbantpatentariarelec> getTbantpatentariarelecList() {
        return tbantpatentariarelecList;
    }

    public void setTbantpatentariarelecList(List<Tbantpatentariarelec> tbantpatentariarelecList) {
        this.tbantpatentariarelecList = tbantpatentariarelecList;
    }

    @XmlTransient
    public List<Tbcatrelatorioec> getTbcatrelatorioecList() {
        return tbcatrelatorioecList;
    }

    public void setTbcatrelatorioecList(List<Tbcatrelatorioec> tbcatrelatorioecList) {
        this.tbcatrelatorioecList = tbcatrelatorioecList;
    }

    @XmlTransient
    public List<Tbantnaopatentariarelec> getTbantnaopatentariarelecList() {
        return tbantnaopatentariarelecList;
    }

    public void setTbantnaopatentariarelecList(List<Tbantnaopatentariarelec> tbantnaopatentariarelecList) {
        this.tbantnaopatentariarelecList = tbantnaopatentariarelecList;
    }

    public Tbcadusuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Tbcadusuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Tbcadstatusrelatorio getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Tbcadstatusrelatorio idStatus) {
        this.idStatus = idStatus;
    }

    public Tbfamiliaec getIdFamiliaEc() {
        return idFamiliaEc;
    }

    public void setIdFamiliaEc(Tbfamiliaec idFamiliaEc) {
        this.idFamiliaEc = idFamiliaEc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelatorioEc != null ? idRelatorioEc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbrelatorioec)) {
            return false;
        }
        Tbrelatorioec other = (Tbrelatorioec) object;
        if ((this.idRelatorioEc == null && other.idRelatorioEc != null) || (this.idRelatorioEc != null && !this.idRelatorioEc.equals(other.idRelatorioEc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.epec.beans.Tbrelatorioec[ idRelatorioEc=" + idRelatorioEc + " ]";
    }



	public String getCategorias() {
		return categorias;
	}

	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}

    
}
