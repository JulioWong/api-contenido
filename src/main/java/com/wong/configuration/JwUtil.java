package com.wong.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwUtil {

	// Método para crear el JWT y enviarlo al cliente en el header de la respuesta
	static void addAuthentication(HttpServletResponse res, String username) {
		String token = Jwts.builder()
						.setSubject(username)
						// hash con el que firmaremos la clave
						.signWith(SignatureAlgorithm.HS512, "P@titO")
						.compact();
		
		// Agregamos al encabezado del token
		res.addHeader("Authorization", "Bearer " + token);
	}
	
	static Authentication getAuthentication(HttpServletRequest request) {
		// Obtenemos el token que viene en el encabezado de la peticion
		String token = request.getHeader("Authorization");
		
		// si hay un token presente, entonces lo validamos
		if (token != null) {
			String user = Jwts.parser()
							.setSigningKey("P@titO")
							.parseClaimsJws(token.replace("Bearer", "")) // este metodo es el que valida
							.getBody()
							.getSubject();
			
			// recordamos que para las demas peticiones que no sean /login
			// no requerimos una authorizacion por username / password
			// por este motivo podemos devolver un UsernameAuthenticationToken sin password
			return user != null ? new UsernamePasswordAuthenticationToken(user, null, java.util.Collections.emptyList()) : null;
		}
		return null;
	}
}
