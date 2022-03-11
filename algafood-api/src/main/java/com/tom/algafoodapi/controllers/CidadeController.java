package com.tom.algafoodapi.controllers;

import java.util.List;
import java.util.Optional;

import com.tom.algafoodapi.common.utils.StringUtils;
import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.domain.model.Estado;
import com.tom.algafoodapi.infrastructure.dto.CidadeDAO;
import com.tom.algafoodapi.services.CidadeService;

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
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping()
    public List<Cidade> listar() {
        return this.cidadeService.getRepository().findAll();
    }

    @GetMapping(value = "/{cidadeId}")
    public ResponseEntity<?> findyById(@PathVariable Long cidadeId) {
        Optional<Cidade> cidade = this.cidadeService.getRepository().findById(cidadeId);
        if (cidade.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(cidadeId,Cidade.class.getSimpleName()));
        }
        return ResponseEntity.ok(cidade);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CidadeDAO dto) {
        // TODO: process POST request

        try {
            Cidade cidade = this.cidadeService.add(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(dto.getEstado().getId(),Estado.class.getSimpleName()));
        }

    }

    @PutMapping(value = "/{cidadeId}")
    public ResponseEntity<?> update(@RequestBody CidadeDAO dto, @PathVariable Long cidadeId) {
        // TODO: process PUT request
        Optional<Cidade> cidade = this.cidadeService.getRepository().findById(cidadeId);

        if (cidade.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(StringUtils.entityNotExist(cidadeId,Cidade.class.getSimpleName()));
        }
        BeanUtils.copyProperties(dto, cidade.get(), "id");

        return ResponseEntity.status(HttpStatus.CREATED).body(this.cidadeService.getRepository().save(cidade.get()));

    }

    @DeleteMapping(value = "/{cidadeId}")
    public ResponseEntity<?> delete(@PathVariable Long cidadeId){

        Optional<Cidade> cidade = this.cidadeService.getRepository().findById(cidadeId);
        if(cidade.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(StringUtils.entityNotExist(cidadeId,Cidade.class.getSimpleName()));
        }
        this.cidadeService.getRepository().deleteById(cidadeId);
        return ResponseEntity.noContent().build();
    }

}
