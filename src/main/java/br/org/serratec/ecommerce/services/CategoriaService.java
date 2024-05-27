package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Categoria;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundExceptionHandler;
import br.org.serratec.ecommerce.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	 CategoriaRepository categoriaRepository;
	
	public List< Categoria> findAll() {
		return  categoriaRepository.findAll();
	}
	
	public  Categoria findById(Integer id) {
		return  categoriaRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundExceptionHandler("Esta categoria não existe. Id: "+id));
	}
	
	public  Categoria save(Categoria  categoria) {
		return  categoriaRepository.save(categoria);
	}
	
	public Categoria update(Categoria categoria) {
		Integer id = categoria.getCategoriaId();
		Categoria novaCategoria = categoriaRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundExceptionHandler("Esta categoria não existe. Id: "+id));
	
		novaCategoria.setNomeCategoria(categoria.getNomeCategoria());
		novaCategoria.setDescricaoCategoria(categoria.getDescricaoCategoria());
	
		return categoriaRepository.save(novaCategoria);
	}
	
	public Boolean delete(Integer id) {
		if(categoriaRepository.existsById(id)) {
			 categoriaRepository.deleteById(id);
			 Categoria categoriaDeletado = categoriaRepository.findById(id).orElse(null);
			
			if(categoriaDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
