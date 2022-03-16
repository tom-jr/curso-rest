package com.tom.algafoodapi;

import com.tom.algafoodapi.infrastructure.repository.custom.CustomJpaRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication /*implements CommandLineRunner*/ {

	@Autowired
	private AllInjects allInjects;

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	// TODO Auto-generated method stub

	// 	Cozinha cozinha01 = new Cozinha(null, "Tailandesa");
	// 	Cozinha cozinha02 = new Cozinha(null, "Indiana");
	// 	Cozinha cozinha03 = new Cozinha(null, "Argentina");
	// 	Cozinha cozinha04 = new Cozinha(null, "Brasileira");
	// 	this.allInjects.getCozinhaRepository().saveAll(Arrays.asList(cozinha01, cozinha02, cozinha03, cozinha04));

	// 	Estado estado01 = new Estado(null, "Minas Gerais");
	// 	Estado estado02 = new Estado(null, "São Paulo");
	// 	Estado estado03 = new Estado(null, "Ceará");

	// 	this.allInjects.getEstadoRepository().saveAll(Arrays.asList(estado01, estado02, estado03));

	// 	Cidade cidade01 = new Cidade(null, "Uberlândia", estado01);
	// 	Cidade cidade02 = new Cidade(null, "Belo Horizonte", estado01);
	// 	Cidade cidade03 = new Cidade(null, "São Paulo", estado02);
	// 	Cidade cidade04 = new Cidade(null, "Campinas", estado02);
	// 	Cidade cidade05 = new Cidade(null, "Fortaleza", estado03);

	// 	allInjects.getCidadeRepository().saveAll(Arrays.asList(cidade01, cidade02, cidade03, cidade04, cidade05));

	// 	Endereco endereco01 = new Endereco("38400-999", "Rua João, Pinheiro", "1000", "complemento", "Centro",
	// 			cidade01);

	// 	LocalDateTime now = LocalDateTime.now();

	// 	Restaurante restaurante01 = new Restaurante(null, "Thai Gourmet", new BigDecimal("10"), now, now, endereco01,cozinha01);
	// 	Restaurante restaurante02 = new Restaurante(null, "Thai Delivery", new BigDecimal("9.50"), now, now, null,cozinha01);
	// 	Restaurante restaurante03 = new Restaurante(null, "Tuk Tuk Comida Indiana", new BigDecimal("15"), now, now,null, cozinha02);

	// 	Restaurante restaurante04 = new Restaurante(null, "Java Steakhouse", new BigDecimal("12"), now, now,null, cozinha03);
	// 	Restaurante restaurante05 = new Restaurante(null, "Lanchonete do Tio Sam", new BigDecimal("11"), now, now,null, cozinha04);
	// 	Restaurante restaurante06 = new Restaurante(null, "Bar da Maria", new BigDecimal("6"), now, now,null, cozinha04);


	// 	this.allInjects.getRestauranteRepository().saveAll(Arrays.asList(restaurante01, restaurante02, restaurante03,restaurante04,restaurante05,restaurante06));

	// 	FormaPagamento formaPagamento01 = new FormaPagamento(null, "Cartão de crédito");
	// 	FormaPagamento formaPagamento02 = new FormaPagamento(null, "Cartão de débito");
	// 	FormaPagamento formaPagamento03 = new FormaPagamento(null, "Dinheiro");

	// 	restaurante01.setFormasPagamento(Arrays.asList(formaPagamento01, formaPagamento02, formaPagamento03));
	// 	restaurante02.setFormasPagamento(Arrays.asList(formaPagamento03));
	// 	restaurante03.setFormasPagamento(Arrays.asList(formaPagamento02, formaPagamento03));
	// 	restaurante04.setFormasPagamento(Arrays.asList(formaPagamento01, formaPagamento02));
	// 	restaurante05.setFormasPagamento(Arrays.asList(formaPagamento01, formaPagamento02));
	// 	restaurante06.setFormasPagamento(Arrays.asList(formaPagamento03));


	// 	allInjects.getFormaPagamentoRepository()
	// 			.saveAll(Arrays.asList(formaPagamento01, formaPagamento02, formaPagamento03));
	// 	this.allInjects.getRestauranteRepository().saveAll(Arrays.asList(restaurante01, restaurante02, restaurante03));

	// 	Permissao permissao01 = new Permissao(null, "CONSULTAR_COZINHAS", "Permite consultar cozinhas");
	// 	Permissao permissao02 = new Permissao(null, "EDITAR_COZINHAS", "Permite editar cozinhas");

	// 	this.allInjects.getPermissaoRepository().saveAll(Arrays.asList(permissao01, permissao02));


	// 	Produto produto01 = new Produto(null, "Porco com molho agridoce", "Deliciosa carne suína ao molho especial", new BigDecimal("78.90"), true, restaurante01);
	// 	Produto produto02 = new Produto(null, "Camarão tailandês", "16 camarões grandes ao molho picante", new BigDecimal("110"), true, restaurante01);
	// 	Produto produto03 = new Produto(null, "Salada picante com carne grelhada", "Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha", new BigDecimal("87.20"), true, restaurante02);
	// 	Produto produto04 = new Produto(null, "Garlic Naan", "Pão tradicional indiano com cobertura de alho", new BigDecimal("21"), true, restaurante03);
	// 	Produto produto05 = new Produto(null, "Murg Curry", "Cubos de frango preparados com molho curry e especiarias", new BigDecimal("43"), true, restaurante03);
	// 	Produto produto06 = new Produto(null,"Bife Ancho", "Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé", new BigDecimal("79"), true, restaurante04);
	// 	Produto produto07 = new Produto(null,"T-Bone", "Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon", new BigDecimal("89"), true, restaurante04);
	// 	Produto produto08 = new Produto(null,"Sanduíche X-Tudo", "Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese", new BigDecimal("19"), true, restaurante05);
	// 	Produto produto09 = new Produto (null,"Espetinho de Cupim", "Acompanha farinha, mandioca e vinagrete", new BigDecimal("8"), true, restaurante06);

	// 	this.allInjects.getProdutoRepository().saveAll(Arrays.asList(produto01,produto02,produto03,produto04,produto05,produto06,produto07,produto08,produto09));

	// }

}
