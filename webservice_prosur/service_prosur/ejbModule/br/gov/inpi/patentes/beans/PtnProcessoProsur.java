package br.gov.inpi.patentes.beans;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "ptn_processo_prosur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PtnProcessoProsur.finByCodPedido", query = "SELECT d FROM PtnProcessoProsur d WHERE d.codigoPedidoInpi = :codigoPedidoInpi")})
public class PtnProcessoProsur {
     
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(name="cd_processo_prosur")
	private Integer codigo;
	
	@Column(name="dt_envio")
	private Date dataEnvio;
	
	
	@Column(name="id_prosur")
	private Long idProsur;
	
	
	@Column(name="cod_pedido")
	private Integer codigoPedidoInpi;


	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
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


	public Integer getCodigoPedidoInpi() {
		return codigoPedidoInpi;
	}


	public void setCodigoPedidoInpi(Integer codigoPedidoInpi) {
		this.codigoPedidoInpi = codigoPedidoInpi;
	}


	
	
	
}
