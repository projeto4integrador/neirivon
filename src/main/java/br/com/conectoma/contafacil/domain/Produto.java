package br.com.conectoma.contafacil.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "web_produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String descricao;
	
	@NotNull
	@Column(name="valor")
    private double valor;
	
	@NotNull
    private int situacao;
	
	//@JsonIgnore
	//@ManyToOne
	//@JoinColumn(name = "id_categoria")
	
	//@JsonIgnore
	//@OneToMany(mappedBy = "id.produto")
	//private List<ItemPedido> itens = new ArrayList<>();
	
	@JsonBackReference //Do outro lado associação já foram buscados os objetos
	@ManyToMany
	@JoinTable(name="web_produto_categoria",
		joinColumns = @JoinColumn(name="produto_id"),
		inverseJoinColumns = @JoinColumn(name="categoria_id")
	)
	private List<Categoria> categorias = new ArrayList<>();

	public Produto(){
		
	}
	
	
	
	public Produto(Long id, String descricao, double valor, int situacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.situacao = situacao;
	}
/*
	@JsonIgnore
	public List<Pedido> getPedidos(){
		List<Pedido> list = new LinkedList<>();
		for(ItemPedido x : this.itens) {
			list.add(x.getPedido());
		}
		return list;
	}
	*/

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}



	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", valor=" + valor + ", situacao=" + situacao + "]";
	}
	
	

}
