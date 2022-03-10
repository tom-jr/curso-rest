package com.tom.algafoodapi.domain.repository;

import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.domain.repository.custom.CustomJpaRepository;
import com.tom.algafoodapi.domain.repository.custom.RestauranteRepositoryCustom;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryCustom{

}
