package br.com.conectoma.contafacil.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.conectoma.contafacil.enums.StatusMesa;

@Entity
@Table(name = "web_mesa")
public class Mesa implements Serializable {
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String numero;
	
	private Integer estado;
	
	@JsonIgnore
	@OneToMany(mappedBy="mesa")
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Mesa() {
		
	}

	public Mesa(Long id, String numero, StatusMesa estado) {
		super();
		this.id = id;
		this.numero = numero;
		this.estado = (estado==null) ? null : estado.getCod();
		
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
		return StatusMesa.toEnum(estado);
	}
	
	public void setEstado(StatusMesa estado) {
		this.estado = estado.getCod();
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Mesa other = (Mesa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
