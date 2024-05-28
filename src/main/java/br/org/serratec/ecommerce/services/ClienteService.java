package br.org.serratec.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.dtos.ClienteResumidoDto;
import br.org.serratec.ecommerce.entities.Cliente;
import br.org.serratec.ecommerce.entities.Endereco;
import br.org.serratec.ecommerce.exceptions.EntityNotFoundExceptionHandler;
import br.org.serratec.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		if(clientes.isEmpty()) {
			throw new NoSuchElementException("Não existe nunhum cliente cadastrado.");
		}
		return clienteRepository.findAll();
	}
	
	public List<ClienteResumidoDto> findAllClienteResumido() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteResumidoDto> clientesDto = new ArrayList<>();
		
		for(Cliente cliente : clientes) {
			ClienteResumidoDto clienteDto = new ClienteResumidoDto();
			clienteDto.setNomeCompleto(cliente.getNomeCompleto());
			clienteDto.setEmail(cliente.getEmail());
			clienteDto.setCpf(cliente.getCpf());
 			
			clientesDto.add(clienteDto);
		}
		return clientesDto;
	}
	
	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundExceptionHandler("Este cliente não existe. Id: " + id));
	}
	
	public ClienteResumidoDto findByIdResumido(Integer id) {
		Cliente cliente = clienteRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundExceptionHandler("Este cliente não existe. Id: " + id));
	
		ClienteResumidoDto clienteDto = new ClienteResumidoDto();
		clienteDto.setNomeCompleto(cliente.getNomeCompleto());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setCpf(cliente.getCpf());
		
		return clienteDto;
	}
	
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Integer id,Cliente cliente) {
		Cliente clienteAux = clienteRepository.findById(id).orElseThrow(
				()-> new EntityNotFoundExceptionHandler("Este cliente não existe. Id: " + id));;
		Endereco endereco = cliente.getEndereco();
		clienteAux.setDataNascimento(cliente.getDataNascimento());
		clienteAux.setEndereco(endereco);
		clienteAux.setTefelone(cliente.getTefelone());
		clienteAux.setEmail(cliente.getEmail());
		clienteAux.setNomeCompleto(cliente.getNomeCompleto());
		return clienteRepository.save(clienteAux);
	}
	
	public Boolean delete(Integer id) {
		if(clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			Cliente clienteDeletado =  clienteRepository.findById(id).orElse(null);
			
			if(clienteDeletado == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	
	
}
