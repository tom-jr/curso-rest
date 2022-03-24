package com.tom.algafoodapi.controllers;

import java.util.List;

import com.tom.algafoodapi.domain.model.Restaurante;
import com.tom.algafoodapi.infrastructure.dto.RestauranteDTO;
import com.tom.algafoodapi.services.RestauranteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService restauranteService;

    @GetMapping()
    public List<Restaurante> listar() {

        return restauranteService.getRepository().findAll();
    }

    @GetMapping(value = "/{restauranteId}")
    public Restaurante findById(@PathVariable Long restauranteId) {

        return this.restauranteService.findById(restauranteId);
    }

    @PostMapping
    public Restaurante add(@RequestBody RestauranteDTO dto) throws Exception {
        return this.restauranteService.add(new Restaurante(), dto);

    }

    @PutMapping(value = "/{restauranteId}")
    public Restaurante update(@PathVariable Long restauranteId, @RequestBody RestauranteDTO dto) throws Exception {
        Restaurante restaurante = this.restauranteService.findById(restauranteId);
        return this.restauranteService.add(restaurante, dto);
    }

    @DeleteMapping(value = "/{restauranteId}")
    public void delete(@PathVariable Long restauranteId) {

        this.restauranteService.delete(restauranteId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(value = "/spec")
    public List<Restaurante> withFreeShip() {
        return this.restauranteService.getRepository().findAllWithFreeShip();
    }
}
