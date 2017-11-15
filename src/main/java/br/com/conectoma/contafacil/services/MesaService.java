package br.com.conectoma.contafacil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conectoma.contafacil.domain.Mesa;
import br.com.conectoma.contafacil.repositories.MesaRepository;
import br.com.conectoma.contafacil.services.exceptions.ObjectNotFoundException;

@Service
public class MesaService {
	
	@Autowired
	private MesaRepository repo;
	
	public Mesa find(Long id) {
		Mesa obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
					+ ", Tipo: " + Mesa.class.getName());
		}
		return obj;
	}
	
	public Mesa findAll(String mesa) {
		Mesa obj = (Mesa) repo.findAll();
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Mesa: " + mesa 
					+ ", Tipo: " + Mesa.class.getName());
		}
		return obj;
	}

}
