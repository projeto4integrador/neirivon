package br.com.conectoma.contafacil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conectoma.contafacil.domain.Usuario;
import br.com.conectoma.contafacil.repositories.UsuarioRepository;
import br.com.conectoma.contafacil.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Long id) {
		Usuario obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
					+ ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}
	
	public Usuario findAll(String email) {
		Usuario obj = (Usuario) repo.findAll();
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Usuario: " + email 
					+ ", Tipo: " + Usuario.class.getName());
		}
		return obj;
	}

}
