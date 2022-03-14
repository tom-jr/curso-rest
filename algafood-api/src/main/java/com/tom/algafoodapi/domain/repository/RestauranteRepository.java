package com.tom.algafoodapi.domain.repository;

import java.util.List;

import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.domain.repository.custom.CustomJpaRepository;
import com.tom.algafoodapi.domain.repository.custom.RestauranteRepositoryCustom;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryCustom , JpaSpecificationExecutor<Restaurante>{

}
