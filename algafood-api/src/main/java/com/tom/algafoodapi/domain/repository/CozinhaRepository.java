package com.tom.algafoodapi.domain.repository;

import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.repository.custom.CustomJpaRepository;
import com.tom.algafoodapi.domain.repository.custom.CozinhaRepositoryCustom;

public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>, CozinhaRepositoryCustom{

}
