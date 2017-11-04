package br.com.conectoma.contafacil.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.conectoma.contafacil.domain.ItemPedido;
import br.com.conectoma.contafacil.domain.PagamentoComBoleto;
import br.com.conectoma.contafacil.domain.Pedido;
import br.com.conectoma.contafacil.domain.enums.EstadoPagamento;
import br.com.conectoma.contafacil.repositories.ClienteRepository;
import br.com.conectoma.contafacil.repositories.ItemPedidoRepository;
import br.com.conectoma.contafacil.repositories.PagamentoRepository;
import br.com.conectoma.contafacil.repositories.PedidoRepository;
import br.com.conectoma.contafacil.repositories.ProdutoRepository;
import br.com.conectoma.contafacil.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Pedido find(Long id) {
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id 
					+ ", Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getValor());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		System.out.println(obj);
		return obj;
	}

}
