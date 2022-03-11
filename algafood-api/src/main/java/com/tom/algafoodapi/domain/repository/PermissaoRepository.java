package com.tom.algafoodapi.domain.repository;

import com.tom.algafoodapi.domain.model.Permissao;
import com.tom.algafoodapi.domain.repository.custom.CustomJpaRepository;

public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {
    
}
