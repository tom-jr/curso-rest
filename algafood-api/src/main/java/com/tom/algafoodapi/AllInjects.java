package com.tom.algafoodapi;

import com.tom.algafoodapi.domain.repository.CidadeRepository;
import com.tom.algafoodapi.domain.repository.CozinhaRepository;
import com.tom.algafoodapi.domain.repository.EstadoRepository;
import com.tom.algafoodapi.domain.repository.FormaPagamentoRepository;
import com.tom.algafoodapi.domain.repository.PermissaoRepository;
import com.tom.algafoodapi.domain.repository.ProdutoRepository;
import com.tom.algafoodapi.domain.repository.RestauranteRepository;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AllInjects {

    private CozinhaRepository cozinhaRepository;
    private RestauranteRepository restauranteRepository;
    private CidadeRepository cidadeRepository;
    private EstadoRepository estadoRepository;
    private FormaPagamentoRepository formaPagamentoRepository;
    private PermissaoRepository permissaoRepository;
    private ProdutoRepository produtoRepository;

    public AllInjects(CozinhaRepository cozinhaRepository, RestauranteRepository restauranteRepository,
            CidadeRepository cidadeRepository, EstadoRepository estadoRepository,
            FormaPagamentoRepository formaPagamentoRepository, PermissaoRepository permissaoRepository,
            ProdutoRepository produtoRepository) {
        this.cozinhaRepository = cozinhaRepository;
        this.restauranteRepository = restauranteRepository;
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.formaPagamentoRepository = formaPagamentoRepository;
        this.permissaoRepository = permissaoRepository;
        this.produtoRepository = produtoRepository;
    }

}
