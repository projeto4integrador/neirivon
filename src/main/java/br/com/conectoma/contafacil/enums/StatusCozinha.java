package br.com.conectoma.contafacil.enums;

public enum StatusCozinha {
	
	PREPARO(1, "Em preparo"),
	LIBERADO(2, "Liberado");
	
	private int cod;
	private String descricao;
	
	private StatusCozinha(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusCozinha toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
		
		for(StatusCozinha x : StatusCozinha.values()) {
			if(codigo.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + codigo);
	}

}
