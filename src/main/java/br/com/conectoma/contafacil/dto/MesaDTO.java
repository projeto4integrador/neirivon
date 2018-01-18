package br.com.conectoma.contafacil.dto;

import java.io.Serializable;

import br.com.conectoma.contafacil.domain.Mesa;
import br.com.conectoma.contafacil.enums.StatusMesa;

public class MesaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String numero;
	
	private StatusMesa estado;
	
	public MesaDTO() {
		
	}

	public MesaDTO(Mesa obj) {
		id = obj.getId();
		numero = obj.getNumero();
		estado = obj.getEstado();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public StatusMesa getEstado() {
		return estado;
	}

	public void setEstado(StatusMesa estado) {
		this.estado = estado;
	}
}
