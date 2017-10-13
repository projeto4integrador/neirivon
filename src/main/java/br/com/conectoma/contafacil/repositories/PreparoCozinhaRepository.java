package br.com.conectoma.contafacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectoma.contafacil.domain.PreparoCozinha;;

@Repository
public interface PreparoCozinhaRepository extends JpaRepository<PreparoCozinha, Long> {

}
