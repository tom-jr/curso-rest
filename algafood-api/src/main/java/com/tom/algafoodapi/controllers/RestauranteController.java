package com.tom.algafoodapi.controllers;

import java.util.List;
import java.util.Optional;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.services.RestauranteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    
    @GetMapping()
    public List<Restaurante> listar(){

        return restauranteService.getRestauranteRepository().findAll();
    }

    @GetMapping(value="/{restauranteId}")
    public ResponseEntity<?> getMethodName(@PathVariable Long restauranteId) {
    
        Optional<Restaurante> restaurante = this.restauranteService.getRestauranteRepository().findById(restauranteId);
        if(restaurante.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StringUtils.entityNotExist(restauranteId, Restaurante.class.getSimpleName()));
        }
        return ResponseEntity.ok(restaurante);
    }
    
    
}
