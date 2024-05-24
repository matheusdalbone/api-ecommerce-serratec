package br.org.serratec.ecommerce.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.dtos.RelatorioPedidoDto;
import br.org.serratec.ecommerce.entities.ItemPedido;
import br.org.serratec.ecommerce.entities.Produto;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundException;
import br.org.serratec.ecommerce.repositories.ItemPedidoRepository;
import br.org.serratec.ecommerce.repositories.ProdutoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ItemPedido> findAll() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findById(Integer id) {
		return itemPedidoRepository.findById(id).orElse(null);
	}
	
	public ItemPedido save(ItemPedido itemPedido) {
		Integer produtoId = itemPedido.getProduto().getProdutoId();
		Produto produto = produtoRepository.findById(produtoId).orElse(null);
		
		itemPedido.setPrecoVenda(produto.getValorUnitario());
		itemPedido.setValorBruto(itemPedido.getPrecoVenda() * itemPedido.getQuantidade());
		itemPedido.setValorLiquido(itemPedido.getValorBruto() - (itemPedido.getValorBruto() * itemPedido.getPercentualDesconto() / 100));
		
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido update(Integer id, ItemPedido itemPedido) {
		ItemPedido entidade = itemPedidoRepository.getReferenceById(id);
		
		Integer produtoId = itemPedido.getProduto().getProdutoId();
		Produto produto = produtoRepository.findById(produtoId).orElseThrow(
				() -> new EntityNotFoundException(produtoId));
		
		if(!produto.equals(null)) {
			entidade.setPrecoVenda(produto.getValorUnitario());
			entidade.setProduto(produto);
			entidade.setValorBruto(entidade.getPrecoVenda() * entidade.getQuantidade());
			entidade.setValorLiquido(entidade.getValorBruto() - (entidade.getValorBruto() * entidade.getPercentualDesconto() / 100));
		} else {
			return itemPedidoRepository.save(entidade);
		}
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
	
	public RelatorioPedidoDto relatorioPedido(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id).orElse(null);
		RelatorioPedidoDto relatorioPedido = null;
		
		relatorioPedido = modelMapper.map(itemPedido, RelatorioPedidoDto.class);
		
		return relatorioPedido;
	}
}
