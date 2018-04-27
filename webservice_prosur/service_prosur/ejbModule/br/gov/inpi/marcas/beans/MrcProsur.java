package br.gov.inpi.marcas.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/***
 * Entidade - Classe responsável em mapear a tabela
 * 'mrc_prosur' para manter um log dos pedidos que já
 * foram enviados para o serviço Prosur.
 * 
 * @author tgouvea
 *
 */
@Entity
@Table(name="mrc_processo_prosur")
public class MrcProsur implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="cd_processo_prosur")
	private Integer codigoProcessoProsur;
	
	@Column(name="dt_envio")
	private Date dataEnvio;

	
	@Column(name="id_prosur")
	private Long idProsur;
	
	
	@Column(name="cd_process")
	private Integer codigoPedidoMarcaInpi;


	public Integer getCodigoProcessoProsur() {
		return codigoProcessoProsur;
	}


	public void setCodigoProcessoProsur(Integer codigoProcessoProsur) {
		this.codigoProcessoProsur = codigoProcessoProsur;
	}


	public Date getDataEnvio() {
		return dataEnvio;
	}


	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}


	public Long getIdProsur() {
		return idProsur;
	}


	public void setIdProsur(Long idProsur) {
		this.idProsur = idProsur;
	}


	public Integer getCodigoPedidoMarcaInpi() {
		return codigoPedidoMarcaInpi;
	}


	public void setCodigoPedidoMarcaInpi(Integer codigoPedidoMarcaInpi) {
		this.codigoPedidoMarcaInpi = codigoPedidoMarcaInpi;
	}


	@Override
	public String toString() {
		return "MrcProsur [codigoProcessoProsur=" + codigoProcessoProsur
				+ ", dataEnvio=" + dataEnvio + ", idProsur=" + idProsur
				+ ", codigoPedidoMarcaInpi=" + codigoPedidoMarcaInpi + "]";
	}
	
	
	
	

}
