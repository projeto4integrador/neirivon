package br.com.conectoma.contafacil.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.conectoma.contafacil.domain.Cliente;
import br.com.conectoma.contafacil.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "O Campo NOME é de preenchimento obrigatório")
	@Length(min=5,max=120,message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;

	@NotEmpty(message = "O Campo EMAIL é de preenchimento obrigatório")
	@Email(message = "Email inválido!")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	
}
