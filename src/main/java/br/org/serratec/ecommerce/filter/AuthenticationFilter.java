package br.org.serratec.ecommerce.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.org.serratec.ecommerce.entities.Usuario;
import br.org.serratec.ecommerce.repositories.UsuarioRepository;
import br.org.serratec.ecommerce.services.JwtTokenService;
import br.org.serratec.ecommerce.services.UsuarioDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenService jwtTokenService; 

	@Autowired
	private UsuarioRepository userRepository; 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recoveryToken(request); // Pega o token do Header Authorization, na requisição
		if (token != null) {
			String subject = jwtTokenService.getSubjectFromToken(token); // Pega o subject do Jwt - ou seja, o e-mail do usuário

			Usuario user = userRepository.findByEmail(subject).get(); // Recupera os dados do Usuário através do seu e-mail

			UsuarioDetailsImpl userDetails = new UsuarioDetailsImpl(user); // Instancia um UserDetails com o usuário 

			// Gera um "Authentication" (objeto de autenticação do Spring Security)
			Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsuario().getEmail(), null,
					userDetails.getAuthorities());

			// Seta o objeto de autenticação no contexto de segurança do Spring Security
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} 
		
		// Dá prosseguimento no processamento da requisição
		filterChain.doFilter(request, response); 
	}

	private String recoveryToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}
}
