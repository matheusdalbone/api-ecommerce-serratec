package br.org.serratec.ecommerce.dtos;

public class CategoriaDto {
	private String categoriaId;
	private String nomeCategoria;
	private String descricaoCategoria;
	public String getCategoriaId() {
		return categoriaId;
		
	}
	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	
}
