package br.gov.inpi.patentes.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ptn_prioridade")
@NamedQueries({
@NamedQuery(name="PtnPrioridade.findPorId",
			query="SELECT p FROM PtnPrioridade p WHERE p.codigoPedido = :codigoPedido" )
})
public class PtnPrioridade implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		
	@Column(name = "cod_pedido")
	private Integer codigoPedido;
	
	
	@Id
	@Column(name="num_pedido_origem")
	private String numeroPrioridade;
	
	@Column(name="dt_pedido_origem")
	private Date dataPrioridade;
	
	
	@Column(name="cod_pais")
	private String pais;


	public Integer getCodigoPedido() {
		return codigoPedido;
	}


	public void setCodigoPedido(Integer codigoPedido) {
		this.codigoPedido = codigoPedido;
	}


	public String getNumeroPrioridade() {
		return numeroPrioridade;
	}


	public void setNumeroPrioridade(String numeroPrioridade) {
		this.numeroPrioridade = numeroPrioridade;
	}


	public Date getDataPrioridade() {
		return dataPrioridade;
	}


	public void setDataPrioridade(Date dataPrioridade) {
		this.dataPrioridade = dataPrioridade;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	@Override
	public String toString() {
		return "PtnPrioridade [codigoPedido=" + codigoPedido
				+ ", numeroPrioridade=" + numeroPrioridade
				+ ", dataPrioridade=" + dataPrioridade + ", pais=" + pais + "]";
	}
	
	
	
	

}
