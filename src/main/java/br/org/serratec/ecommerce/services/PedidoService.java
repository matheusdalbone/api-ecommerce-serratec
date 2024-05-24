package br.org.serratec.ecommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.dtos.PedidoDto;
import br.org.serratec.ecommerce.entities.Pedido;
import br.org.serratec.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public List<PedidoDto> findAllPedidoDto(){
		List <Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDto> pedidosDto = new ArrayList<>();
		
		for(Pedido pedido: pedidos) {
			PedidoDto pedidoDto = new PedidoDto();
			pedidoDto.setIdPedido(pedido.getIdPedido());
			//pedidoDto.setDataPedido(pedido.getDataPedido());
			pedidoDto.setValorTotal(pedido.getValorTotal());
			pedidosDto.add(pedidoDto);
		}
		return pedidosDto;
	}
	
	public Pedido findById(Integer id) {
		return pedidoRepository.findById(id).orElseThrow();
	}
	
	public PedidoDto findByIdPedidoDto(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow();
		PedidoDto pedidoDto = null;
		pedidoDto = modelMapper.map(pedido, PedidoDto.class);
		
		return pedidoDto;
	}
	
	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido update(Integer id, Pedido pedido) {
		Pedido novoPedido = pedidoRepository.getReferenceById(id);
		updateData(novoPedido, pedido);
		return pedidoRepository.save(novoPedido);
	}

	private void updateData(Pedido novoPedido, Pedido pedido) {
		novoPedido.setDataPedido(pedido.getDataPedido());
		novoPedido.setDataEntrega(pedido.getDataEntrega());
		novoPedido.setDataEnvio(pedido.getDataEnvio());
		novoPedido.setStatus(pedido.getStatus());
		novoPedido.setValorTotal(pedido.getValorTotal());
		novoPedido.setCliente(pedido.getCliente());
		
	}
	
	public Boolean delete(Integer id) {
		if(pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
			Pedido pedidoDeletado =  pedidoRepository.findById(id).orElse(null);
			
			if(pedidoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
