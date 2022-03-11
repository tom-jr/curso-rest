package com.tom.algafoodapi.controllers;

import java.util.List;

import com.tom.algafoodapi.domain.model.FormaPagamento;
import com.tom.algafoodapi.services.FormaPagamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/formas-pagamentos")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;
    @GetMapping
    public List<FormaPagamento> listar() {
        return this.formaPagamentoService.getRepository().findAll();
    }
    
    
    
}
