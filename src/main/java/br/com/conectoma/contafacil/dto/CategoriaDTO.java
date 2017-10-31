package br.com.conectoma.contafacil.dto;

import java.io.Serializable;

import br.com.conectoma.contafacil.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	
	public CategoriaDTO () {
		
	}
	
	public CategoriaDTO(Categoria obj) {
		
		id = obj.getId();
		descricao =  obj.getDescricao();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
