package com.tom.algafoodapi.controllers;

import java.util.List;

import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.infrastructure.dto.CozinhaDTO;
import com.tom.algafoodapi.services.CozinhaService;

import org.springframework.beans.BeanUtils;
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
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar() {
        return this.cozinhaService.getRepository().findAll();
    }

    @GetMapping(value = "/{cozinhaId}")
    public Cozinha findById(@PathVariable Long cozinhaId) {
        return this.cozinhaService.findById(cozinhaId);
    }

    @PostMapping
    public Cozinha adicionar(@RequestBody CozinhaDTO dto) {
        return this.cozinhaService.adicionar(dto);
    }

    @PutMapping(value = "/{cozinhaId}")
    public Cozinha update(@PathVariable Long cozinhaId, @RequestBody CozinhaDTO dto) {
        // TODO: process PUT request
        Cozinha cozinha = this.cozinhaService.findById(cozinhaId);
        BeanUtils.copyProperties(dto, cozinha, "id");

        return cozinhaService.getRepository().save(cozinha);
    }

    @DeleteMapping(value = "/{cozinhaId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cozinhaId) {

        this.cozinhaService.delete(cozinhaId);
    }
}
