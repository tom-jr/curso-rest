package com.tom.algafoodapi.controllers;

import java.util.List;
import java.util.Optional;

import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.infrastructure.dto.CozinhaDTO;
import com.tom.algafoodapi.services.CozinhaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<?> findById(@PathVariable Long cozinhaId) {
        Optional<Cozinha> cozinha = this.cozinhaService.getRepository().findById(cozinhaId);
        if (cozinha.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("Cozinha com id: %d não existe", cozinhaId));
        }
        return ResponseEntity.ok(cozinha);
    }

    @PostMapping
    public Cozinha adicionar(@RequestBody CozinhaDTO dto){
        return this.cozinhaService.adicionar(dto);
    }
}
