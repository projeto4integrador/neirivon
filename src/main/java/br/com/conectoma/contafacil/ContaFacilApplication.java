package br.com.conectoma.contafacil;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.conectoma.contafacil.domain.Categoria;
import br.com.conectoma.contafacil.domain.Cidade;
import br.com.conectoma.contafacil.domain.Cliente;
import br.com.conectoma.contafacil.domain.Endereco;
import br.com.conectoma.contafacil.domain.Estado;
import br.com.conectoma.contafacil.domain.PreparoCozinha;
import br.com.conectoma.contafacil.domain.Produto;
import br.com.conectoma.contafacil.domain.enums.TipoCliente;
import br.com.conectoma.contafacil.repositories.CategoriaRepository;
import br.com.conectoma.contafacil.repositories.CidadeRepository;
import br.com.conectoma.contafacil.repositories.ClienteRepository;
import br.com.conectoma.contafacil.repositories.EnderecoRepository;
import br.com.conectoma.contafacil.repositories.EstadoRepository;
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
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Neirivon Elias Cardoso", "neirivon@gmail.com", "80761917691", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("34.99674-5385","34.3222-1446"));
		
		Endereco e1 = new Endereco(null, "Rua Maria Osória de Jesus", "138", "Casa", "São Jorge", "38410-198", cli1,c1);
		Endereco e2 = new Endereco(null, "Praça Nicolau Feres", "1227", "Apartamento", "Martins", "38410-111", cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1, e2));
		
	}
}
