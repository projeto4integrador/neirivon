package br.com.conectoma.contafacil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectoma.contafacil.domain.Biroska;

@Repository
public interface BiroskaRepository extends JpaRepository<Biroska, Long> {

	List<Biroska> findByNomeIgnoreCaseContaining(String nome);
}
