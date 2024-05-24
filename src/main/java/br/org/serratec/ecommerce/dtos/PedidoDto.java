package br.org.serratec.ecommerce.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PedidoDto {
	private Integer idPedido;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPedido;
	
	private Double valorTotal;
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
