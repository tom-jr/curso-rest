package com.tom.algafoodapi.controllers;

import java.util.List;

import javax.validation.Valid;

import com.tom.algafoodapi.domain.model.Cidade;
import com.tom.algafoodapi.infrastructure.dto.CidadeDTO;
import com.tom.algafoodapi.services.CidadeService;

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
@RequestMapping(value = "/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping()
    public List<Cidade> listar() {
        return this.cidadeService.getRepository().findAll();
    }

    @GetMapping(value = "/{cidadeId}")
    public Cidade findById(@PathVariable Long cidadeId) {
        return cidadeService.findById(cidadeId);
    }

    @PostMapping
    public Cidade add(@RequestBody @Valid CidadeDTO dto) throws Exception {

        return this.cidadeService.add(new Cidade(), dto);
    }

    @PutMapping(value = "/{cidadeId}")
    public Cidade update(@RequestBody @Valid CidadeDTO dto, @PathVariable Long cidadeId) throws Exception {
        Cidade cidade = this.cidadeService.findById(cidadeId);
        return this.cidadeService.add(cidade, dto);
    }

    @DeleteMapping(value = "/{cidadeId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long cidadeId) {

        this.cidadeService.delete(cidadeId);
    }

}
