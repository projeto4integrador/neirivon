package br.com.conectoma.contafacil.dto;

import java.io.Serializable;

import br.com.conectoma.contafacil.domain.Produto;

public class ProdutoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String descricao;
	
	private Double valor;
	
	public ProdutoDTO() {
		
	}

	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		descricao = obj.getDescricao();
		valor = obj.getValor();
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
