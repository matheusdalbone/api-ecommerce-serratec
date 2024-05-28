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

import br.org.serratec.ecommerce.entities.ItemPedido;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundExceptionHandler;
import br.org.serratec.ecommerce.services.ItemPedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/itemPedidos")
public class ItemPedidoController {
	
	@Autowired
	ItemPedidoService itemPedidoService;
	
	@GetMapping
	public ResponseEntity<List<ItemPedido>> findAll(){
		return new ResponseEntity<>(itemPedidoService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> findById(@PathVariable Integer id){
		ItemPedido itemPedido = itemPedidoService.findById(id);
		if (itemPedido == null)
			return new ResponseEntity<>(itemPedido, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(itemPedido, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ItemPedido> save(@RequestBody @Valid ItemPedido itemPedido) {
		return new ResponseEntity<>(itemPedidoService.save(itemPedido), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ItemPedido> update(@PathVariable Integer id, @RequestBody @Valid ItemPedido itemPedido) {
		itemPedido = itemPedidoService.update(id,itemPedido);
		return new ResponseEntity<>(itemPedido,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Boolean deletado = itemPedidoService.delete(id);
		if (deletado)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			throw new EntityNotFoundExceptionHandler("Este item não pode ser deletado do pedido pois o objeto de Id:" + id + " não existe. (╯°□°）╯︵ ┻━┻");
	}
}

