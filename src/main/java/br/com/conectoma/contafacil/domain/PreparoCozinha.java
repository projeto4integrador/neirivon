package br.com.conectoma.contafacil.domain;

import java.io.Serializable;

public class PreparoCozinha implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String observacao;
	private String hora;
	private Boolean estado;
	
	public PreparoCozinha() {		
	}

	public PreparoCozinha(Long id, String observacao, String hora, Boolean estado) {
		super();
		this.id = id;
		this.observacao = observacao;
		this.hora = hora;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreparoCozinha other = (PreparoCozinha) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
