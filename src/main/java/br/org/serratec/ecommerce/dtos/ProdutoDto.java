package br.org.serratec.ecommerce.dtos;

public class ProdutoDto {
	private String produtoId;
	private String nome;

	public String getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
