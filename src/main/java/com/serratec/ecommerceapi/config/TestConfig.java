package com.serratec.ecommerceapi.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.serratec.ecommerceapi.models.Categoria;
import com.serratec.ecommerceapi.models.Cliente;
import com.serratec.ecommerceapi.models.Endereco;
import com.serratec.ecommerceapi.models.Funcionario;
import com.serratec.ecommerceapi.models.FuncionarioProduto;
import com.serratec.ecommerceapi.models.Pedido;
import com.serratec.ecommerceapi.models.PedidoItem;
import com.serratec.ecommerceapi.models.Produto;
import com.serratec.ecommerceapi.repositories.CategoriaRepository;
import com.serratec.ecommerceapi.repositories.ClienteRepository;
import com.serratec.ecommerceapi.repositories.FuncioarioRepository;
import com.serratec.ecommerceapi.repositories.FuncionarioProdutoRepository;
import com.serratec.ecommerceapi.repositories.PedidoItemRepository;
import com.serratec.ecommerceapi.repositories.PedidoRepository;
import com.serratec.ecommerceapi.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private ClienteRepository clientRepository;

	@Autowired
	private PedidoRepository orderRepository;

	@Autowired
	private CategoriaRepository categoryRepository;

	@Autowired
	private ProdutoRepository productRepository;

	@Autowired
	private PedidoItemRepository orderItemRepository;

	@Autowired
	private FuncioarioRepository funcionarioRepository;

	@Autowired
	private FuncionarioProdutoRepository funcionarioProdutoRepository;

	@Override
	public void run(String... args) throws Exception {

		Endereco en1 = new Endereco(null, "Rua das Flores teste", "Centro", 22, "123456789", "Petropolis", "RJ", "");
		Endereco en2 = new Endereco(null, "Rua das Flores teste", "Centro", 22, "123456789", "Petropolis", "RJ", "");
		Endereco en3 = new Endereco(null, "Rua das Flores teste", "Centro", 22, "123456789", "Petropolis", "RJ", "");
		Endereco en4 = new Endereco(null, "Rua das Flores teste", "Centro", 22, "123456789", "Petropolis", "RJ", "");

		Funcionario f1 = new Funcionario(null, "João da Silva", "583.126.800-47", "129029092", en3);
		Funcionario f2 = new Funcionario(null, "Maria Santos", "812.341.150-26", "2092091092", en4);

		Cliente c1 = new Cliente(null, "rafa", "Rafael", "rafael.santana@gmail.com", "16353926758", new Date(),
				"123425687", en1);

		Cliente c2 = new Cliente(null, "rafa", "Rafael", "rafael.santana18@gmail.com", "24725291048", new Date(),
				"123456", en2);

		Pedido o1 = new Pedido(null, new Date(), c1);
		Pedido o2 = new Pedido(null, new Date(), c1);

		Categoria cat1 = new Categoria(null, "SUCOS", "teste suco");
		Categoria cat2 = new Categoria(null, "VINHOS", "teste suco");
		Categoria cat3 = new Categoria(null, "CERVEJAS", "teste suco");

		Produto p1 = new Produto(null, "SUCO ATIV PLUS", "Teste suco", new Date(), 2.50, cat1, f1,10);
		Produto p2 = new Produto(null, "SUCO SOJA ADES MACA", "teste suco", new Date(), 1.50, cat1, f1,20);
		Produto p3 = new Produto(null, "ITAIPAVA LATA", "teste suco", new Date(), 3.50, cat3, f2,12);
		Produto p4 = new Produto(null, "HACHE CABERNET SAUVIGNON 2017", "teste suco", new Date(), 2.50, cat2, f2,22);

		PedidoItem oi1 = new PedidoItem(null, o1, p1, 2.0F, p1.getValor());
		PedidoItem oi2 = new PedidoItem(null, o1, p3, 1.0F, p3.getValor());
		PedidoItem oi3 = new PedidoItem(null, o2, p3, 2.0F, p3.getValor());

		FuncionarioProduto fp1 = new FuncionarioProduto(null, f1, p1, "Cadastrou", new Date());
		FuncionarioProduto fp2 = new FuncionarioProduto(null, f1, p2, "Cadastrou", new Date());
		FuncionarioProduto fp3 = new FuncionarioProduto(null, f2, p3, "Cadastrou", new Date());
		FuncionarioProduto fp4 = new FuncionarioProduto(null, f2, p4, "Cadastrou", new Date());

		clientRepository.saveAll(Arrays.asList(c1, c2));

		orderRepository.saveAll(Arrays.asList(o1, o2));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

		funcionarioRepository.saveAll(Arrays.asList(f1, f2));

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));

		funcionarioProdutoRepository.saveAll(Arrays.asList(fp1, fp2, fp3, fp4));

	}

}
