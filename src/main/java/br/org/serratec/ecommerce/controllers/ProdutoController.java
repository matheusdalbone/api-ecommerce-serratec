package br.org.serratec.ecommerce.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.ecommerce.entities.Imagem;
import br.org.serratec.ecommerce.entities.Produto;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundExceptionHandler;
import br.org.serratec.ecommerce.services.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		return new ResponseEntity<>(produtoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		if (produto == null)
			return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@GetMapping("/{id}/imagem")
	public ResponseEntity<byte[]> findImagem(@PathVariable Integer id) {
		Imagem imagem = produtoService.getImagemId(id);
		if (imagem != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, imagem.getTipo());
			headers.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(imagem.getData().length));

			return new ResponseEntity<>(imagem.getData(), headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping
	public ResponseEntity<Produto> save(@RequestParam("file") MultipartFile file,
			@RequestPart("produto") @Valid Produto produto) throws IOException {
		return new ResponseEntity<>(produtoService.save(file, produto), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestParam("file") MultipartFile file,
			@RequestPart("produto") @Valid Produto produto) throws IOException {
		produto = produtoService.update(id, file, produto);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Boolean deletado = produtoService.delete(id);
		if (deletado)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			throw new EntityNotFoundExceptionHandler("Este pedido não pode ser deletado pois o objeto de Id:" + id + " não existe. (╯°□°）╯︵ ┻━┻");
	}

}
