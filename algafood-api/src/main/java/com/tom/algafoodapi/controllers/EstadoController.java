package com.tom.algafoodapi.controllers;

import java.util.List;

import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.infrastructure.dto.EstadoDTO;
import com.tom.algafoodapi.services.EstadoService;

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
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return this.estadoService.getRepository().findAll();
    }

    @GetMapping(value = "/{estadoId}")
    public Estado findById(@PathVariable Long estadoId) {
        return this.estadoService.findById(estadoId);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody EstadoDTO dto) {
        Estado estado = this.estadoService.add(new Estado(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estadoService.getRepository().save(estado));

    }

    @PutMapping(value = "/{estadoId}")
    public Estado update(@PathVariable Long estadoId, @RequestBody EstadoDTO dto) {
        Estado estado = this.estadoService.findById(estadoId);
        estado = this.estadoService.add(estado, dto);
        return this.estadoService.getRepository().save(estado);
    }

    @DeleteMapping(value = "/{estadoId}")
    public void delete(@PathVariable Long estadoId) {
        this.estadoService.delete(estadoId);
        
    }
}
