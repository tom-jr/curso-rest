package com.tom.algafoodapi.infrastructure.repository.custom;

import java.util.List;

import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.domain.repository.RestauranteRepository;
import com.tom.algafoodapi.domain.repository.custom.RestauranteRepositoryCustom;
import com.tom.algafoodapi.infrastructure.specs.RestauranteSpecs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {
    @Autowired
    @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> findAllWithFreeShip() {
        return this.restauranteRepository.findAll(RestauranteSpecs.withFreeShip());
    }
    
}
