package br.gov.inpi.patentes.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="ptn_vinculo_pedido")
public class PtnPedidoDivido {
		
	@Id
	@Column(name="cd_pedido_origem")
	private Integer cdPedidoOrigem;
	
	@Column(name="cd_pedido_derivad")
	private Integer cdPedidoDerivad;
	
	
	@Column(name="dt_vinculo")
	private Date dataVinculoPedido;
	
	@Column(name="cd_vinculo")
	private String cdVinculo;

	public Integer getCdPedidoOrigem() {
		return cdPedidoOrigem;
	}

	public void setCdPedidoOrigem(Integer cdPedidoOrigem) {
		this.cdPedidoOrigem = cdPedidoOrigem;
	}

	public Integer getCdPedidoDerivad() {
		return cdPedidoDerivad;
	}

	public void setCdPedidoDerivad(Integer cdPedidoDerivad) {
		this.cdPedidoDerivad = cdPedidoDerivad;
	}

	public Date getDataVinculoPedido() {
		return dataVinculoPedido;
	}

	public void setDataVinculoPedido(Date dataVinculoPedido) {
		this.dataVinculoPedido = dataVinculoPedido;
	}

	public String getCdVinculo() {
		return cdVinculo;
	}

	public void setCdVinculo(String cdVinculo) {
		this.cdVinculo = cdVinculo;
	}

	
	
	

}
