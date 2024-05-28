package br.org.serratec.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Usuario;
import br.org.serratec.ecommerce.repositories.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		return new UsuarioDetailsImpl(user);
	}

}