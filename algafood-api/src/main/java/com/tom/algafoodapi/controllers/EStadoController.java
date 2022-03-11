package com.tom.algafoodapi.controllers;

import java.util.List;

import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.services.EstadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/estados")
public class EStadoController {
    
    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return this.estadoService.getRepository().findAll();
    }
    
}
