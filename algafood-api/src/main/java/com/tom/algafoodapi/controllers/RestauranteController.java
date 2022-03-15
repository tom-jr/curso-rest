package com.tom.algafoodapi.controllers;

import java.util.List;
import java.util.Optional;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.infrastructure.dto.RestauranteDTO;
import com.tom.algafoodapi.infrastructure.specs.RestauranteSpecs;
import com.tom.algafoodapi.services.CozinhaService;
import com.tom.algafoodapi.services.RestauranteService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping()
    public List<Restaurante> listar() {

        return restauranteService.getRepository().findAll();
    }

    @GetMapping(value = "/{restauranteId}")
    public ResponseEntity<?> findById(@PathVariable Long restauranteId) {

        Optional<Restaurante> restaurante = this.restauranteService.getRepository().findById(restauranteId);
        if (restaurante.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(restauranteId, Restaurante.class.getSimpleName()));
        }
        return ResponseEntity.ok(restaurante);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody RestauranteDTO dto) {
        // TODO: process POST request

        try {
            Restaurante restaurante = this.restauranteService.add(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(this.restauranteService.getRepository().save(restaurante));
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    StringUtils.entityNotExist(dto.getCozinha().getId(), Cozinha.class.getSimpleName()));
        }

    }

    @PutMapping(value = "/{restaurantId}")
    public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody RestauranteDTO dto) {
        // TODO: process PUT request
        Optional<Restaurante> restaurante = this.restauranteService.getRepository().findById(restaurantId);
        Optional<Cozinha> cozinha = this.cozinhaService.getRepository().findById(dto.getCozinha().getId());

        if (restaurante.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(restaurantId, Restaurante.class.getSimpleName()));
        }
        if (cozinha.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(dto.getCozinha().getId(), Cozinha.class.getSimpleName()));
        }

        BeanUtils.copyProperties(dto, restaurante.get(), "id","formasPagamento","endereco");
        BeanUtils.copyProperties(dto.getCozinha(), cozinha.get(), "id");

        restaurante.get().setCozinha(cozinha.get());
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.restauranteService.getRepository().save(restaurante.get()));
    }

    @DeleteMapping(value = "/{restaurantId}")
    public ResponseEntity<?> delete(@PathVariable Long restaurantId) {

        Optional<Restaurante> restaurante = this.restauranteService.getRepository().findById(restaurantId);
        if (restaurante.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(restaurantId, Restaurante.class.getSimpleName()));
        }

        this.restauranteService.getRepository().delete(restaurante.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(value = "/spec")
    public ResponseEntity<?> withFreeShip(){
        return ResponseEntity.ok(this.restauranteService.getRepository().findAllWithFreeShip());
    }
}
