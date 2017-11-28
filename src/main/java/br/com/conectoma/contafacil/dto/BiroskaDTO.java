package br.com.conectoma.contafacil.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.conectoma.contafacil.domain.Biroska;

public class BiroskaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "O Campo nome é de preenchimento obrigatório")
	@Length(min=5,max=80,message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	private Double latitude;
	
	private Double longitude;
	
	private String img_url;
	
	public BiroskaDTO () {
		
	}
	
	public BiroskaDTO(Biroska obj) {
		
		id = obj.getId();
		nome =  obj.getNome();
		latitude = obj.getLatitude();
		longitude = obj.getLongitude();
		img_url = obj.getImg_url();
		
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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

}
