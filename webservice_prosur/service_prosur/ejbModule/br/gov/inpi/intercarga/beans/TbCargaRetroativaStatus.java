/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.inpi.intercarga.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lasilva
 */
@Entity
@Table(name = "tb_carga_retroativa_status")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TbCargaRetroativaStatus.findAll", query = "SELECT t FROM TbCargaRetroativaStatus t"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findByIdCarga", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.idCarga = :idCarga"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findByTpCarga", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.tpCarga = :tpCarga"),    
    @NamedQuery(name = "TbCargaRetroativaStatus.findByQtVolExecutadoMrc", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.qtVolExecutadoMrc = :qtVolExecutadoMrc"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findByQtVolExecutarMrc", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.qtVolExecutarMrc = :qtVolExecutarMrc"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findByQtVolExecutadoDi", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.qtVolExecutadoDi = :qtVolExecutadoDi"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findByQtVolExecutarDi", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.qtVolExecutarDi = :qtVolExecutarDi"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findByQtVolExecutadoPatente", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.qtVolExecutadoPatente = :qtVolExecutadoPatente"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findByQtVolExecutarPatente", query = "SELECT t FROM TbCargaRetroativaStatus t WHERE t.qtVolExecutarPatente = :qtVolExecutarPatente"),
    @NamedQuery(name = "TbCargaRetroativaStatus.findQtVolExecutadoMrc", query = "SELECT t.qtVolExecutadoMrc FROM TbCargaRetroativaStatus t "
																			  + "WHERE t.qtVolExecutadoMrc IS NOT NULL "
																			  + "AND t.qtVolExecutarMrc IS NOT NULL "
																			  + "ORDER BY t.idCargaRetroativa DESC"),
	@NamedQuery(name = "TbCargaRetroativaStatus.findQtVolExecutarMrc", query = "SELECT t.qtVolExecutarMrc FROM TbCargaRetroativaStatus t "
																			 + "WHERE t.qtVolExecutadoMrc IS NOT NULL "
																			 + "AND t.qtVolExecutarMrc IS NOT NULL "
																			 + "ORDER BY t.idCargaRetroativa DESC"),
	@NamedQuery(name = "TbCargaRetroativaStatus.findQtVolExecutadoDi", query = "SELECT t.qtVolExecutadoDi FROM TbCargaRetroativaStatus t "
																			 + "WHERE t.qtVolExecutadoDi IS NOT NULL "
																			 + "AND t.qtVolExecutarDi IS NOT NULL "
																			 + "ORDER BY t.idCargaRetroativa DESC"),
	@NamedQuery(name = "TbCargaRetroativaStatus.findQtVolExecutarDi", query = "SELECT t.qtVolExecutarDi FROM TbCargaRetroativaStatus t "
																			+ "WHERE t.qtVolExecutadoDi IS NOT NULL "
																			+ "AND t.qtVolExecutarDi IS NOT NULL "
																			+ "ORDER BY t.idCargaRetroativa DESC"),
	@NamedQuery(name = "TbCargaRetroativaStatus.findQtVolExecutadoPatente", query = "SELECT t.qtVolExecutadoPatente FROM TbCargaRetroativaStatus t "
																				  + "WHERE t.qtVolExecutadoPatente IS NOT NULL "
																			      + "AND t.qtVolExecutarPatente IS NOT NULL "
																			      + "ORDER BY t.idCargaRetroativa DESC"),
	@NamedQuery(name = "TbCargaRetroativaStatus.findQtVolExecutarPatente", query = "SELECT t.qtVolExecutarPatente FROM TbCargaRetroativaStatus t "
																				 + "WHERE t.qtVolExecutadoPatente IS NOT NULL "
																				 + "AND t.qtVolExecutarPatente IS NOT NULL "
																				 + "ORDER BY t.idCargaRetroativa DESC")})
