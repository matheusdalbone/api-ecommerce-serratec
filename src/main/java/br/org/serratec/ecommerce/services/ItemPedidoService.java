package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.ItemPedido;
import br.org.serratec.ecommerce.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> findAll() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido save(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido update(Integer id, ItemPedido itemPedido) {
		ItemPedido entidade = itemPedidoRepository.getReferenceById(id);
		return itemPedidoRepository.save(entidade);
	}
	
	public Boolean delete(Integer id) {
		if(itemPedidoRepository.existsById(id)) {
			itemPedidoRepository.deleteById(id);
			ItemPedido itemPedidoDeletado =  itemPedidoRepository.findById(id).orElse(null);
			
			if(itemPedidoDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
