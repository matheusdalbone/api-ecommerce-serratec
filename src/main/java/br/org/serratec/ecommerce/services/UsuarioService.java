package br.org.serratec.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.ecommerce.entities.Role;
import br.org.serratec.ecommerce.entities.RoleEnum;
import br.org.serratec.ecommerce.entities.Usuario;
import br.org.serratec.ecommerce.records.CredenciaisLoginRecord;
import br.org.serratec.ecommerce.records.JwtTokenRecord;
import br.org.serratec.ecommerce.records.UserRecord;
import br.org.serratec.ecommerce.repositories.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
	PasswordEncoder encoder;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public JwtTokenRecord authenticateUser(CredenciaisLoginRecord credenciaisLoginRecord) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(credenciaisLoginRecord.email(), credenciaisLoginRecord.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
       return new JwtTokenRecord(jwtTokenService.generateToken(userDetails));
    }

    public Usuario createUser(UserRecord userRecord) {

    	Set<String> strRoles = userRecord.role();
    	List<Role> roles = new ArrayList<>();
    	
    	for(String strRole : strRoles) {
    		Role role = new Role(RoleEnum.valueOf(strRole));
    		roles.add(role);
    	}
    	
    	Usuario newUser = new Usuario(userRecord.email(), 
					encoder.encode(userRecord.password()),
					roles
				);

        // Salva o novo usuário no banco de dados
        return usuarioRepository.save(newUser);
    }
}