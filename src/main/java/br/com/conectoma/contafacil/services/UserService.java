package br.com.conectoma.contafacil.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.conectoma.contafacil.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
