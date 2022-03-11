package com.tom.algafoodapi.services;

import com.tom.algafoodapi.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    


    
    public RestauranteRepository getRestauranteRepository() {
        return restauranteRepository;
    }
}
