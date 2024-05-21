package br.org.serratec.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Cliente;
import br.org.serratec.ecommerce.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente) {
		return clienteRepository.save(cliente);
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
