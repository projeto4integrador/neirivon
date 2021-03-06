package br.com.conectoma.contafacil.resources;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectoma.contafacil.domain.Mesa;
import br.com.conectoma.contafacil.dto.MesaDTO;
import br.com.conectoma.contafacil.services.MesaService;

@RestController
@RequestMapping(value="/mesas")
public class MesaResource {
	
	@Autowired
	private MesaService service;

	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Mesa> find(@PathVariable Long id) {
		Mesa obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MesaDTO>> findAll() {
		List<Mesa> list = service.findAll();

		List<MesaDTO> listDto = list.stream().map(obj -> new MesaDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

}
