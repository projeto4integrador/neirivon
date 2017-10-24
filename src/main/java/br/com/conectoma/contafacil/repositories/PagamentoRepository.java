package br.com.conectoma.contafacil.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectoma.contafacil.domain.Pagamento;

//É necessário criar apenas o Repository da SuperClasse

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
