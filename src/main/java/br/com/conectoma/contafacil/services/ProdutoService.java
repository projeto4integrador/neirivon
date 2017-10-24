package br.com.conectoma.contafacil.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conectoma.contafacil.domain.Categoria;
import br.com.conectoma.contafacil.domain.Produto;
import br.com.conectoma.contafacil.repositories.CategoriaRepository;
import br.com.conectoma.contafacil.repositories.ProdutoRepository;
import br.com.conectoma.contafacil.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto buscar(Long id) {
		Produto obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
					+ ", Tipo: " + Produto.class.getName());
		}
		return obj;
	}

}
