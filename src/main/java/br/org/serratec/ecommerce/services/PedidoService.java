package br.org.serratec.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.dtos.PedidoDto;
import br.org.serratec.ecommerce.dtos.RelatorioPedidoDto;
import br.org.serratec.ecommerce.entities.Cliente;
import br.org.serratec.ecommerce.entities.ItemPedido;
import br.org.serratec.ecommerce.entities.Pedido;
import br.org.serratec.ecommerce.enums.StatusEnum;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundExceptionHandler;
import br.org.serratec.ecommerce.repositories.ClienteRepository;
import br.org.serratec.ecommerce.repositories.ItemPedidoRepository;
import br.org.serratec.ecommerce.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EmailService email;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<Pedido> findAll() {
		List<Pedido> pedidos = pedidoRepository.findAll();
		if(pedidos.isEmpty()) {
			throw new NoSuchElementException("Não existe nenhum pedido cadastrada.");
		}
		return pedidoRepository.findAll();
	}
	
	public List<PedidoDto> findAllPedidoDto(){
		List <Pedido> pedidos = pedidoRepository.findAll();
		List<PedidoDto> pedidosDto = new ArrayList<>();
		
		for(Pedido pedido: pedidos) {
			PedidoDto pedidoDto = new PedidoDto();
			pedidoDto.setIdPedido(pedido.getIdPedido());
			pedidoDto.setDataPedido(pedido.getDataPedido());
			pedidoDto.setValorTotal(pedido.getValorTotal());
			pedidosDto.add(pedidoDto);
		}
		return pedidosDto;
	}
	
	public Pedido findById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundExceptionHandler("Este pedido não existe. Id: " + id));

		return pedido;
	}
	
	public PedidoDto findByIdPedidoDto(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundExceptionHandler("Este pedido resumido não existe. Id: " + id));
		PedidoDto pedidoDto = null;
		pedidoDto = modelMapper.map(pedido, PedidoDto.class);
		
		return pedidoDto;
	}
	
	public Pedido save(Pedido pedido) {
		if (pedido.getStatus() == null) 
			pedido.setStatus(StatusEnum.PEDIDO_EM_ABERTO);
		return pedidoRepository.save(pedido);
	}
	
	public Pedido update(Integer id, Pedido pedido) {
		Integer clienteId = pedido.getCliente().getClienteId();
		Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
		String destinatario = cliente.getEmail();
		Pedido novoPedido = pedidoRepository.findById(id).orElse(null);
		updateData(novoPedido, pedido);
		
		switch(novoPedido.getStatus()) {
			case PEDIDO_REALIZADO:
				novoPedido = atualizarValor(id);
				if(novoPedido.getDataEnvio() == null && novoPedido.getDataEntrega() == null)
				email.enviaEmail(destinatario,"Pedido realizado" , "Seu pedido foi realizado." + relatorioPedido(id).toStringFormatado());
				if(novoPedido.getDataEnvio() != null && novoPedido.getDataEntrega() == null) {
					novoPedido.setStatus(StatusEnum.EM_TRANSITO);
					email.enviaEmail(destinatario,"Pedido em trânsito" , "Seu pedido foi enviado para a transportadora e está a caminho." + relatorioPedido(id).toStringFormatado());
				}
				if(novoPedido.getDataEnvio() != null && novoPedido.getDataEntrega() != null) {
					novoPedido.setStatus(StatusEnum.PEDIDO_ENTREGUE);
					email.enviaEmail(destinatario,"Pedido entregue" , "Seu pedido chegou em sua residência." + relatorioPedido(id).toStringFormatado());
				}
				break;
			default:
				break;

			}
		return pedidoRepository.save(novoPedido);
	} 

	private void updateData(Pedido novoPedido, Pedido pedido) {
		novoPedido.setDataEntrega(pedido.getDataEntrega());
		novoPedido.setDataEnvio(pedido.getDataEnvio());
		novoPedido.setStatus(pedido.getStatus());
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
	
	public RelatorioPedidoDto relatorioPedido(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundExceptionHandler("Este relatório de pedido não existe. Id: " + id));
		RelatorioPedidoDto relatorioPedido = null;
		
		relatorioPedido = modelMapper.map(pedido, RelatorioPedidoDto.class);
		return relatorioPedido;
	}
	

	public Pedido atualizarValor(Integer id) {
		
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(
				() -> new EntityNotFoundExceptionHandler("Este pedido não existe. Id: " + id));
		Double valorTotal = 0.0;
		
		List<ItemPedido> itensPedido = pedido.getItensPedido();
		for(ItemPedido item: itensPedido) {
			valorTotal += item.getValorLiquido();
		}
		
		pedido.setValorTotal(valorTotal);
		pedidoRepository.save(pedido);
		
		return pedido;
	}
	
}
