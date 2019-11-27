package com.wong.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wong.entity.Usuario;
import com.wong.repository.GestorUsuario;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {
	@Autowired
	@Qualifier("gestorUsuario")
	private GestorUsuario repo;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		BCryptPasswordEncoder encoder = passwordEncoder();
		Usuario user = repo.findByUsuario(username);
		
		return new User(user.getUsuario(), encoder.encode(user.getContrasena()), 
				user.isActivo(), user.isActivo(), user.isActivo(), user.isActivo(), buildgrante(user.getRol()));
	}
	
	public List<GrantedAuthority> buildgrante(byte rol) {
		String[] roles = {"Lector", "Usuario", "Administrador"};
		List<GrantedAuthority> auths = new ArrayList<>();
		
		for(int i = 0; i < rol; i++) {
			auths.add(new SimpleGrantedAuthority(roles[i]));
		}
		return auths;
	}
	
}
