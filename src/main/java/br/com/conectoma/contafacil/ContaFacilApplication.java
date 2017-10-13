package br.com.conectoma.contafacil;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.conectoma.contafacil.domain.Categoria;
import br.com.conectoma.contafacil.domain.PreparoCozinha;
import br.com.conectoma.contafacil.domain.Produto;
import br.com.conectoma.contafacil.repositories.CategoriaRepository;
import br.com.conectoma.contafacil.repositories.PreparoCozinhaRepository;
import br.com.conectoma.contafacil.repositories.ProdutoRepository;

@SpringBootApplication
public class ContaFacilApplication implements CommandLineRunner {

	@Autowired
	private PreparoCozinhaRepository preparoCozinhaRepository;
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ContaFacilApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		PreparoCozinha pc1 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc2 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc3 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		
		Categoria cat1 = new Categoria(null, "Bebida");
		Categoria cat2 = new Categoria(null, "Sanduiche");
		
		Produto p1 = new Produto(null, "Refrigerante", 3.00, 1);
		Produto p2 = new Produto(null, "Pao", 1.00, 1);
		Produto p3 = new Produto(null, "Cerveja", 5.00, 1);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		preparoCozinhaRepository.save(Arrays.asList(pc1, pc2, pc3));
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		produtoRepository.save(Arrays.asList(p1, p2, p3));
		
	}
}
