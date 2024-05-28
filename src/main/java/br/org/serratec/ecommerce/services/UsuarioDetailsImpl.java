package br.org.serratec.ecommerce.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Usuario;

@Service
public class UsuarioDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public UsuarioDetailsImpl() {
		super();
	}

	public UsuarioDetailsImpl(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return usuario.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getNome().name()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}