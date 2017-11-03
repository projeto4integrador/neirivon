package br.com.conectoma.contafacil.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.conectoma.contafacil.domain.Categoria;
import br.com.conectoma.contafacil.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.descricao LIKE %:descricao% AND cat IN:categorias")
	//Page<Produto> search(@Param("descricao") String descricao,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	@Transactional(readOnly=true)
	Page<Produto> findDistinctByDescricaoContainingAndCategoriasIn(String descricao, List<Categoria> categorias, Pageable pageRequest);

}
