package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Produto;
import br.org.serratec.ecommerce.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> findAll(){
		return produtoRepository.findAll();
	}
	
	public Produto findById(Integer id) {
		return produtoRepository.findById(id).orElse(null);
	}
	
	public Produto save(Produto Produto) {
		return produtoRepository.save(Produto);
	}
	
	public Produto update(Integer id, Produto produto) {
		Produto entidade = produtoRepository.getReferenceById(id);
		return produtoRepository.save(entidade);
	}
	
	public Boolean delete(Integer id) {
		if (produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			Produto produtoDeletado = produtoRepository.findById(id).orElse(null);
			if (produtoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public long count() {
		return produtoRepository.count();
	}
}
