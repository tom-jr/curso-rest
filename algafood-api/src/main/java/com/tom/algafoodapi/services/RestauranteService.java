package com.tom.algafoodapi.services;

import java.time.LocalDateTime;
import java.util.Optional;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.tom.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.tom.algafoodapi.domain.exception.GeneralException;
import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.domain.repository.CozinhaRepository;
import com.tom.algafoodapi.domain.repository.RestauranteRepository;
import com.tom.algafoodapi.infrastructure.dto.RestauranteDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaService cozinhaService;

    public Restaurante add(Restaurante restaurante, RestauranteDTO dto) throws Exception {
        Cozinha cozinha = null;
        try {
            cozinha = this.cozinhaService.findById(dto.getCozinha().getId());
        } catch (EntidadeNaoEncontradaException e) {
            // TODO: handle exception
            throw new GeneralException(e.getMessage());
        }
        
        restaurante.setCozinha(cozinha);
        restaurante.setId(restaurante.getId() != null ? restaurante.getId() : null);
        restaurante.setNome(dto.getNome());
        restaurante.setTaxaFrete(dto.getTaxaFrete());
    
        return this.getRepository().save(restaurante);
    }

    public RestauranteRepository getRepository() {
        return restauranteRepository;
    }

    public Restaurante findById(Long restauranteId) {
        return this.getRepository().findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(
                StringUtils.entityNotExist(restauranteId, Restaurante.class.getSimpleName())));
    }

    public void delete(Long restauranteId) {
        Restaurante restaurante = this.findById(restauranteId);
        try {
        this.getRepository().delete(restaurante);
        } catch (DataIntegrityViolationException e) {
            //TODO: handle exception
            throw new EntidadeEmUsoException(StringUtils.entityLinked(restaurante.getClass().getSimpleName()));
        }
    }
}
