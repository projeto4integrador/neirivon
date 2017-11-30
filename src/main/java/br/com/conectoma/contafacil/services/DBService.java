package br.com.conectoma.contafacil.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.conectoma.contafacil.domain.Biroska;
import br.com.conectoma.contafacil.domain.Categoria;
import br.com.conectoma.contafacil.domain.Cidade;
import br.com.conectoma.contafacil.domain.Cliente;
import br.com.conectoma.contafacil.domain.Endereco;
import br.com.conectoma.contafacil.domain.Estado;
import br.com.conectoma.contafacil.domain.ItemPedido;
import br.com.conectoma.contafacil.domain.Mesa;
import br.com.conectoma.contafacil.domain.Pagamento;
import br.com.conectoma.contafacil.domain.PagamentoComBoleto;
import br.com.conectoma.contafacil.domain.PagamentoComCartao;
import br.com.conectoma.contafacil.domain.Pedido;
import br.com.conectoma.contafacil.domain.PreparoCozinha;
import br.com.conectoma.contafacil.domain.Produto;
import br.com.conectoma.contafacil.domain.Usuario;
import br.com.conectoma.contafacil.domain.enums.EstadoPagamento;
import br.com.conectoma.contafacil.domain.enums.Perfil;
import br.com.conectoma.contafacil.domain.enums.TipoCliente;
import br.com.conectoma.contafacil.enums.StatusMesa;
import br.com.conectoma.contafacil.repositories.BiroskaRepository;
import br.com.conectoma.contafacil.repositories.CategoriaRepository;
import br.com.conectoma.contafacil.repositories.CidadeRepository;
import br.com.conectoma.contafacil.repositories.ClienteRepository;
import br.com.conectoma.contafacil.repositories.EnderecoRepository;
import br.com.conectoma.contafacil.repositories.EstadoRepository;
import br.com.conectoma.contafacil.repositories.ItemPedidoRepository;
import br.com.conectoma.contafacil.repositories.MesaRepository;
import br.com.conectoma.contafacil.repositories.PagamentoRepository;
import br.com.conectoma.contafacil.repositories.PedidoRepository;
import br.com.conectoma.contafacil.repositories.PreparoCozinhaRepository;
import br.com.conectoma.contafacil.repositories.ProdutoRepository;
import br.com.conectoma.contafacil.repositories.UsuarioRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired 
	private MesaRepository mesaRepository;
	@Autowired
	private BiroskaRepository biroskaRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		PreparoCozinha pc1 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc2 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc3 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		
		Categoria cat1 = new Categoria(null, "Bebida");
		Categoria cat2 = new Categoria(null, "Sanduiche");
		Categoria cat3 = new Categoria(null, "Balas e doces");
		Categoria cat4 = new Categoria(null, "Porções");
		Categoria cat5 = new Categoria(null, "Salgados");
		Categoria cat6 = new Categoria(null, "Jantinha");
		Categoria cat7 = new Categoria(null, "Marmitex");
		Categoria cat8 = new Categoria(null, "Sobremesa");
		Categoria cat9 = new Categoria(null, "Sortidos");
		Categoria cat10 = new Categoria(null, "Sorvete");
		Categoria cat11 = new Categoria(null, "Tempero");
		
		Produto p1 = new Produto(null, "Refrigerante", 3.00, 1);
		Produto p2 = new Produto(null, "Sanduiche bruto", 21.00, 1);
		Produto p3 = new Produto(null, "Cerveja Skol Litrao", 5.00, 1);
		Produto p4 = new Produto(null, "Porção de Batatinha", 5.00, 1);
		Produto p5 = new Produto(null, "Arroz carreteiro", 5.00, 1);
		Produto p6 = new Produto(null, "Marmitex com churrasco", 17.00, 1);
		Produto p7 = new Produto(null, "Queijo com doce de leite", 5.00, 1);
		Produto p8 = new Produto(null, "Kibon", 5.00, 1);
		Produto p9 = new Produto(null, "Pastel de guariroba", 5.00, 1);
		Produto p10 = new Produto(null, "Coxinha", 5.00, 1);
		Produto p11 = new Produto(null, "Filé de tilápia", 5.00, 1);
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		cat3.getProdutos().addAll(Arrays.asList(p7));
		cat4.getProdutos().addAll(Arrays.asList(p4,p11));
		cat5.getProdutos().addAll(Arrays.asList(p10));
		cat6.getProdutos().addAll(Arrays.asList(p5));
		cat7.getProdutos().addAll(Arrays.asList(p6));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat4));
		p5.getCategorias().addAll(Arrays.asList(cat6));
		p6.getCategorias().addAll(Arrays.asList(cat7));
		p7.getCategorias().addAll(Arrays.asList(cat3));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat5));
		p11.getCategorias().addAll(Arrays.asList(cat4));
		
		preparoCozinhaRepository.save(Arrays.asList(pc1, pc2, pc3));
		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.save(Arrays.asList(est1, est2));
		cidadeRepository.save(Arrays.asList(c1, c2, c3));
		
		Biroska bar1 = new Biroska(null, "Sanduicheria São Jorge", 18.9588555, 48.231682, "sanduicheriaSaoJorge.jpg");
		Biroska bar2 = new Biroska(null, "Lanchonete Seo Jorge", 18.953276, 48.235141, "LanchoneteSeoJorge.jpg");
		
		Cliente cli1 = new Cliente(null, "Neirivon Elias Cardoso", "neirivon@gmail.com", "80761917691", TipoCliente.PESSOAFISICA, pe.encode("jedi"));
		cli1.getTelefones().addAll(Arrays.asList("34.99674-5385","34.3222-1446"));
		
		Cliente cli2 = new Cliente(null, "Obi Wan Kenobi", "neirivon@hotmail.com", "97467393288", TipoCliente.PESSOAFISICA, pe.encode("jedi"));
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("34.7899-5455","34.3666-7666"));
		
		Usuario usu1 = new Usuario(null, "Neirivon Elias Cardoso", "neirivon@gmail.com", "123456");
		
		Mesa m1 = new Mesa(null, "01", StatusMesa.DISPONIVEL);
		Mesa m2 = new Mesa(null, "02", StatusMesa.DISPONIVEL);
		
		Endereco e1 = new Endereco(null, "Rua Maria Osória de Jesus", "138", "Casa", "São Jorge", "38410-198", cli1,c1);
		Endereco e2 = new Endereco(null, "Praça Nicolau Feres", "1227", "Apartamento", "Martins", "38410-111", cli1,c2);
		Endereco e3 = new Endereco(null, "Avenida Floriano Peixoto", "31", "Apartamento", "Centro", "38410-111", cli2,c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		usuarioRepository.save(Arrays.asList(usu1));
		biroskaRepository.save(Arrays.asList(bar1, bar2));
		clienteRepository.save(Arrays.asList(cli1, cli2));
		mesaRepository.save(Arrays.asList(m1, m2));
		enderecoRepository.save(Arrays.asList(e1, e2, e3));
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		
		Pedido ped1 = new Pedido(null, sdf.parse("16/01/2012 22:01"), bar1, cli1,usu1, m1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("17/01/2012 18:01"), bar2, cli1, usu1, m2, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("24/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.0, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
		
	}

}
