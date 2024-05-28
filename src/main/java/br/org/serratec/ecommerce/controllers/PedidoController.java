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

import br.org.serratec.ecommerce.dtos.PedidoDto;
import br.org.serratec.ecommerce.dtos.RelatorioPedidoDto;
import br.org.serratec.ecommerce.entities.Pedido;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundExceptionHandler;
import br.org.serratec.ecommerce.services.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		return new ResponseEntity<>(pedidoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/pedido-resumido")
	public ResponseEntity<List<PedidoDto>>findAllPedidoDto(){
		return new ResponseEntity<>(pedidoService.findAllPedidoDto(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido pedido = pedidoService.findById(id);
		
		if(pedido == null) {
			return new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);	
		}else {
			return new ResponseEntity<>(pedido, HttpStatus.OK);
		}
	}
	
	@GetMapping("/pedido-resumido/{id}")
	public ResponseEntity<PedidoDto> findByIdPedidoDto(@PathVariable Integer id){
		PedidoDto pedidoDto = null;
		pedidoDto = pedidoService.findByIdPedidoDto(id);
		if(pedidoDto == null) 
			return new ResponseEntity<>(pedidoService.findByIdPedidoDto(id), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(pedidoService.findByIdPedidoDto(id), HttpStatus.OK);
	}
	
	@GetMapping("/relatorio-pedido/{id}")
	public ResponseEntity<RelatorioPedidoDto> relatorioPedido(@PathVariable Integer id) {
		RelatorioPedidoDto relatorioPedido = null;
		relatorioPedido = pedidoService.relatorioPedido(id);
		
		if(relatorioPedido == null)
			return new ResponseEntity<>(relatorioPedido, HttpStatus.NOT_FOUND);
		else 
			return new ResponseEntity<>(relatorioPedido, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> save(@Valid @RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.save(pedido), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Integer id, @RequestBody @Valid Pedido pedido){
		pedido = pedidoService.update(id, pedido);
		return ResponseEntity.ok().body(pedido);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Boolean deletado = pedidoService.delete(id);
		
		if(deletado)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			throw new EntityNotFoundExceptionHandler("Este pedido não pode ser deletado pois o objeto de Id:" + id + " não existe. (╯°□°）╯︵ ┻━┻");
	}
	
}
