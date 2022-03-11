package com.tom.algafoodapi.domain.repository;

import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.domain.repository.custom.CustomJpaRepository;

public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {
    
}
