package com.tom.algafoodapi.services;

import java.util.Optional;

import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.domain.repository.CozinhaRepository;
import com.tom.algafoodapi.domain.repository.RestauranteRepository;
import com.tom.algafoodapi.infrastructure.dto.RestauranteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaService cozinhaService;

    
    public Restaurante add(RestauranteDTO dto) throws Exception {

        Optional<Cozinha> cozinha = this.cozinhaService.getRepository().findById(dto.getCozinha().getId());
        if(cozinha.isEmpty()){
            throw new Exception();
        }

        Restaurante restaurante = new Restaurante(null, dto.getNome(), dto.getTaxaFrete(), cozinha.get());
        return restaurante;
    }


    public RestauranteRepository getRepository() {
        return restauranteRepository;
    }
}
