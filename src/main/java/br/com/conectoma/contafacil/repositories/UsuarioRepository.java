package br.com.conectoma.contafacil.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.conectoma.contafacil.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	 // Não levar em consideração a caixa (maiuscula ou minuscula)
	List<Usuario> findByUsuarioIgnoreCaseContaining(String usuario);


}
