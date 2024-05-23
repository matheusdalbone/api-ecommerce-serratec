package br.org.serratec.ecommerce.dtos;

public class ItemPedidoDto {

	private Integer idItemPedido;
	private Integer quantidade;
	private Double precoVenda;
	private Double percentualDesconto;
	private Double valorBruto;
	private Double valorLiquido;
	private Integer idPedido;
	private Integer idProduto;
	
	
	public ItemPedidoDto(Integer idItemPedido, Integer quantidade, Double precoVenda, Double percentualDesconto,
			Double valorBruto, Double valorLiquido, Integer idPedido, Integer idProduto) {
		super();
		this.idItemPedido = idItemPedido;
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.percentualDesconto = percentualDesconto;
		this.valorBruto = valorBruto;
		this.valorLiquido = valorLiquido;
		this.idPedido = idPedido;
		this.idProduto = idProduto;
	}


	public Integer getIdItemPedido() {
		return idItemPedido;
	}


	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPrecoVenda() {
		return precoVenda;
	}


	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}


	public Double getPercentualDesconto() {
		return percentualDesconto;
	}


	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}


	public Double getValorBruto() {
		return valorBruto;
	}


	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}


	public Double getValorLiquido() {
		return valorLiquido;
	}


	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}


	public Integer getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}


	public Integer getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
 
}
