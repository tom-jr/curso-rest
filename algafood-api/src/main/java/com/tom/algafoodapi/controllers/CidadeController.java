package com.tom.algafoodapi.controllers;

import java.util.List;

import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.services.CidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping()
    public List<Cidade> listar() {
        return this.cidadeService.getRepository().findAll();
    }
    
    
}
