package br.com.conectoma.contafacil.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.conectoma.contafacil.domain.Categoria;
import br.com.conectoma.contafacil.repositories.CategoriaRepository;
import br.com.conectoma.contafacil.services.exceptions.DataIntegrityException;
import br.com.conectoma.contafacil.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Long id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
					+ ", Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {

		find(obj.getId());
		return repo.save(obj);
		
	}
	
	public void delete(Long id) {
		
		find(id); //Caso o id não exista dispara uma exceção
		
		try {
			repo.delete(id);
		} 
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Categoria que possui Produtos!");
		}
		
	}

	public List<Categoria> findAll() {
		
		return repo.findAll();
	}

}
