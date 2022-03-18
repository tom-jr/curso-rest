package com.tom.algafoodapi.controllers;

import java.util.List;
import java.util.Optional;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.infrastructure.dto.EstadoDTO;
import com.tom.algafoodapi.services.EstadoService;

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
@RequestMapping(value = "/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
        return this.estadoService.getRepository().findAll();
    }

    @GetMapping(value = "/{estadoId}")
    public ResponseEntity<?> findyById(@PathVariable Long estadoId) {
        Optional<Estado> cidade = this.estadoService.getRepository().findById(estadoId);
        if (cidade.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(estadoId, Estado.class.getSimpleName()));
        }
        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody EstadoDTO dto) {
        // TODO: process POST request
        Estado estado = this.estadoService.add(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estadoService.getRepository().save(estado));

    }

    @PutMapping(value = "/{estadoId}")
    public ResponseEntity<?> update(@PathVariable Long estadoId, @RequestBody EstadoDTO dto) {
        // TODO: process PUT request
        Optional<Estado> estado = this.estadoService.getRepository().findById(estadoId);
        if (estado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(estadoId, Estado.class.getSimpleName()));
        }
        BeanUtils.copyProperties(dto, estado.get(), "id");

        return ResponseEntity.status(HttpStatus.CREATED).body(this.estadoService.getRepository().save(estado.get()));
    }


    @DeleteMapping(value = "/{estadoId}")
    public ResponseEntity<?> delete(@PathVariable Long estadoId) {
        Optional<Estado> estado = this.estadoService.getRepository().findById(estadoId);
        if (estado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(estadoId, Estado.class.getSimpleName()));
        }
        try {
            this.estadoService.getRepository().delete(estado.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(StringUtils.entityLinked(Estado.class.getSimpleName()));
        }
    }
}
