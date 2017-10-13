package br.com.conectoma.contafacil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conectoma.contafacil.domain.PreparoCozinha;
import br.com.conectoma.contafacil.repositories.PreparoCozinhaRepository;

@Service
public class PreparoCozinhaService {
	
	@Autowired
	private PreparoCozinhaRepository repo;
	
	public PreparoCozinha buscar(Long id) {
		PreparoCozinha obj = repo.findOne(id);
		return obj;
	}

}
