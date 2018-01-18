package br.com.conectoma.contafacil.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectoma.contafacil.domain.Produto;
import br.com.conectoma.contafacil.dto.ProdutoDTO;
import br.com.conectoma.contafacil.resources.utils.URL;
import br.com.conectoma.contafacil.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Long id) {
		Produto obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(

			@RequestParam(value = "descricao", defaultValue = "") String descricao,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,

			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		String descricaoDecoded = URL.decodeParam(descricao);
		List<Long> ids = URL.decodeLongList(categorias);
		Page<Produto> list = service.search(descricaoDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(listDto);
	}

	
}
