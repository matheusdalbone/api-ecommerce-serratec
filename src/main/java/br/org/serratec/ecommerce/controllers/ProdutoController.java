package br.org.serratec.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.ecommerce.entities.Produto;
import br.org.serratec.ecommerce.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		return new ResponseEntity<>(produtoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id){
		Produto livro = produtoService.findById(id);
		if (livro == null)
			return new ResponseEntity<>(livro, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(livro, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.save(produto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto) {
		produto = produtoService.update(id,produto);
		return new ResponseEntity<>(produto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Boolean deletado = produtoService.delete(id);
		if (deletado)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
