package br.com.conectoma.contafacil.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.conectoma.contafacil.domain.PreparoCozinha;

@RestController
@RequestMapping(value="/preparoscozinha")
public class PreparoCozinhaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List <PreparoCozinha> listar() {
		
		PreparoCozinha pc1 = new PreparoCozinha(null,"Sem milho e tomate", "18:00", true);
		PreparoCozinha pc2 = new PreparoCozinha(null,"vatapa com pimento", "19:00", true);
		PreparoCozinha pc3 = new PreparoCozinha(null,"sem coentro", "11:00", true);
		
		List<PreparoCozinha> lista = new ArrayList<>();
		
		lista.add(pc1);
		lista.add(pc2);
		lista.add(pc3);
		
		return lista;
	}

}
