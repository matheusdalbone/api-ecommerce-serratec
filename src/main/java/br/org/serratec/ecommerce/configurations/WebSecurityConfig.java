package br.org.serratec.ecommerce.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.org.serratec.ecommerce.filter.AuthenticationFilter;
import br.org.serratec.ecommerce.services.UsuarioDetailsServiceImpl;




@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig {
	@Autowired
	UsuarioDetailsServiceImpl userDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
           
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/health-check", "/auth/**",  "/swagger-ui/**","/api/roles/**", "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.GET,"/categorias/**","/produtos/**").permitAll()
                    .requestMatchers("/**").hasAnyRole("ADMIN","USER")
                    .anyRequest().permitAll())
            		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .requestMatchers(HttpMethod.GET,"/itemPedido").hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
//                    
//                    .requestMatchers(HttpMethod.GET,"/pedidos","/clientes","/enderecos").hasAuthority("ROLE_ADMIN")
//                    .requestMatchers(HttpMethod.POST,"/produtos","/categorias","/itemPedido").hasAuthority("ROLE_ADMIN")
//                    .requestMatchers(HttpMethod.PUT,"/pedidos","/produtos","/categorias","/itemPedido/{id}").hasAuthority("ROLE_ADMIN")
//                    .requestMatchers(HttpMethod.DELETE,"/**").hasAuthority("ROLE_ADMIN")
//                    
//                    .requestMatchers(HttpMethod.GET,"/enderecos/{id}","/pedidos/{id}","/clientes/{id}").hasAuthority("ROLE_USER")
//                    .requestMatchers(HttpMethod.POST,"/enderecos","/pedidos","/clientes","/pedidos").hasAuthority("ROLE_USER")
//                    .requestMatchers(HttpMethod.PUT,"/enderecos/{id}","/clientes/{id}").hasAuthority("ROLE_USER")
            		
		;
	

		http.authenticationProvider(authenticationProvider());

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080/"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public AuthenticationFilter authenticationJwtTokenFilter() {
		return new AuthenticationFilter();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}