public class TbCargaRetroativaStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carga", nullable = false)
    private Integer idCarga;
    
    @Column(name = "tp_carga")
    private String tpCarga;
    
    @Column(name = "qt_vol_executado_mrc")
    private String qtVolExecutadoMrc;
    
    @Column(name = "qt_vol_executar_mrc")
    private String qtVolExecutarMrc;
    
    @Column(name = "qt_vol_executado_di")
    private String qtVolExecutadoDi;
    
    @Column(name = "qt_vol_executar_di")
    private String qtVolExecutarDi;
    
    @Column(name = "qt_vol_executado_patente")
    private String qtVolExecutadoPatente;
    
    @Column(name = "qt_vol_executar_patente")
    private String qtVolExecutarPatente;
    
    @Column(name = "id_carga_retroativa")
    private int idCargaRetroativa;
  /*  
    @JoinColumn(name = "id_carga_retroativa", referencedColumnName = "id_carga_retroativa")
    @ManyToOne
    private TbCargaRetroativa idCargaRetroativa;*/

    public TbCargaRetroativaStatus() {
    }

    public TbCargaRetroativaStatus(Integer idCarga, int idCargaRetroativa) {
        this.idCarga = idCarga;
        this.idCargaRetroativa = idCargaRetroativa;
    }


    public Integer getIdCarga() {
        return idCarga;
    }

    public void setIdCarga(Integer idCarga) {
        this.idCarga = idCarga;
    }

    public String getTpCarga() {
        return tpCarga;
    }

    public void setTpCarga(String tpCarga) {
        this.tpCarga = tpCarga;
    }

    public String getQtVolExecutadoMrc() {
        return qtVolExecutadoMrc;
    }

    public void setQtVolExecutadoMrc(String qtVolExecutadoMrc) {
        this.qtVolExecutadoMrc = qtVolExecutadoMrc;
    }

    public String getQtVolExecutarMrc() {
        return qtVolExecutarMrc;
    }

    public void setQtVolExecutarMrc(String qtVolExecutarMrc) {
        this.qtVolExecutarMrc = qtVolExecutarMrc;
    }

    public String getQtVolExecutadoDi() {
        return qtVolExecutadoDi;
    }

    public void setQtVolExecutadoDi(String qtVolExecutadoDi) {
        this.qtVolExecutadoDi = qtVolExecutadoDi;
    }

    public String getQtVolExecutarDi() {
        return qtVolExecutarDi;
    }

    public void setQtVolExecutarDi(String qtVolExecutarDi) {
        this.qtVolExecutarDi = qtVolExecutarDi;
    }

    public String getQtVolExecutadoPatente() {
        return qtVolExecutadoPatente;
    }

    public void setQtVolExecutadoPatente(String qtVolExecutadoPatente) {
        this.qtVolExecutadoPatente = qtVolExecutadoPatente;
    }

    public String getQtVolExecutarPatente() {
        return qtVolExecutarPatente;
    }

    public void setQtVolExecutarPatente(String qtVolExecutarPatente) {
        this.qtVolExecutarPatente = qtVolExecutarPatente;
    }
    
    public int getIdCargaRetroativa() {
        return idCargaRetroativa;
    }

    public void setIdCargaRetroativa(int idCargaRetroativa) {
        this.idCargaRetroativa = idCargaRetroativa;
    }

   /* public TbCargaRetroativa getIdCargaRetroativa() {
        return idCargaRetroativa;
    }

    public void setIdCargaRetroativa(TbCargaRetroativa idCargaRetroativa) {
        this.idCargaRetroativa = idCargaRetroativa;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarga != null ? idCarga.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCargaRetroativaStatus)) {
            return false;
        }
        TbCargaRetroativaStatus other = (TbCargaRetroativaStatus) object;
        if ((this.idCarga == null && other.idCarga != null) || (this.idCarga != null && !this.idCarga.equals(other.idCarga))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.gov.inpi.intercarga.beans.TbCargaRetroativaStatus[ idCarga=" + idCarga + " ]";
    }
    
}
