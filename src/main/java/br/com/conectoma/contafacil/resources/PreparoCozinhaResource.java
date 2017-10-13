package br.com.conectoma.contafacil.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectoma.contafacil.domain.PreparoCozinha;
import br.com.conectoma.contafacil.services.PreparoCozinhaService;

@RestController
@RequestMapping(value="/preparoscozinha")
public class PreparoCozinhaResource {
	
	@Autowired
	private PreparoCozinhaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		PreparoCozinha obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
		
	}

}
