package com.tom.algafoodapi;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.model.Endereco;
import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.domain.model.FormaPagamento;
import com.tom.algafoodapi.domain.model.Permissao;
import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.infrastructure.repository.custom.CustomJpaRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication implements CommandLineRunner {

	@Autowired
	private AllInjects allInjects;

	public static void main(String[] args) {
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		Cozinha cozinha01 = new Cozinha(null, "Tailandesa");
		Cozinha cozinha02 = new Cozinha(null, "Indiana");
		this.allInjects.getCozinhaRepository().saveAll(Arrays.asList(cozinha01, cozinha02));

		Estado estado01 = new Estado(null, "Minas Gerais");
		Estado estado02 = new Estado(null, "São Paulo");
		Estado estado03 = new Estado(null, "Ceará");

		this.allInjects.getEstadoRepository().saveAll(Arrays.asList(estado01, estado02, estado03));

		Cidade cidade01 = new Cidade(null, "Uberlândia", estado01);
		Cidade cidade02 = new Cidade(null, "Belo Horizonte", estado01);
		Cidade cidade03 = new Cidade(null, "São Paulo", estado02);
		Cidade cidade04 = new Cidade(null, "Campinas", estado02);
		Cidade cidade05 = new Cidade(null, "Fortaleza", estado03);

		allInjects.getCidadeRepository().saveAll(Arrays.asList(cidade01, cidade02, cidade03, cidade04, cidade05));

		Endereco endereco01 = new Endereco("38400-999", "Rua João, Pinheiro", "1000", "complemento", "Centro", cidade01);

		LocalDateTime now = LocalDateTime.now();

		Restaurante restaurante01 = new Restaurante(null, "Thai Gourmet", new BigDecimal("10"), now, now, endereco01, cozinha01);
		Restaurante restaurante02 = new Restaurante(null, "Thai Delivery", new BigDecimal("9.50"), now, now, endereco01, cozinha01);
		Restaurante restaurante03 = new Restaurante(null, "Tuk Tuk Comida Indiana", new BigDecimal("15"), now, now, endereco01, cozinha02);
		restaurante01.setEndereco(endereco01);

		this.allInjects.getRestauranteRepository().saveAll(Arrays.asList(restaurante01, restaurante02, restaurante03));

		FormaPagamento formaPagamento01 = new FormaPagamento(null, "Cartão de crédito");
		FormaPagamento formaPagamento02 = new FormaPagamento(null, "Cartão de débito");
		FormaPagamento formaPagamento03 = new FormaPagamento(null, "Dinheiro");

		restaurante01.setFormasPagamento(Arrays.asList(formaPagamento01, formaPagamento02, formaPagamento03));
		restaurante02.setFormasPagamento(Arrays.asList(formaPagamento03));
		restaurante03.setFormasPagamento(Arrays.asList(formaPagamento02, formaPagamento03));

		allInjects.getFormaPagamentoRepository()
				.saveAll(Arrays.asList(formaPagamento01, formaPagamento02, formaPagamento03));
		this.allInjects.getRestauranteRepository().saveAll(Arrays.asList(restaurante01, restaurante02, restaurante03));

		Permissao permissao01 = new Permissao(null, "CONSULTAR_COZINHAS", "Permite consultar cozinhas");
		Permissao permissao02 = new Permissao(null, "EDITAR_COZINHAS", "Permite editar cozinhas");

		this.allInjects.getPermissaoRepository().saveAll(Arrays.asList(permissao01, permissao02));

	}
}
