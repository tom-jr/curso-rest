package com.tom.algafoodapi.domain.repository.custom;

import java.util.List;

import com.tom.algafoodapi.domain.model.Restaurante;

public interface RestauranteRepositoryCustom {
    List<Restaurante> findAllWithFreeShip();

    
}
