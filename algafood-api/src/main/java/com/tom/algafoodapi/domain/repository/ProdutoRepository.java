package com.tom.algafoodapi.domain.repository;

import com.tom.algafoodapi.domain.model.Produto;
import com.tom.algafoodapi.domain.repository.custom.CustomJpaRepository;

public interface ProdutoRepository extends CustomJpaRepository<Produto,Long>{}
