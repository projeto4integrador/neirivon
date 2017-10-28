package br.com.conectoma.contafacil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectoma.contafacil.domain.PreparoCozinha;

@Repository
public interface PreparoCozinhaRepository extends JpaRepository<PreparoCozinha, Long> {
	
	 // Não levar em consideração a caixa (maiuscula ou minuscula)
	List<PreparoCozinha> findByObservacaoIgnoreCaseContaining(String observacao);

}
